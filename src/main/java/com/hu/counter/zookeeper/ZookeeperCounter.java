package com.hu.counter.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.framework.recipes.atomic.PromotedToLock;
import org.apache.curator.retry.RetryNTimes;

import com.hu.counter.Counter;
import com.hu.util.zookeeper.ZkClientBuilder;

import lombok.extern.slf4j.Slf4j;
/**
 * zookeeper计数器
 * 基于curator的DistributedAtomicLong
 * @author japper
 *
 */
@Slf4j
public class ZookeeperCounter implements Counter {
	
	private   DistributedAtomicLong 	distributedAtomicLong;
	/**
	 * 计数器标志
	 */
	private String counterName;
	
	public ZookeeperCounter(String counterName) {
		this.counterName=counterName;
		 CuratorFramework client = ZkClientBuilder.buildeCuratorFramework(3, 1000);
		 client.start();
		 //创建分布式计数器失败时重试10，每次间隔10毫秒
		 String path="/zkCounterRoot/".concat(counterName);
		 distributedAtomicLong= new DistributedAtomicLong(client,path , new RetryNTimes(10, 10),PromotedToLock.builder().retryPolicy(new RetryNTimes(Integer.MAX_VALUE, 1)).lockPath(path).build());
	}
	@Override
	public long increment(){
		try {
			AtomicValue<Long> result=distributedAtomicLong.increment();
			if(result.succeeded()) {
				return result.postValue();
			}
			log.warn("计数器:{}increment失败：{}",counterName,result);
			return increment();
		} catch (Exception e) {
			throw new RuntimeException("--计数器:"+counterName+"increment异常",e);
		}
	}

	@Override
	public long increment(long incrementNum) {
		try {
			AtomicValue<Long> result=distributedAtomicLong.add(incrementNum);
			if(result.succeeded()) {
				return result.postValue();
			}
			log.warn("计数器:{}increment指定值失败：{},尝试进行下一次",counterName,result);
			return increment(incrementNum);
		} catch (Exception e) {
			throw new RuntimeException("--计数器:"+counterName+"increment指定值异常",e);
		}
	}

	@Override
	public void set(long num) {
		try {
			distributedAtomicLong.forceSet(num);
		} catch (Exception e) {
			throw new RuntimeException("--计数器:"+counterName+"set指定值异常",e);
		}
	}

	@Override
	public Long get() {
		try {
			AtomicValue<Long> result=distributedAtomicLong.get();
			if(result.succeeded()) {
				return result.postValue();
			}
			log.warn("计数器:{}get失败：{},尝试进行下一次",counterName,result);
			return get();
		} catch (Exception e) {
			throw new RuntimeException("--计数器:"+counterName+"get异常",e);
		}
	}

}
