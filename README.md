# distributed-counter
a  distributed counter use zookeeper、redis、and so on,just to learn。

2017-10-25 
 the version 1.0 begin to writeing!

counter-client: 包含 redis,zk,hush-counter
counter-server: hush-counter server (singleton、ha两种模式)
 
counter:
 
 increment(int x)
 set(int val)
 get()

redis
zookeeper

serialNum(snowflak,uuid):
 next()
 
每一个类型的counter增加一个enable方法，
redis :jedispool, jedisOperater (set,get,expire,ttl,hset hmset,del ,keys，zset,zadd,sadd,execute(jedis)) 
 enable : true 
 type:sentlate,cluster

redis配置及两种ha方案，redisoperator及counter
公司地址修改的调整后端代码，和对方系统联调
(redis 3.0.5之前到达maxmemory的值后默认为volitile-lru,3.0.5之后为noeviction[不会删除任何key]再次set会报错)
zikao,house,car,learn
 
