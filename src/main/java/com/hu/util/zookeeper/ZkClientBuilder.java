package com.hu.util.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import com.hu.bo.config.ZookeeperConfig;
import com.hu.util.SystemConfigManager;
/**
 * zookeeper 客户端构建
 * @author japper
 *
 */
public class ZkClientBuilder {

	/**
	 * 构建curator客户端
	 * @param retryTimes
	 * @param retryTimeInterval
	 * @return
	 */
	public static CuratorFramework buildeCuratorFramework(int retryTimes,int retryTimeInterval) {
		ZookeeperConfig zookeeperConfig = SystemConfigManager.getSystemPropertiesBean(ZookeeperConfig.class);
		//创建curator客户端
		CuratorFramework client = CuratorFrameworkFactory.newClient(zookeeperConfig.getAddress(), new ExponentialBackoffRetry(retryTimeInterval,retryTimes));
		return client;
	}
}
