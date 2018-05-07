package com.hu.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import com.hu.annotation.PropertiesPrefix;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * yaml文件解析
 * @author japper
 *
 */
public class YamlParser {
    private static final Logger LOG=LoggerFactory.getLogger(YamlParser.class);

    /**
     * 将yaml文件内容设置到java bean中
     * @param clazz
     * @param fis
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T>  T  parseYmlToObject(Class<T> clazz, InputStream fis){
        try{
            Yaml yaml=new Yaml();
            if(clazz.isAnnotationPresent(PropertiesPrefix.class)){ //加载指定的配置项到对象中
                Map<String,Object> properties=(Map<String, Object>) yaml.load(fis);
                PropertiesPrefix propertiesPrefix=clazz.getAnnotation(PropertiesPrefix.class);
                //设置信息到对象字段中
               Map<String,Object> value=(Map<String,Object>) properties.get(propertiesPrefix.value());
               T targetBean=clazz.newInstance();
               for(Map.Entry<String,Object> entry:value.entrySet()){
                   setFieldToBean(entry.getValue(),entry.getKey(),targetBean);
               }
                return targetBean;
            }else{//加载所有字段到对象中
                return yaml.loadAs(fis,clazz);
            }
        }catch (Exception e){
        LOG.error("-------parseYmlToObject error",e);
        return null;
        }finally {
        	if(fis!=null) {
        		try {
					fis.close();
				} catch (IOException e) {
					 LOG.error("-------parseYmlToObject error",e);
				}
        	}
        }
    }
    /**
     * 将yaml文件内容设置到java bean中
     * @param clazz
     * @param file
     * @param <T>
     * @return
     */
    public static <T>  T  parseYmlToObject(Class<T> clazz, File file) {
    		try {
    		FileInputStream fis=new FileInputStream(file);
    		return parseYmlToObject(clazz,fis);
    		}catch (Exception e){
    	        LOG.error("-------parseYmlToObject error",e);
    	        return null;
    	    }
    }

    /**
     * 设置字段到javaBean中
     * @param fieldVal 属性字段内容
     * @param sourceBean javaBean
     */
    @SuppressWarnings("unchecked")
    private static void setFieldToBean(Object fieldVal,String fieldKey,Object sourceBean) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
    		fieldKey=replaceSpecialCharToUpWord(fieldKey,'-','_');
    	    if(fieldVal instanceof Map){
                Map<String,Object> fields=(Map<String,Object>)fieldVal;
                Field objField=sourceBean.getClass().getDeclaredField(fieldKey);
                objField.setAccessible(true);
                Object sourceFieldBean= objField.getType().newInstance();
                for(Map.Entry<String,Object> entry:fields.entrySet()){
                    setFieldToBean(entry.getValue(),entry.getKey(),sourceFieldBean);
                }
                objField.set(sourceBean,sourceFieldBean);
            }else{
               Field field=sourceBean.getClass().getDeclaredField(fieldKey);
               field.setAccessible(true);
               field.set(sourceBean,fieldVal);
            }
    }

    private static String replaceSpecialCharToUpWord(String fieldKey,char... specialChars){
        for(char specialChar:specialChars) {
            fieldKey=replaceSpecialCharToUpWord(fieldKey,specialChar);
        }
        return fieldKey;
    }
    private static String replaceSpecialCharToUpWord(String fieldKey,char specialChar){
        StringBuilder sb=new StringBuilder(fieldKey);
        int index=sb.indexOf(String.valueOf(specialChar));
        while(index!=-1){
            sb.replace(index,index+2,String.valueOf(sb.charAt(index+1)).toUpperCase());
            index=sb.indexOf(String.valueOf(specialChar));
        }
        return sb.toString();
    }

}
