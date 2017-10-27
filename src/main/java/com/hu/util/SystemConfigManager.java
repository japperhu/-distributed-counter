package com.hu.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author japper
 * 系统配置信息管理
 */
public class SystemConfigManager {
    private static final Logger LOG= LoggerFactory.getLogger(SystemConfigManager.class);
    private static final Map<String,Object> sysConfigStore=new HashMap<>();

    /**
     * 获取系统配置参数对象
     * @param clazz
     * @param <T>
     * @return
     */
    public  static <T>  T getSystemPropertiesBean(Class<T> clazz){
        try {
            if (sysConfigStore.containsKey(clazz.getName())) {
                return (T) sysConfigStore.get(clazz.getName());
            } else {
                String url = SystemConfigManager.class.getClassLoader().getResource(Constants.SYS_CONFIG_FILE_NAME).toString();
                url = URLDecoder.decode(url, Constants.SYS_CHARSET_NAME).substring(6);// 去掉协议前缀file:/
                T propertiesBean=YamlParser.parseYmlToObject(clazz, new File(url));
                sysConfigStore.put(clazz.getName(),propertiesBean);
                return propertiesBean;
            }
        }catch (Exception e){
            LOG.info("----获取系统配置信息对象异常",e);
        }
        return null;
    }

}
