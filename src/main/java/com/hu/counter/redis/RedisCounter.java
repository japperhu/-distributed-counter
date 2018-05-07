package com.hu.counter.redis;

import com.hu.counter.Counter;
import com.hu.util.redis.IRedisClient;
import com.hu.util.redis.RedisClientBuilder;
/**
 * 基于redis的counter实现
 * @author japper
 *
 */
public class RedisCounter implements Counter {
	private static IRedisClient redisClient;
	/**
	 * 计数器标志
	 */
	private String counterName;
	public RedisCounter(String counterName) {
		redisClient=RedisClientBuilder.buildRedisClient();
		this.counterName=counterName;
	}
	
	@Override
	public long increment(long incrementNum) {
		return redisClient.incrBy(counterName, incrementNum);
	}

	@Override
	public void set(long num) {
		redisClient.set(counterName, String.valueOf(num));
	}

	@Override
	public Long get() {
		String val=redisClient.get(counterName);
		return val==null?null:Long.valueOf(val);
	}

}
