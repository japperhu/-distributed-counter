# distributed-counter
a  distributed counter use zookeeper、redis、and so on,just to learn。

2017-10-25 
 the version 1.0 begin to writeing!

counter-client: include redis,zk,hush-counter
counter-server: hush-counter server (singleton、ha)
 
counter:
 
 increment(int x)
 set(int val)
 get()

redis
zookeeper

serialNum(snowflak,uuid):
next()

-----------------redis client desigin:
IRedisClient:
	singleton,sentinel,cluster

builderwarp: 
	builder(model,redisConfig){基于Model和config构建对应的redisclient}

#### Config
 redis:
    model:
    pool:
    singleton:
        addr:
        passwd: 
    sentinel:
        addr:
        passwd:
     cluster:
	addr:
        passwd:
    
 
