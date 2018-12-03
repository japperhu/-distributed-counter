package com.hu.test.counter.redis;

import static org.junit.Assert.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hu.counter.Counter;
import com.hu.counter.redis.RedisCounter;
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
	
	private static IRedisClient redisSentinelClient;
	
	@BeforeClass
	public static void init() {
		//redisClient=RedisClientBuilder.buildRedisClient();
		redisSentinelClient=RedisClientBuilder.buildRedisClient();
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
	
	/********sentinel模式test
	 * @throws InterruptedException *********/
	@Test
	public void testSentinelNormalOper() throws InterruptedException {
		redisSentinelClient.setex("m", 2, "val-m");
		assertEquals("val-m",redisSentinelClient.get("m"));
		Thread.sleep(3000);
		assertEquals(null,redisSentinelClient.get("m"));
		redisSentinelClient.lpush("list-a", "1","2","3");
		assertEquals("3",redisSentinelClient.lindex("list-a",0));
	}
	
	@Test
	public void testSentinelExecuteOper() {
		redisSentinelClient.execute(new IRedisExecuteCallback() {
			@Override
			public void action(Jedis jedis) {
				LOG.info("----flush前dbsize为：{}",jedis.dbSize());
				jedis.flushDB();
				assertEquals(0L, (long)jedis.dbSize());
				LOG.info("----flush后dbsize为：{}",jedis.dbSize());
			}
		});
	}
	/********cluster模式test*********/
	@Test
	public void testClusterNormalOper() {
		
	}
	
	@Test
	public void testClusterExecuteOper() {
		
	}
	
	@Test
	public void testCounter() {
		Counter counter=new RedisCounter("counter-a");
		assertEquals(null, counter.get());
		counter.set(2);
		assertEquals(2L, counter.get().longValue());
		counter.increment(1);
		assertEquals(3L, counter.get().longValue());
	}
	
	@Test
	public void testCounterWithMultiThreads() {
		Counter counter=new RedisCounter("counter-b");
		int threads=20;
		CountDownLatch countDownLatch=new CountDownLatch(threads);
		ThreadPoolExecutor executor=(ThreadPoolExecutor)Executors.newFixedThreadPool(threads);
		long begin=System.currentTimeMillis();
		for(int i=0;i<threads;i++) {
			Thread thread=new Thread (){
				@Override
				public void run() {
					for(int i=0;i<20000;i++) {
						counter.increment();
					}
					countDownLatch.countDown();
				}
			};
			thread.setName("thread-counter-"+i);
			executor.submit(thread);
		}
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOG.info("耗时:{}",System.currentTimeMillis()-begin);
		LOG.info("counter值:{}",counter.get());
		assertEquals(counter.get().intValue(), 400000L);
	}
	
	
	@AfterClass
	public static void destory() {
//		redisClient.destory();
//		redisClient=null;
		redisSentinelClient.destory();
		redisSentinelClient=null;
		LOG.info("---redisClient 销毁");
	}

}
