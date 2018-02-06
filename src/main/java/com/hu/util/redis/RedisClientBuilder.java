package com.hu.util.redis;

import java.util.ArrayList;
import java.util.List;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.hu.bo.config.RedisConfig;
import com.hu.bo.config.redis.RedisPoolConfig;
import com.hu.bo.enums.RedisModelEnum;
import com.hu.util.SystemConfigManager;
import com.hu.util.redis.impl.RedisClusterClient;
import com.hu.util.redis.impl.RedisSentinelClient;
import com.hu.util.redis.impl.RedisSingletonClient;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;


/**
 * 基于配置信息构建redisclient
 * @author japper
 *
 */
public class RedisClientBuilder {
	
	private static IRedisClient redisClient;
	
	private static final String ADDRESS_HOST_PORT_SPLIT_CHAR=":";
	private static final String MULITY_ADDRESS_SPLIT_CHAR=",";
	
	/**
	 * 基于redis配置信息 see classpath:app.yml 构建redis客户端
	 * @return
	 */
	public synchronized static IRedisClient buildRedisClient() {
		if(redisClient==null) {
		  RedisConfig redisConfig=SystemConfigManager.getSystemPropertiesBean(RedisConfig.class);
		  Preconditions.checkNotNull(redisConfig, "未配置redis相关信息，请在app.xml中配置redis相关信息");
		  Preconditions.checkArgument(redisConfig.isEnable(),"未在app.xml中设置redis.enable为true");
		  Preconditions.checkNotNull(redisConfig.getAddress(), "未在app.xml中配置redis.address");
		  Preconditions.checkNotNull(redisConfig.getAddress(), "未在app.xml中配置redis.model");
		  RedisModelEnum redisMOdel=RedisModelEnum.getRedisModelByType(redisConfig.getModel());
		  switch(redisMOdel) {
		  case SINGLETON :
			  redisClient=new RedisSingletonClient(buildRedisPool(redisConfig));
			  break;
		  case SENTINEL :
			  redisClient=new RedisSentinelClient(buildRedisSentinelPool(redisConfig));
			  break;
		  case CLUSTER :
			  redisClient=new RedisClusterClient(buildRedisCluster(redisConfig));
			  break;
		  default :
			  throw new IllegalArgumentException(" redis model is  wrong ,must be singleton、sentinel、cluster.");
		  }
		}
		return redisClient;
	}

	/**
	 * 构建redis集群客户端
	 * @param redisConfig redis配置信息
	 * @return
	 */
	private static JedisCluster buildRedisCluster(RedisConfig redisConfig) {
		String[] addresses=redisConfig.getAddress().split(MULITY_ADDRESS_SPLIT_CHAR);
		List<HostAndPort> addressList=new ArrayList<>(addresses.length);
		for(String address:addresses){
			String [] hostAndPortStr=address.split(ADDRESS_HOST_PORT_SPLIT_CHAR);
			addressList.add(new HostAndPort(hostAndPortStr[0], Integer.valueOf(hostAndPortStr[1])));
		}
		ImmutableSet<HostAndPort> hostAndPorts=ImmutableSet.copyOf(addressList);
		return new JedisCluster(hostAndPorts,redisConfig.getPool().getConnectionTimeout(),redisConfig.getPool().getSoTimeout(),redisConfig.getCluster().getMaxAttempts(),redisConfig.getPassword(),buildReidPoolConfig(redisConfig));
	}
	/**
	 * 构建redis单实例模式客户端连接池
	 * @param redisConfig redis配置信息
	 * @return
	 */
	private static JedisPool buildRedisPool(RedisConfig redisConfig) {
		String [] hostAndPort=redisConfig.getAddress().split(ADDRESS_HOST_PORT_SPLIT_CHAR);
		return new JedisPool(buildReidPoolConfig(redisConfig),hostAndPort[0],Integer.valueOf(hostAndPort[1]),redisConfig.getPool().getConnectionTimeout(),redisConfig.getPassword(),redisConfig.getDbNum());
	}
	
	/**
	 * 构建redis哨兵模式客户端连接池
	 * @param redisConfig redis配置信息
	 * @return
	 */
	private static JedisSentinelPool buildRedisSentinelPool(RedisConfig redisConfig) {
		//哨兵地址集合，每个地址格式为host:port
		ImmutableSet<String> sentinels=ImmutableSet.copyOf(redisConfig.getAddress().split(MULITY_ADDRESS_SPLIT_CHAR));
		return  new JedisSentinelPool(redisConfig.getSentinel().getMasterName(), sentinels, buildReidPoolConfig(redisConfig),redisConfig.getPool().getConnectionTimeout(),redisConfig.getPool().getSoTimeout(),redisConfig.getPassword(),redisConfig.getDbNum());
	}
	/**
	 * 构建redis pool配置信息
	 * @param redisConfig redis配置信息
	 * @return JedisPoolConfig
	 */
	private static JedisPoolConfig buildReidPoolConfig(RedisConfig redisConfig) {
		RedisPoolConfig redisPoolConfig=redisConfig.getPool();
		JedisPoolConfig config=new JedisPoolConfig();
		config.setMaxIdle(redisPoolConfig.getMaxIdle());
		config.setMaxTotal(redisPoolConfig.getMaxTotal());
		config.setMaxWaitMillis(redisPoolConfig.getMaxWaitMillis());
		config.setMinIdle(redisPoolConfig.getMinIdle());
		config.setTestOnCreate(redisPoolConfig.isTestOnCreate());
		config.setTestOnReturn(redisPoolConfig.isTestOnReturn());
		return config;
	}
}
