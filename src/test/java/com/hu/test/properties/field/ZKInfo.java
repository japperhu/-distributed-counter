package com.hu.test.properties.field;

import lombok.Data;

import java.io.Serializable;

/**
 * @author japper
 * zk 信息
 *
 */
@Data
public class ZKInfo  implements Serializable{

    private int timeout;
    private String url;
}
