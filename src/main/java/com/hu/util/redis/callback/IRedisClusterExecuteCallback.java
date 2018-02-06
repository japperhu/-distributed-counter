package com.hu.util.redis.callback;

import redis.clients.jedis.JedisCluster;

/**
 * cluster模式的execute回调
 * <strong>注意不要在IRedisClusterExecuteCallback{@link #action(JedisCluster)}中 
 *  调用JedisCluster的close方法 </strong>
 * @author japper
 *
 */
public interface IRedisClusterExecuteCallback {
	/**
	 * redis集群操作
	 * @param jedisCluster redis集群客户端
	 */
	void action(JedisCluster jedisCluster);
}
