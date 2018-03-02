package com.hu.test.counter.redis;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hu.util.redis.IRedisClient;
import com.hu.util.redis.RedisClientBuilder;
import com.hu.util.redis.callback.IRedisExecuteCallback;

import redis.clients.jedis.Jedis;

/**
 * 封装的redis客户端测试
 * @author japper
 *
 */
public class RedisClientTest {
	private static final Logger LOG=LoggerFactory.getLogger(RedisClientTest.class);
	private static IRedisClient redisClient;
	
	
	@BeforeClass
	public static void init() {
		redisClient=RedisClientBuilder.buildRedisClient();
		LOG.info("---redisClient初始化完成");
	}
	/********singleton模式test
	 * @throws InterruptedException *********/
	@Test
	public void testSingleNormalOper() throws InterruptedException {
		redisClient.set("a", "val-a");
		assertEquals("val-a", redisClient.get("a"));
		redisClient.hset("hmap", "a", "b");
		assertEquals("b", redisClient.hget("hmap", "a"));
		redisClient.expire("hmap", 2);
		Thread.sleep(2500);
		assertEquals(null,redisClient.hget("hmap","a"));
	}
	
	@Test
	public void testSingleExecuteOper() {
		redisClient.execute(new IRedisExecuteCallback() {
			@Override
			public void action(Jedis jedis) {
				assertEquals(1L, (long)jedis.dbSize());
			}
		});
	}
	/********sentinel模式test*********/
	@Test
	public void testSentinelNormalOper() {
		
	}
	
	@Test
	public void testSentinelExecuteOper() {
		
	}
	/********cluster模式test*********/
	@Test
	public void testClusterNormalOper() {
		
	}
	
	@Test
	public void testClusterExecuteOper() {
		
	}
	
	
	
	@AfterClass
	public static void destory() {
		redisClient.destory();
		redisClient=null;
		LOG.info("---redisClient 销毁");
	}

}
