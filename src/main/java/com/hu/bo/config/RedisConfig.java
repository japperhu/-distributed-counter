package com.hu.bo.config;

import com.hu.annotation.PropertiesPrefix;
import com.hu.bo.config.redis.RedisClusterConfig;
import com.hu.bo.config.redis.RedisPoolConfig;
import com.hu.bo.config.redis.RedisSentinelConfig;
import com.hu.bo.enums.RedisModelEnum;

import lombok.Data;

/**
 * redis 配置信息
 * pool: redis连接池配置信息
 * sentinel: redis sentinel 配置信息
 * cluster : redis cluster 配置信息
 * @author japper
 *
 */
@PropertiesPrefix("redis")
@Data
public class RedisConfig {
	/**
	 * singleton模式redis连接池
	 */
	private RedisPoolConfig pool;
	/**
	 * sentinel模式redis连接池
	 */
	private RedisSentinelConfig sentinel;
	/**
	 * cluster模式redis连接池
	 */
	private RedisClusterConfig cluster;
	/**
	 * 默认的db编号
	 */
	private int dbNum=0;
	/**
	 * redis地址信息，singleton模式格式为 host:port 
	 * sentinel为 sentinels的 host1:port1,host2:port2,... 多个sentinel地址用逗号隔开
	 * cluster同sentinel多个地址用逗号隔开
	 */
	private String address;
	
	private String password;
	/**
	 * 是否启用redis counter
	 */
	private boolean enable;
	/**
	 * redis 运行模式，参见 {@link RedisModelEnum}
	 */
	private String model;
}
