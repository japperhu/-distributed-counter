package com.hu.test.properties;

import com.hu.annotation.PropertiesPrefix;
import com.hu.test.properties.field.ZKInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @author japper
 * zk配置文件
 */
@Data
@PropertiesPrefix("zookeeper")
public class ZKProperties implements Serializable{
    private String nodeName;
    private ZKInfo service;
}
