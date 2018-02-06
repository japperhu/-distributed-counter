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
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.params.geo.GeoRadiusParam;
import redis.clients.jedis.params.sortedset.ZAddParams;
import redis.clients.jedis.params.sortedset.ZIncrByParams;
/**
 * redis集群客户端
 * @author japper
 *
 */
public class RedisClusterClient implements JedisCommands,IRedisClient {
	/**
	 * redis集群操作
	 */
	private JedisCluster jedisCluster;
	
	public RedisClusterClient(JedisCluster jedisCluster) {
		this.jedisCluster=jedisCluster;
	}
	
	@Override
	public String set(String key, String value) {
		return jedisCluster.set(key, value);
	}

	@Override
	public String set(String key, String value, String nxxx, String expx, long time) {
		return jedisCluster.set(key, value, nxxx, expx, time);
	}

	@Override
	public String set(String key, String value, String nxxx) {
		return jedisCluster.set(key, value, nxxx);
	}

	@Override
	public String get(String key) {
		return jedisCluster.get(key);
	}

	@Override
	public Boolean exists(String key) {
		return jedisCluster.exists(key);
	}

	@Override
	public Long persist(String key) {
		return jedisCluster.persist(key);
	}

	@Override
	public String type(String key) {
		return jedisCluster.type(key);
	}

	@Override
	public Long expire(String key, int seconds) {
		return jedisCluster.expire(key, seconds);
	}

	@Override
	public Long pexpire(String key, long milliseconds) {
		return jedisCluster.pexpire(key, milliseconds);
	}

	@Override
	public Long expireAt(String key, long unixTime) {
		return jedisCluster.expireAt(key, unixTime);
	}

	@Override
	public Long pexpireAt(String key, long millisecondsTimestamp) {
		return jedisCluster.pexpireAt(key, millisecondsTimestamp);
	}

	@Override
	public Long ttl(String key) {
		return jedisCluster.ttl(key);
	}

	@Override
	public Long pttl(String key) {
		return jedisCluster.pttl(key);
	}

	@Override
	public Boolean setbit(String key, long offset, boolean value) {
		return jedisCluster.setbit(key, offset, value);
	}

	@Override
	public Boolean setbit(String key, long offset, String value) {
		return jedisCluster.setbit(key, offset, value);
	}

	@Override
	public Boolean getbit(String key, long offset) {
		return jedisCluster.getbit(key, offset);
	}

	@Override
	public Long setrange(String key, long offset, String value) {
		return jedisCluster.setrange(key, offset, value);
	}

	@Override
	public String getrange(String key, long startOffset, long endOffset) {
		return jedisCluster.getrange(key, startOffset, endOffset);
	}

	@Override
	public String getSet(String key, String value) {
		return jedisCluster.getSet(key, value);
	}

	@Override
	public Long setnx(String key, String value) {
		return jedisCluster.setnx(key, value);
	}

	@Override
	public String setex(String key, int seconds, String value) {
		return jedisCluster.setex(key, seconds, value);
	}

	@Override
	public String psetex(String key, long milliseconds, String value) {
		return jedisCluster.psetex(key, milliseconds, value);
	}

	@Override
	public Long decrBy(String key, long integer) {
		return jedisCluster.decrBy(key, integer);
	}

	@Override
	public Long decr(String key) {
		return jedisCluster.decr(key);
	}

	@Override
	public Long incrBy(String key, long integer) {
		return jedisCluster.incrBy(key, integer);
	}

	@Override
	public Double incrByFloat(String key, double value) {
		return jedisCluster.incrByFloat(key, value);
	}

	@Override
	public Long incr(String key) {
		return jedisCluster.incr(key);
	}

	@Override
	public Long append(String key, String value) {
		return jedisCluster.append(key, value);
	}

	@Override
	public String substr(String key, int start, int end) {
		return jedisCluster.substr(key, start, end);
	}

	@Override
	public Long hset(String key, String field, String value) {
		return jedisCluster.hset(key, field, value);
	}

	@Override
	public String hget(String key, String field) {
		return jedisCluster.hget(key, field);
	}

	@Override
	public Long hsetnx(String key, String field, String value) {
		return jedisCluster.hsetnx(key, field, value);
	}

	@Override
	public String hmset(String key, Map<String, String> hash) {
		return jedisCluster.hmset(key, hash);
	}

	@Override
	public List<String> hmget(String key, String... fields) {
		return jedisCluster.hmget(key, fields);
	}

	@Override
	public Long hincrBy(String key, String field, long value) {
		return jedisCluster.hincrBy(key, field, value);
	}

	@Override
	public Double hincrByFloat(String key, String field, double value) {
		return jedisCluster.hincrByFloat(key, field, value);
	}

	@Override
	public Boolean hexists(String key, String field) {
		return jedisCluster.hexists(key, field);
	}

