package com.hu.util.redis.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.hu.util.redis.IRedisClient;
import com.hu.util.redis.callback.IRedisClusterExecuteCallback;
import com.hu.util.redis.callback.IRedisExecuteCallback;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.params.geo.GeoRadiusParam;
import redis.clients.jedis.params.sortedset.ZAddParams;
import redis.clients.jedis.params.sortedset.ZIncrByParams;
/**
 * redis 哨兵模式客户端
 * @author japper
 *
 */
@SuppressWarnings("deprecation")
public class RedisSentinelClient implements JedisCommands,IRedisClient{
	
	private JedisSentinelPool jedisSentinelPool;
	
	public RedisSentinelClient(JedisSentinelPool jedisSentinelPool) {
		this.jedisSentinelPool=jedisSentinelPool;
	}
	@Override
	public String set(String key, String value) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.set(key,value);
		}
	}

	@Override
	public String set(String key, String value, String nxxx, String expx, long time) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.set(key,value,nxxx,expx,time);
		}
	}

	@Override
	public String set(String key, String value, String nxxx) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.set(key,value,nxxx);
		}
	}

	@Override
	public String get(String key) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.get(key);
		}
	}

	@Override
	public Boolean exists(String key) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.exists(key);
		}
	}

	@Override
	public Long persist(String key) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.persist(key);
		}
	}

	@Override
	public String type(String key) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.type(key);
		}
	}

	@Override
	public Long expire(String key, int seconds) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.expire(key,seconds);
		}
	}

	@Override
	public Long pexpire(String key, long milliseconds) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.pexpire(key,milliseconds);
		}
	}

	@Override
	public Long expireAt(String key, long unixTime) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.expireAt(key,unixTime);
		}
	}

	@Override
	public Long pexpireAt(String key, long millisecondsTimestamp) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.pexpireAt(key,millisecondsTimestamp);
		}
	}

	@Override
	public Long ttl(String key) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.ttl(key);
		}
	}

	@Override
	public Long pttl(String key) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.pttl(key);
		}
	}

	@Override
	public Boolean setbit(String key, long offset, boolean value) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.setbit(key,offset,value);
		}
	}

	@Override
	public Boolean setbit(String key, long offset, String value) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.setbit(key,offset,value);
		}
	}

	@Override
	public Boolean getbit(String key, long offset) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.getbit(key,offset);
		}
	}

	@Override
	public Long setrange(String key, long offset, String value) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.setrange(key,offset,value);
		}
	}

	@Override
	public String getrange(String key, long startOffset, long endOffset) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.getrange(key,startOffset,endOffset);
		}
	}

	@Override
	public String getSet(String key, String value) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.getSet(key,value);
		}
	}

	@Override
	public Long setnx(String key, String value) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.setnx(key,value);
		}
	}

	@Override
	public String setex(String key, int seconds, String value) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.setex(key,seconds,value);
		}
	}

	@Override
	public String psetex(String key, long milliseconds, String value) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.psetex(key,milliseconds,value);
		}
	}

	@Override
	public Long decrBy(String key, long integer) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.decrBy(key,integer);
		}
	}

	@Override
	public Long decr(String key) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.decr(key);
		}
	}

	@Override
	public Long incrBy(String key, long integer) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.incrBy(key,integer);
		}
	}

	@Override
	public Double incrByFloat(String key, double value) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.incrByFloat(key,value);
		}
	}

	@Override
	public Long incr(String key) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.incr(key);
		}
	}

	@Override
	public Long append(String key, String value) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.append(key,value);
		}
	}

	@Override
	public String substr(String key, int start, int end) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.substr(key,start,end);
		}
	}

	@Override
	public Long hset(String key, String field, String value) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.hset(key,field,value);
		}
	}

	@Override
	public String hget(String key, String field) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.hget(key,field);
		}
	}

	@Override
	public Long hsetnx(String key, String field, String value) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.hsetnx(key,field,value);
		}
	}

	@Override
	public String hmset(String key, Map<String,String> hash) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.hmset(key,hash);
		}
	}

	@Override
	public List<String> hmget(String key, String... fields) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.hmget(key,fields);
		}
	}

	@Override
	public Long hincrBy(String key, String field, long value) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.hincrBy(key,field,value);
		}
	}

	@Override
	public Double hincrByFloat(String key, String field, double value) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.hincrByFloat(key,field,value);
		}
	}

	@Override
	public Boolean hexists(String key, String field) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.hexists(key,field);
		}
	}

	@Override
	public Long hdel(String key, String... field) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.hdel(key,field);
		}
	}

	@Override
	public Long hlen(String key) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.hlen(key);
		}
	}

	@Override
	public Set<String> hkeys(String key) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.hkeys(key);
		}
	}

	@Override
	public List<String> hvals(String key) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.hvals(key);
		}
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.hgetAll(key);
		}
	}

	@Override
	public Long rpush(String key, String... string) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.rpush(key,string);
		}
	}

	@Override
	public Long lpush(String key, String... string) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.lpush(key,string);
		}
	}

	@Override
	public Long llen(String key) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.llen(key);
		}
	}

	@Override
	public List<String> lrange(String key, long start, long end) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.lrange(key,start,end);
		}
	}

	@Override
	public String ltrim(String key, long start, long end) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.ltrim(key,start,end);
		}
	}

	@Override
	public String lindex(String key, long index) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.lindex(key,index);
		}
	}

	@Override
	public String lset(String key, long index, String value) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.lset(key,index,value);
		}
	}

	@Override
	public Long lrem(String key, long count, String value) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.lrem(key,count,value);
		}
	}

	@Override
	public String lpop(String key) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.lpop(key);
		}
	}

	@Override
	public String rpop(String key) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.rpop(key);
		}
	}

	@Override
	public Long sadd(String key, String... member) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.sadd(key,member);
		}
	}

	@Override
	public Set<String> smembers(String key) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.smembers(key);
		}
	}

	@Override
	public Long srem(String key, String... member) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.srem(key,member);
		}
	}

	@Override
	public String spop(String key) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.spop(key);
		}
	}

	@Override
	public Set<String> spop(String key, long count) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.spop(key,count);
		}
	}

	@Override
	public Long scard(String key) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.scard(key);
		}
	}

	@Override
	public Boolean sismember(String key, String member) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.sismember(key,member);
		}
	}

	@Override
	public String srandmember(String key) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.srandmember(key);
		}
	}

	@Override
	public List<String> srandmember(String key, int count) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.srandmember(key,count);
		}
	}

	@Override
	public Long strlen(String key) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.strlen(key);
		}
	}

	@Override
	public Long zadd(String key, double score, String member) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zadd(key,score,member);
		}
	}

	@Override
	public Long zadd(String key, double score, String member, ZAddParams params) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zadd(key,score,member,params);
		}
	}

	@Override
	public Long zadd(String key, Map<String,Double> scoreMembers) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zadd(key,scoreMembers);
		}
	}

	@Override
	public Long zadd(String key, Map<String,Double> scoreMembers, ZAddParams params) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zadd(key,scoreMembers,params);
		}
	}

	@Override
	public Set<String> zrange(String key, long start, long end) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zrange(key,start,end);
		}
	}

	@Override
	public Long zrem(String key, String... member) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zrem(key,member);
		}
	}

	@Override
	public Double zincrby(String key, double score, String member) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zincrby(key,score,member);
		}
	}

	@Override
	public Double zincrby(String key, double score, String member, ZIncrByParams params) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zincrby(key,score,member,params);
		}
	}

	@Override
	public Long zrank(String key, String member) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zrank(key,member);
		}
	}

	@Override
	public Long zrevrank(String key, String member) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zrevrank(key,member);
		}
	}

	@Override
	public Set<String> zrevrange(String key, long start, long end) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zrevrange(key,start,end);
		}
	}

	@Override
	public Set<Tuple> zrangeWithScores(String key, long start, long end) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zrangeWithScores(key,start,end);
		}
	}

	@Override
	public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zrevrangeWithScores(key,start,end);
		}
	}

	@Override
	public Long zcard(String key) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zcard(key);
		}
	}

	@Override
	public Double zscore(String key, String member) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zscore(key,member);
		}
	}

	@Override
	public List<String> sort(String key) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.sort(key);
		}
	}

	@Override
	public List<String> sort(String key, SortingParams sortingParameters) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.sort(key,sortingParameters);
		}
	}

	@Override
	public Long zcount(String key, double min, double max) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zcount(key,min,max);
		}
	}

	@Override
	public Long zcount(String key, String min, String max) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zcount(key,min,max);
		}
	}

	@Override
	public Set<String> zrangeByScore(String key, double min, double max) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zrangeByScore(key,min,max);
		}
	}

	@Override
	public Set<String> zrangeByScore(String key, String min, String max) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zrangeByScore(key,min,max);
		}
	}

	@Override
	public Set<String> zrevrangeByScore(String key, double max, double min) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zrevrangeByScore(key,max,min);
		}
	}

	@Override
	public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zrangeByScore(key,min,max,offset,count);
		}
	}

	@Override
	public Set<String> zrevrangeByScore(String key, String max, String min) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zrevrangeByScore(key,max,min);
		}
	}

	@Override
	public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zrangeByScore(key,min,max,offset,count);
		}
	}

	@Override
	public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zrevrangeByScore(key,max,min,offset,count);
		}
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zrangeByScoreWithScores(key,min,max);
		}
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zrevrangeByScoreWithScores(key,max,min);
		}
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zrangeByScoreWithScores(key,min,max,offset,count);
		}
	}

	@Override
	public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zrevrangeByScore(key,max,min,offset,count);
		}
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zrangeByScoreWithScores(key,min,max);
		}
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zrevrangeByScoreWithScores(key,max,min);
		}
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zrangeByScoreWithScores(key,min,max,offset,count);
		}
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zrevrangeByScoreWithScores(key,max,min,offset,count);
		}
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zrevrangeByScoreWithScores(key,max,min,offset,count);
		}
	}

	@Override
	public Long zremrangeByRank(String key, long start, long end) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zremrangeByRank(key,start,end);
		}
	}

	@Override
	public Long zremrangeByScore(String key, double start, double end) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zremrangeByScore(key,start,end);
		}
	}

	@Override
	public Long zremrangeByScore(String key, String start, String end) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zremrangeByScore(key,start,end);
		}
	}

	@Override
	public Long zlexcount(String key, String min, String max) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zlexcount(key,min,max);
		}
	}

	@Override
	public Set<String> zrangeByLex(String key, String min, String max) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zrangeByLex(key,min,max);
		}
	}

	@Override
	public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zrangeByLex(key,min,max,offset,count);
		}
	}

	@Override
	public Set<String> zrevrangeByLex(String key, String max, String min) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zrevrangeByLex(key,max,min);
		}
	}

	@Override
	public Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zrevrangeByLex(key,max,min,offset,count);
		}
	}

	@Override
	public Long zremrangeByLex(String key, String min, String max) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zremrangeByLex(key,min,max);
		}
	}

	@Override
	public Long linsert(String key, LIST_POSITION where, String pivot, String value) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.linsert(key,where,pivot,value);
		}
	}

	@Override
	public Long lpushx(String key, String... string) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.lpushx(key,string);
		}
	}

	@Override
	public Long rpushx(String key, String... string) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.rpushx(key,string);
		}
	}

	@Override
	public List<String> blpop(String arg) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.blpop(arg);
		}
	}

	@Override
	public List<String> blpop(int timeout, String key) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.blpop(timeout,key);
		}
	}

	@Override
	public List<String> brpop(String arg) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.brpop(arg);
		}
	}

	@Override
	public List<String> brpop(int timeout, String key) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.brpop(timeout,key);
		}
	}

	@Override
	public Long del(String key) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.del(key);
		}
	}

	@Override
	public String echo(String string) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.echo(string);
		}
	}

	@Override
	public Long move(String key, int dbIndex) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.move(key,dbIndex);
		}
	}

	@Override
	public Long bitcount(String key) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.bitcount(key);
		}
	}

	@Override
	public Long bitcount(String key, long start, long end) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.bitcount(key,start,end);
		}
	}

	@Override
	public Long bitpos(String key, boolean value) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.bitpos(key,value);
		}
	}

	@Override
	public Long bitpos(String key, boolean value, BitPosParams params) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.bitpos(key,value,params);
		}
	}

	@Override
	public ScanResult<Entry<String, String>> hscan(String key, int cursor) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.hscan(key,cursor);
		}
	}

	@Override
	public ScanResult<String> sscan(String key, int cursor) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.sscan(key,cursor);
		}
	}

	@Override
	public ScanResult<Tuple> zscan(String key, int cursor) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zscan(key,cursor);
		}
	}

	@Override
	public ScanResult<Entry<String, String>> hscan(String key, String cursor) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.hscan(key,cursor);
		}
	}

	@Override
	public ScanResult<Entry<String, String>> hscan(String key, String cursor, ScanParams params) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.hscan(key,cursor,params);
		}
	}

	@Override
	public ScanResult<String> sscan(String key, String cursor) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.sscan(key,cursor);
		}
	}

	@Override
	public ScanResult<String> sscan(String key, String cursor, ScanParams params) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.sscan(key,cursor,params);
		}
	}

	@Override
	public ScanResult<Tuple> zscan(String key, String cursor) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zscan(key,cursor);
		}
	}

	@Override
	public ScanResult<Tuple> zscan(String key, String cursor, ScanParams params) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.zscan(key,cursor,params);
		}
	}

	@Override
	public Long pfadd(String key, String... elements) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.pfadd(key,elements);
		}
	}

	@Override
	public long pfcount(String key) {
		
		return 0;
	}

	@Override
	public Long geoadd(String key, double longitude, double latitude, String member) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.geoadd(key,longitude,latitude,member);
		}
	}

	@Override
	public Long geoadd(String key, Map<String,GeoCoordinate> memberCoordinateMap) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.geoadd(key,memberCoordinateMap);
		}
	}

	@Override
	public Double geodist(String key, String member1, String member2) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.geodist(key,member1,member2);
		}
	}

	@Override
	public Double geodist(String key, String member1, String member2, GeoUnit unit) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.geodist(key,member1,member2,unit);
		}
	}

	@Override
	public List<String> geohash(String key, String... members) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.geohash(key,members);
		}
	}

	@Override
	public List<GeoCoordinate> geopos(String key, String... members) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.geopos(key,members);
		}
	}

	@Override
	public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.georadius(key,longitude,latitude,radius,unit);
		}
	}

	@Override
	public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.georadius(key,longitude,latitude,radius,unit,param);
		}
	}

	@Override
	public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.georadiusByMember(key,member,radius,unit);
		}
	}

	@Override
	public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit, GeoRadiusParam param) {
		
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.georadiusByMember(key,member,radius,unit,param);
		}
	}

	@Override
	public List<Long> bitfield(String key, String... arguments) {
		try(Jedis jedis=jedisSentinelPool.getResource()) {
		return jedis.bitfield(key,arguments);
		}
	}


	@Override
	public void execute(IRedisExecuteCallback redisExecuteCallback) {
		try(Jedis jedis=jedisSentinelPool.getResource()) {
			redisExecuteCallback.action(jedis);
		}
	}

	@Override
	public void execute(IRedisClusterExecuteCallback redisClusterExecuteCallback) {
		throw new UnsupportedOperationException("sentinel模式不支持cluster操作");
	}
	
	public void destory() {
		if(jedisSentinelPool!=null) {
			jedisSentinelPool.close();
			jedisSentinelPool.destroy();
		}
	}

	
	
	
}
