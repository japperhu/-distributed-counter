package com.hu.counter;
/**
 * counter
 * 
 * 先提供increment和set两个api，condtion时redis可以转义为lua脚本,zookeeper的最好使用分布式互斥锁了,hush Counter准备使用dsl在服务器上支持原子执行
 * @author japper
 *
 */
public interface Counter {
	
	/**
	 * 
	 * @return
	 */
	long increment();
	/**
	 * 自增一定值
	 * @param incrementNum 自增量
	 * @return 自增后的结果
	 */
	long increment(long incrementNum);
	/**
	 * 设置为一个值
	 * @param num 设置的值
	 */
	void set(long num);
	/**
	 * 获得当前计数器的值,不存在将返回null
	 * @return
	 */
	Long get();
	

}
