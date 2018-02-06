package com.hu.util.redis.callback;

import redis.clients.jedis.Jedis;

/**
 * jedis singleton和sentinel模式的execute回调
 * <strong>注意无需再IRedisExecuteCallback{@link #action(Jedis)}方法中调用jedis的close方法。 RedisClient会自动关闭</strong>
 * @author japper
 *
 */
public interface IRedisExecuteCallback {
	/**
	 * redis 操作
	 * @param jedis
	 */
	void action(Jedis jedis);

}
