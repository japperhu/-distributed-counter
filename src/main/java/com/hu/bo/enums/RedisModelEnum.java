package com.hu.bo.enums;
/**
 * redis模式枚举
 * @author japper
 *
 */
public enum RedisModelEnum {
	/**
	 * 单实例运行模式
	 */
	SINGLETON("singleton"),
	/**
	 * 哨兵模式
	 */
	SENTINEL("sentinel"),
	/**
	 * 集群模式
	 */
	CLUSTER("cluster");
	
	private String type;
	
	private RedisModelEnum(String type) {
		this.type=type;
	}
	
	public String getType() {
		return type;
	}
	/**
	 * 根据type获取redisModelEnum
	 * @param type redis模式类型
	 * @return
	 */
	public static RedisModelEnum getRedisModelByType(String type) {
		for(RedisModelEnum redisModel:values()){
			if(redisModel.getType().equals(type)) {
				return redisModel;
			}
		}
		return null;
	}
	
	
}