	@Override
	public Long hdel(String key, String... field) {
		return jedisCluster.hdel(key, field);
	}

	@Override
	public Long hlen(String key) {
		return jedisCluster.hlen(key);
	}

	@Override
	public Set<String> hkeys(String key) {
		return jedisCluster.hkeys(key);
	}

	@Override
	public List<String> hvals(String key) {
		return jedisCluster.hvals(key);
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		return jedisCluster.hgetAll(key);
	}

	@Override
	public Long rpush(String key, String... string) {
		return jedisCluster.rpush(key, string);
	}

	@Override
	public Long lpush(String key, String... string) {
		return jedisCluster.lpush(key, string);
	}

	@Override
	public Long llen(String key) {
		return jedisCluster.llen(key);
	}

	@Override
	public List<String> lrange(String key, long start, long end) {
		return jedisCluster.lrange(key, start, end);
	}

	@Override
	public String ltrim(String key, long start, long end) {
		return jedisCluster.ltrim(key, start, end);
	}

	@Override
	public String lindex(String key, long index) {
		return jedisCluster.lindex(key, index);
	}

	@Override
	public String lset(String key, long index, String value) {
		return jedisCluster.lset(key, index, value);
	}

	@Override
	public Long lrem(String key, long count, String value) {
		return jedisCluster.lrem(key, count, value);
	}

	@Override
	public String lpop(String key) {
		return jedisCluster.lpop(key);
	}

	@Override
	public String rpop(String key) {
		return jedisCluster.rpop(key);
	}

	@Override
	public Long sadd(String key, String... member) {
		return jedisCluster.sadd(key, member);
	}

	@Override
	public Set<String> smembers(String key) {
		return jedisCluster.smembers(key);
	}

	@Override
	public Long srem(String key, String... member) {
		return jedisCluster.srem(key, member);
	}

	@Override
	public String spop(String key) {
		return jedisCluster.spop(key);
	}

	@Override
	public Set<String> spop(String key, long count) {
		return jedisCluster.spop(key, count);
	}

	@Override
	public Long scard(String key) {
		return jedisCluster.scard(key);
	}

	@Override
	public Boolean sismember(String key, String member) {
		return jedisCluster.sismember(key, member);
	}

	@Override
	public String srandmember(String key) {
		return jedisCluster.srandmember(key);
	}

	@Override
	public List<String> srandmember(String key, int count) {
		return jedisCluster.srandmember(key, count);
	}

	@Override
	public Long strlen(String key) {
		return jedisCluster.strlen(key);
	}

	@Override
	public Long zadd(String key, double score, String member) {
		return jedisCluster.zadd(key, score,member);
	}

	@Override
	public Long zadd(String key, double score, String member, ZAddParams params) {
		return jedisCluster.zadd(key, score,member,params);
	}

	@Override
	public Long zadd(String key, Map<String, Double> scoreMembers) {
		return jedisCluster.zadd(key, scoreMembers);
	}

	@Override
	public Long zadd(String key, Map<String, Double> scoreMembers, ZAddParams params) {
		return jedisCluster.zadd(key, scoreMembers,params);
	}

	@Override
	public Set<String> zrange(String key, long start, long end) {
		return jedisCluster.zrange(key, start, end);
	}

	@Override
	public Long zrem(String key, String... member) {
		return jedisCluster.zrem(key, member);
	}

	@Override
	public Double zincrby(String key, double score, String member) {
		return jedisCluster.zincrby(key, score, member);
	}

	@Override
	public Double zincrby(String key, double score, String member, ZIncrByParams params) {
		
		return null;
	}

	@Override
	public Long zrank(String key, String member) {
		
		return null;
	}

	@Override
	public Long zrevrank(String key, String member) {
		
		return null;
	}

	@Override
	public Set<String> zrevrange(String key, long start, long end) {
		
		return null;
	}

	@Override
	public Set<Tuple> zrangeWithScores(String key, long start, long end) {
		
		return null;
	}

	@Override
	public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
		
