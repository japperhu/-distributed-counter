package com.hu.bo.config.redis;

import lombok.Data;

/**
 * redis 集群配置信息
 * @author japper
 *
 */
@Data
public class RedisClusterConfig {
	private int maxAttempts;
}
