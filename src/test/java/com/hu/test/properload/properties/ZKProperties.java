package com.hu.test.properload.properties;

import com.hu.annotation.PropertiesPrefix;
import lombok.Data;

import java.io.Serializable;

/**
 * @author japper
 * zk配置文件
 */
@Data
@PropertiesPrefix("zookeeper")
public class ZKProperties implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4268352541385307800L;
	private int sessionTimeout;
    private String address;
}
