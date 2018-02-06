package com.hu.bo.config.redis;

import lombok.Data;

/**
 * redis连接池配置信息
 * @author japper
 *
 */
@Data
public class RedisPoolConfig {
	/**
	 * 最大空闲数量
	 */
	private int maxIdle=20;
	/**
	 * 最大链接数量
	 */
	private int maxTotal=300;
	/**
	 * 连接池资源不够时，最大等待时间
	 */
	private int maxWaitMillis=3000;
	/**
	 * 最小空闲连接
	 */
	private int minIdle=1;
	/**
	 * 创建连接是否需要测试链接有效
	 */
	private boolean testOnCreate=false;
	/**
	 * 返回连接是否需要测试链接有效
	 */
	private boolean testOnReturn=true;
	/**
	 * 连接server的超时时间
	 */
	private int connectionTimeout=3000;
	/**
	 * 读取响应的超时时间
	 */
	private int soTimeout=3000;
}
