package com.hu.util.redis;

import com.hu.util.redis.callback.IRedisClusterExecuteCallback;
import com.hu.util.redis.callback.IRedisExecuteCallback;

import redis.clients.jedis.JedisCommands;
/**
 * redis客户端接口
 * @author japper
 *
 */
public interface IRedisClient extends JedisCommands {
	/**
	 * redis singleton及sentinel模式 下的复杂操作可以使用该方式
	 * @param redisExecuteCallback 回调操作
	 */
  void execute(IRedisExecuteCallback redisExecuteCallback);
  	/**
  	 * redis cluster模式下的复杂操作可以使用该方式
  	 * @param redisClusterExecuteCallback 回调操作
  	 */
  void execute(IRedisClusterExecuteCallback redisClusterExecuteCallback);
  /**
   * 销毁客户端
   */
  void destory();
	
}