		return null;
	}

	@Override
	public Long zcard(String key) {
		
		return null;
	}

	@Override
	public Double zscore(String key, String member) {
		
		return null;
	}

	@Override
	public List<String> sort(String key) {
		
		return null;
	}

	@Override
	public List<String> sort(String key, SortingParams sortingParameters) {
		
		return null;
	}

	@Override
	public Long zcount(String key, double min, double max) {
		
		return null;
	}

	@Override
	public Long zcount(String key, String min, String max) {
		
		return null;
	}

	@Override
	public Set<String> zrangeByScore(String key, double min, double max) {
		
		return null;
	}

	@Override
	public Set<String> zrangeByScore(String key, String min, String max) {
		
		return null;
	}

	@Override
	public Set<String> zrevrangeByScore(String key, double max, double min) {
		
		return null;
	}

	@Override
	public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
		
		return null;
	}

	@Override
	public Set<String> zrevrangeByScore(String key, String max, String min) {
		
		return null;
	}

	@Override
	public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
		
		return null;
	}

	@Override
	public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
		
		return null;
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
		
		return null;
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
		
		return null;
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
		
		return null;
	}

	@Override
	public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
		
		return null;
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
		
		return null;
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
		
		return null;
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
		
		return null;
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
		
		return null;
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
		
		return null;
	}

	@Override
	public Long zremrangeByRank(String key, long start, long end) {
		
		return null;
	}

	@Override
	public Long zremrangeByScore(String key, double start, double end) {
		
		return null;
	}

	@Override
	public Long zremrangeByScore(String key, String start, String end) {
		
		return null;
	}

	@Override
	public Long zlexcount(String key, String min, String max) {
		
		return null;
	}

	@Override
	public Set<String> zrangeByLex(String key, String min, String max) {
		
		return null;
	}

	@Override
	public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
		
		return null;
	}

	@Override
	public Set<String> zrevrangeByLex(String key, String max, String min) {
		
		return null;
	}

	@Override
	public Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
		
		return null;
	}

	@Override
	public Long zremrangeByLex(String key, String min, String max) {
		
		return null;
	}

	@Override
	public Long linsert(String key, LIST_POSITION where, String pivot, String value) {
		
		return null;
	}

	@Override
	public Long lpushx(String key, String... string) {
		
		return null;
	}

	@Override
	public Long rpushx(String key, String... string) {
		
		return null;
	}

	@Override
	public List<String> blpop(String arg) {
		
		return null;
	}

	@Override
	public List<String> blpop(int timeout, String key) {
		
		return null;
	}

	@Override
	public List<String> brpop(String arg) {
		
		return null;
	}

	@Override
	public List<String> brpop(int timeout, String key) {
		
		return null;
	}

	@Override
	public Long del(String key) {
		
		return null;
	}

	@Override
	public String echo(String string) {
		
		return null;
	}

	@Override
	public Long move(String key, int dbIndex) {
		
		return null;
	}

	@Override
	public Long bitcount(String key) {
		
		return null;
	}

	@Override
	public Long bitcount(String key, long start, long end) {
		
		return null;
	}

	@Override
	public Long bitpos(String key, boolean value) {
		
		return null;
	}

	@Override
	public Long bitpos(String key, boolean value, BitPosParams params) {
		
		return null;
	}

	@Override
	public ScanResult<Entry<String, String>> hscan(String key, int cursor) {
		
		return null;
	}

	@Override
	public ScanResult<String> sscan(String key, int cursor) {
		
		return null;
	}

	@Override
	public ScanResult<Tuple> zscan(String key, int cursor) {
		
		return null;
	}

	@Override
	public ScanResult<Entry<String, String>> hscan(String key, String cursor) {
		
		return null;
	}

	@Override
	public ScanResult<Entry<String, String>> hscan(String key, String cursor, ScanParams params) {
		
		return null;
	}

	@Override
	public ScanResult<String> sscan(String key, String cursor) {
		
		return null;
	}

	@Override
	public ScanResult<String> sscan(String key, String cursor, ScanParams params) {
		
		return null;
	}

	@Override
	public ScanResult<Tuple> zscan(String key, String cursor) {
		
		return null;
	}

	@Override
	public ScanResult<Tuple> zscan(String key, String cursor, ScanParams params) {
		
		return null;
	}

	@Override
	public Long pfadd(String key, String... elements) {
		
		return null;
	}

	@Override
	public long pfcount(String key) {
		
		return 0;
	}

	@Override
	public Long geoadd(String key, double longitude, double latitude, String member) {
		
		return null;
	}

	@Override
	public Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
		
		return null;
	}

	@Override
	public Double geodist(String key, String member1, String member2) {
		
		return null;
	}

	@Override
	public Double geodist(String key, String member1, String member2, GeoUnit unit) {
		
		return null;
	}

	@Override
	public List<String> geohash(String key, String... members) {
		
		return null;
	}

	@Override
	public List<GeoCoordinate> geopos(String key, String... members) {
		
		return null;
	}

	@Override
	public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius,
			GeoUnit unit) {
		
		return null;
	}

	@Override
	public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit,
			GeoRadiusParam param) {
		
		return null;
	}

	@Override
	public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit) {
		
		return null;
	}

	@Override
	public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit,
			GeoRadiusParam param) {
		
		return null;
	}

	@Override
	public List<Long> bitfield(String key, String... arguments) {
		
		return null;
	}

	@Override
	public void execute(IRedisExecuteCallback redisExecuteCallback) {
		
		
	}

	@Override
	public void execute(IRedisClusterExecuteCallback redisClusterExecuteCallback) {
		
		
	}

	@Override
	public void destory() {
		// TODO Auto-generated method stub
		
	}

}
