package com.hu.bo.config.redis;

import lombok.Data;

/**
 * redis sentinel配置信息
 * @author japper
 *
 */
@Data
public class RedisSentinelConfig {
	/**
	 * redis sentinel模式中的master名称
	 */
	private String masterName;
	

}
