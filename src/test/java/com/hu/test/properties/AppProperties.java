package com.hu.test.properties;

import lombok.Data;

/**
 * 应用程序配置文件
 * @author japper
 *
 */
@Data
public class AppProperties {

private JedisProperties jedis;
private String server_port;
private ZKProperties zookeeper;

}
