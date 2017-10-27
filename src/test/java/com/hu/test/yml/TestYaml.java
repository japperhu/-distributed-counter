package com.hu.test.yml;

import com.hu.test.properties.AppProperties;
import com.hu.test.properties.ZKProperties;
import com.hu.util.SystemConfigManager;
import com.hu.util.YamlParser;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.util.Map;

/**
 * @author japper
 * 测试yaml文件解析
 */
public class TestYaml extends TestCase {
    private final Logger LOG= LoggerFactory.getLogger(TestYaml.class);

    /**
     * 测试加载单部分yaml文件信息
     * 这里加载的都作为hashMap
     */
    public void testSingleYamlLoad(){
        Yaml yaml=new Yaml();
      Object obj= yaml.load(TestYaml.class.getClassLoader().getResourceAsStream("app.yml"));
        LOG.info("---class type:{}",obj.getClass().getName());
        LOG.info("----content:{}",obj);
        printKeyValueAndType(obj);

    }

    /**
     *
     * 测试加载单部分yaml文件信息到对象中，前提是该对象必须有yml文件中的字段。如果没有某个字段则会抛出异�?
     * 把配置文件信息都加载到一个类中也不太友好�?
     *
     */
    public void testLoadYamlToBean(){
        Yaml yaml=new Yaml();
        AppProperties obj= yaml.loadAs(TestYaml.class.getClassLoader().getResourceAsStream("app.yml"), AppProperties.class);
        LOG.info("---class type:{}",obj.getClass().getName());
        LOG.info("----content:{}",obj);
        printKeyValueAndType(obj);
    }


    public void testLoadYamlSomeFieldToBean(){
        String url=TestYaml.class.getClassLoader().getResource("app.yml").toString();
            LOG.info("----file path:{}",url);//file:/D:/xxxx
        AppProperties obj= YamlParser.parseYmlToObject(AppProperties.class,new File(url.substring(6)));
        LOG.info("--AppProperties--content:{}",obj);
        ZKProperties zkProperties=YamlParser.parseYmlToObject(ZKProperties.class,new File(url.substring(6)));
        LOG.info("--ZKProperties--content:{}",zkProperties);
    }

    public void testDupLoadSysConfigBean(){
        ZKProperties a= SystemConfigManager.getSystemPropertiesBean(ZKProperties.class);
        ZKProperties b= SystemConfigManager.getSystemPropertiesBean(ZKProperties.class);
        LOG.info("a==b:{}",a==b);
        assertEquals(a==b,true);
    }


    private void printKeyValueAndType(Object obj){
        if(obj instanceof Map) {
            Map<String, Object> content = (Map) obj;
            for (Map.Entry<String, Object> entry : content.entrySet()) {
                printKeyValueAndType(entry);
            }
        }else{
            if(obj instanceof  Map.Entry){
                Map.Entry<String,Object> entry=(Map.Entry<String,Object>) obj;
                LOG.info("---key:{},value:{},type:{}", entry.getKey(), entry.getValue(), entry.getValue().getClass());
                printKeyValueAndType(entry.getValue());
            }

        }
    }

}
