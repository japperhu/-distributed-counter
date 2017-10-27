package com.hu.test.properties;

import com.hu.test.properties.field.JedisPool;
import lombok.Data;

/**
 * @author japper
 */
@Data
public class JedisProperties {
    private String host;
    private int port;
    private int timeout;
    private String password;
    private JedisPool pool;
}
