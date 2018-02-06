package com.hu.counter;
/**
 * counter
 * 
 * 先提供increment和set两个api
 * @author japper
 *
 */
public interface Counter {
	
	/**
	 * 自增一定值
	 * @param num 自增量
	 * @return 自增后的结果
	 */
	long increment(long num);
	/**
	 * 设置为一个值
	 * @param num 设置的值
	 */
	void set(long num);
	/**
	 * 获得当前计数器的值
	 * @return
	 */
	long get();
	

}
