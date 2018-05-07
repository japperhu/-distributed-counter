package com.hu.test.properload.properties;

import com.hu.bo.config.RedisConfig;

import lombok.Data;

/**
 * 应用程序配置文件
 * @author japper
 *
 */
@Data
public class AppProperties {

private RedisConfig redis;
private String server_port;
private ZKProperties zookeeper;

}
