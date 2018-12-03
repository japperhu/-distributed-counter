package com.hu.test.counter.zk;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.junit.Test;

import com.hu.counter.Counter;
import com.hu.counter.zookeeper.ZookeeperCounter;

import lombok.extern.slf4j.Slf4j;

/**
 * zkcounter测试
 * @author japper
 *
 */
@Slf4j
public class ZKClientTest {
	
	@Test
	public void testCounter() {
		Counter counter=new ZookeeperCounter("counter-a");
		long res1=counter.increment();
		assertEquals(res1, 1);
		long res2=counter.increment(3);
		assertEquals(res2, 4);
		counter.set(8);
		long res=counter.get();
		assertEquals(res, 8);
		log.info("--counter:{}",res);
	}
	

	@Test
	public void testCounterWithMultiThreads() {
		Counter counter=new ZookeeperCounter("counter-a");
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
		log.info("耗时:{}",System.currentTimeMillis()-begin);
		log.info("counter值:{}",counter.get());
		assertEquals(counter.get().intValue(), 400000L);
	}
}
