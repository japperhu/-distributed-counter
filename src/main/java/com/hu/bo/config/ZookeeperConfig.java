package com.hu.bo.config;

import com.hu.annotation.PropertiesPrefix;

import lombok.Data;
/**
 * zookeeper配置信息
 * @author japper
 *
 */
@Data
@PropertiesPrefix("zookeeper")
public class ZookeeperConfig {
	private int sessionTimeout;
    private String address;
}
