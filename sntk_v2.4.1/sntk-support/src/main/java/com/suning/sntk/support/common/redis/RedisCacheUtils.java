package com.suning.sntk.support.common.redis;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.suning.framework.sedis.ShardedJedisAction;
import com.suning.framework.sedis.ShardedJedisClient;
import com.suning.framework.sedis.impl.ShardedJedisClientImpl;
import com.suning.sntk.support.common.utils.JsonUtils;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;

/**
 * Redis缓存工具类. 注意：在 Redis 2.1.3 之前的版本中 修改一个expire key 会删除这个key。
 *
 * @author 88253542
 */
@Component
public class RedisCacheUtils {
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCacheUtils.class);

    protected static ShardedJedisClientImpl jedisClient;

    static {
        jedisClient = new ShardedJedisClientImpl("redis.conf");
    }

    public ShardedJedisClient getJedisClient() {
        return jedisClient;
    }

    public String set(final String key, final String value) {
        return jedisClient.execute(new ShardedJedisAction<String>() {
            @Override
            public String doAction(ShardedJedis jedis) {
                return jedis.set(key, value);
            }
        });
    }

    public String set(final byte[] key, final byte[] value) {
        return jedisClient.execute(new ShardedJedisAction<String>() {
            @Override
            public String doAction(ShardedJedis jedis) {
                return jedis.set(key, value);
            }
        });
    }

    public String get(final String key) {
        return jedisClient.execute(new ShardedJedisAction<String>() {
            @Override
            public String doAction(ShardedJedis jedis) {
                return jedis.get(key);
            }
        });
    }

    public <T> T get(String key, Type type) {
        try {
            String value = this.get(key);
            if (StringUtils.isBlank(value)) {
                return null;
            }
            return JsonUtils.json2Object(value, type);
        } catch (Exception e) {
            LOGGER.error("查询redis缓存异常,{}", key, e);
            return null;
        }
    }

    public byte[] get(final byte[] key) {
        return jedisClient.execute(new ShardedJedisAction<byte[]>() {
            @Override
            public byte[] doAction(ShardedJedis jedis) {
                return jedis.get(key);
            }
        });
    }

    public Boolean exists(final String key) {
        return jedisClient.execute(new ShardedJedisAction<Boolean>() {
            @Override
            public Boolean doAction(ShardedJedis jedis) {
                return jedis.exists(key);
            }
        });
    }

    public Long expire(final String key, final int seconds) {
        return jedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis jedis) {
                return jedis.expire(key, seconds);
            }
        });
    }

    public Long expire(final byte[] key, final int seconds) {
        return jedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis jedis) {
                return jedis.expire(key, seconds);
            }
        });
    }

    public Long expireAt(final String key, final long unixTime) {
        return jedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis jedis) {
                return jedis.expireAt(key, unixTime);
            }
        });
    }

    public Long ttl(final String key) {
        return jedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis jedis) {
                return jedis.ttl(key);
            }
        });
    }

    public String getSet(final String key, final String value) {
        return jedisClient.execute(new ShardedJedisAction<String>() {
            @Override
            public String doAction(ShardedJedis jedis) {
                return jedis.getSet(key, value);
            }
        });
    }

    public Long setnx(final String key, final String value) {
        return jedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis jedis) {
                return jedis.setnx(key, value);
            }
        });
    }

    public String setex(final String key, final int seconds, final String value) {
        return jedisClient.execute(new ShardedJedisAction<String>() {
            @Override
            public String doAction(ShardedJedis jedis) {
                return jedis.setex(key, seconds, value);
            }
        });
    }

    /**
     * 功能描述： 将哈希表 key 中的域 field 的值设为 value 。 如果 key 不存在，一个新的哈希表被创建并进行 HSET 操作。
     * 如果域 field 已经存在于哈希表中，旧值将被覆盖 时间复杂度：O(1) 如果 field 是哈希表中的一个新建域，并且值设置成功，返回 1 。
     * 如果哈希表中域 field 已经存在且旧值已被新值覆盖，返回 0
     *
     * @param
     * @return 返回值
     * @throw 异常描述
     * @see
     */
    public Long hset(final String key, final String field, final String value) {
        return jedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis jedis) {
                return jedis.hset(key, field, value);
            }
        });
    }

    public String hget(final String key, final String field) {
        return jedisClient.execute(new ShardedJedisAction<String>() {
            @Override
            public String doAction(ShardedJedis jedis) {
                return jedis.hget(key, field);
            }
        });
    }

    public Boolean hexists(final String key, final String field) {
        return jedisClient.execute(new ShardedJedisAction<Boolean>() {
            @Override
            public Boolean doAction(ShardedJedis jedis) {
                return jedis.hexists(key, field);
            }
        });
    }

    public String hmset(final String key, final Map<String, String> hash) {
        return jedisClient.execute(new ShardedJedisAction<String>() {
            @Override
            public String doAction(ShardedJedis jedis) {
                return jedis.hmset(key, hash);
            }
        });
    }

    public List<String> hmget(final String key, final String... fields) {
        return jedisClient.execute(new ShardedJedisAction<List<String>>() {
            @Override
            public List<String> doAction(ShardedJedis jedis) {
                return jedis.hmget(key, fields);
            }
        });
    }

    public Long hdel(final String key, final String... fields) {
        return jedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis jedis) {
                return jedis.hdel(key, fields);
            }
        });
    }

    public Long hlen(final String key) {
        return jedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis jedis) {
                return jedis.hlen(key);
            }
        });
    }

    public Boolean sismember(final String key, final String member) {
        return jedisClient.execute(new ShardedJedisAction<Boolean>() {
            @Override
            public Boolean doAction(ShardedJedis jedis) {
                return jedis.sismember(key, member);
            }
        });
    }

    public Long del(final String key) {
        return jedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis jedis) {
                return jedis.del(key);
            }
        });
    }

    public Long del(final byte[] key) {
        return jedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis jedis) {
                return jedis.del(key);
            }
        });
    }

    public Long zadd(final String key, final Map<String, Double> scoreMembers) {
        return jedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis jedis) {
                return jedis.zadd(key, scoreMembers);
            }
        });
    }

    /**
     * 功能描述：将一个或多个 member 元素及其 score 值加入到有序集 key 当中。 如果某个 member
     * 已经是有序集的成员，那么更新这个 member 的 score 值 并通过重新插入这个 member 元素，来保证该 member 在正确的位置上
     * 时间复杂度：O(M*log(N))， N 是有序集的基数， M 为成功添加的新成员的数量
     *
     * @param
     * @return 如果member已存在返回0, 否则返回1
     * @throw 异常描述
     * @see
     */
    public Long zadd(final String key, final double score, final String member) {
        return jedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis jedis) {
                return jedis.zadd(key, score, member);
            }
        });
    }

    public Set<String> zrangeByScore(final String key, final double min, final double max) {
        return jedisClient.execute(new ShardedJedisAction<Set<String>>() {
            @Override
            public Set<String> doAction(ShardedJedis jedis) {
                return jedis.zrangeByScore(key, min, max);
            }
        });
    }

    public Double zscore(final String key, final String member) {
        return jedisClient.execute(new ShardedJedisAction<Double>() {
            @Override
            public Double doAction(ShardedJedis jedis) {
                return jedis.zscore(key, member);
            }
        });
    }

    /**
     * 功能描述：移除有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。
     * 时间复杂度:O(log(N)+M)， N 为有序集的基数，而 M 为被移除成员的数量。
     *
     * @param
     * @return 被移除成员的数量
     * @throw 异常描述
     * @see
     */
    public Long zremrangeByScore(final String key, final double start, final double end) {
        return jedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis jedis) {
                return jedis.zremrangeByScore(key, start, end);
            }
        });
    }

    /**
     * 将序列化对象值value关联到key， 如果key已经持有其他值，SET就覆写旧值
     *
     * @param key   缓存key
     * @param value 缓存的对象
     */
    public void hset(String key, String field, Object value) {
        this.hset(key, field, value, RedisKeyConstants.ONE_DAY_S);
    }

    public boolean hset(String key, String field, Object value, int expireTime) {
        // 缓存管理器中取出缓存配置
        try {
            boolean exist = true;
            long leftTime = this.ttl(key);
            if (leftTime < 0) {
                // 过期时间小于0说明key不存在
                exist = false;
            }
            // 缓存配置不为空且有效则设置业务缓存

            this.hset(key, field, JsonUtils.object2Json(value));
            if (!exist) {
                // 有效时间不为0且key为新插入则设置业务缓存的有效时间
                this.expire(key, expireTime);
            }

            return true;
        } catch (Exception e) {
            LOGGER.error("hset error!", e);
            return false;
        }
    }

    /**
     *
     * @param key
     * @param field
     * @param value
     * @param expireTime
     * @return
     */
    public boolean hset(String key, String field, String value, int expireTime) {
        // 缓存管理器中取出缓存配置
        try {
            // 缓存配置不为空且有效则设置业务缓存
            this.hset(key, field, value);
            this.expire(key, expireTime);
            return true;
        } catch (Exception e) {
            LOGGER.error("hset error!", e);

            return false;
        }
    }

    /**
     * 返回哈希表key中给定域field的值
     *
     * @param key   缓存key
     * @param field key中的域
     * @param type  被缓存的对象类型
     * @return T 被缓存的对象
     */
    public <T> T hget(String key, String field, Type type) {
        // 缓存管理器中取出缓存配置
        try {
            // 缓存配置不为空且有效则获取业务缓存
            String str = this.hget(key, field);
            if (StringUtils.isEmpty(str)) {
                return null;
            }
            // 转换成需要的对象
            return JsonUtils.json2Object(str, type);
        } catch (Exception e) {
            LOGGER.error("hget error!", e);
            return null;
        }
    }

    public Long incr(final String key) {
        return jedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis jedis) {
                return jedis.incr(key);
            }
        });
    }

    public Long incrBy(final String key, final long integer) {
        return jedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis jedis) {
                return jedis.incrBy(key, integer);
            }
        });
    }

    /**
     * 功能描述: 基于redis的计数器应用<br>
     *
     * @param key
     * @param value
     * @return
     */
    public Long counter(String key, Long value, int expireTime) {
        Long counter = null;
        try {
            if (this.exists(key)) {
                counter = this.incrBy(key, value);
                this.expire(key, expireTime);
            }
        } catch (Exception e) {
            LOGGER.error("counter error!", e);
            return null;
        }
        return counter;
    }

    /**
     * 功能描述: 封装key-value形式的数据，value为对象json<br>
     *
     * @param key
     * @param type
     * @return
     */
    public <T> T gget(String key, Type type) {
        try {
            String str = this.get(key);
            if (StringUtils.isNotEmpty(str)) {
                return null;
            }
            // 转换成需要的对象
            return JsonUtils.json2Object(str, type);
        } catch (Exception e) {
            LOGGER.error("gget error!", e);
            return null;
        }
    }

    public void sset(String key, Object value, Integer expireTime) {
        // 缓存管理器中取出缓存配置
        try {
            this.set(key, JsonUtils.object2Json(value));
            // 每次都延迟时间，注意区别hset
            if (null != expireTime) {
                this.expire(key, expireTime);
            }
        } catch (Exception e) {
            LOGGER.error("sset error!", e);
        }
    }

    /**
     * set add
     *
     * @param key
     * @param members
     * @return
     */
    public Long sadd(final String key, final String... members) {
        return jedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis jedis) {
                return jedis.sadd(key, members);
            }
        });
    }

    /**
     * @param key
     * @param members
     * @return
     */
    public Long srem(final String key, final String... members) {
        return jedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis jedis) {
                return jedis.srem(key, members);
            }
        });
    }

    /**
     * 链表尾部追加插入
     *
     * @param key
     * @param values
     * @return
     */
    public Long rpush(final String key, final String... values) {
        return jedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis jedis) {
                return jedis.rpush(key, values);
            }
        });
    }

    public Long rpush(final String key, int expireTime, final String... values) {
        boolean exist = true;
        long leftTime = this.ttl(key);
        if (leftTime < 0) {
            // 过期时间小于0说明key不存在
            exist = false;
        }
        // 缓存配置不为空且有效则设置业务缓存
        long result = this.rpush(key, values);
        if (!exist) {
            // 有效时间不为0且key为新插入则设置业务缓存的有效时间
            this.expire(key, expireTime);
        }
        return result;
    }

    /**
     * 链表头部取数据
     *
     * @param key
     * @return
     */
    public String lpop(final String key) {
        return jedisClient.execute(new ShardedJedisAction<String>() {
            @Override
            public String doAction(ShardedJedis jedis) {
                return jedis.lpop(key);
            }
        });
    }

    public String type(final String key) {
        return jedisClient.execute(new ShardedJedisAction<String>() {
            @Override
            public String doAction(ShardedJedis jedis) {
                return jedis.type(key);
            }
        });
    }

    public List<String> lrange(final String key) {
        return jedisClient.execute(new ShardedJedisAction<List<String>>() {
            @Override
            public List<String> doAction(ShardedJedis jedis) {
                return jedis.lrange(key, 0, -1);
            }
        });
    }

    public Set<String> hkeys(final String key) {
        return jedisClient.execute(new ShardedJedisAction<Set<String>>() {
            @Override
            public Set<String> doAction(ShardedJedis jedis) {
                return jedis.hkeys(key);
            }
        });
    }

    public Map<String, String> hgetAll(final String key) {
        return jedisClient.execute(new ShardedJedisAction<Map<String, String>>() {
            @Override
            public Map<String, String> doAction(ShardedJedis jedis) {
                return jedis.hgetAll(key);
            }
        });
    }

    public Set<String> smembers(final String key) {
        return jedisClient.execute(new ShardedJedisAction<Set<String>>() {
            @Override
            public Set<String> doAction(ShardedJedis jedis) {
                return jedis.smembers(key);
            }
        });
    }

    public Set<String> zrange(final String key) {
        return jedisClient.execute(new ShardedJedisAction<Set<String>>() {
            @Override
            public Set<String> doAction(ShardedJedis jedis) {
                return jedis.zrange(key, 0, -1);
            }
        });
    }

    /**
     * 当 key 存在且是有序集类型时，返回有序集的基数。 当 key 不存在时，返回 0
     * 18032490
     *
     * @param key
     * @return
     */
    public Long zcard(final String key) {
        return jedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis jedis) {
                return jedis.zcard(key);
            }
        });
    }

    /**
     * 指定区间内，带有分数值(可选)的有序集成员的列表
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> zrevrange(final String key, final long start, final long end) {
        return jedisClient.execute(new ShardedJedisAction<Set<String>>() {
            @Override
            public Set<String> doAction(ShardedJedis jedis) {
                return jedis.zrevrange(key, start, end);
            }
        });
    }

    /**
     * @return
     * @see redis.clients.jedis.JedisCommands#incr(String)
     */
    public Long zrem(final String key, final String... members) {
        return jedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis jedis) {
                return jedis.zrem(key, members);
            }
        });
    }

    public <T> T getBin(final String key, final Class<T> type) {
        return jedisClient.execute(new ShardedJedisAction<T>() {
            @Override
            public T doAction(ShardedJedis jedis) {
                return jsonDecode(jedis.get(key), type);
            }
        });
    }

    private <T> T jsonDecode(String input, Class<T> type) {
        if (input == null || input.length() == 0) {
            return null;
        }
        return JSON.parseObject(input, type);
    }

    public void setexBin(final String key, final Object object, final int time) {
        jedisClient.execute(new ShardedJedisAction<String>() {
            @Override
            public String doAction(ShardedJedis jedis) {
                return jedis.setex(key, time, jsonEncode(object));
            }
        });
    }

    public String jsonEncode(Object object) {
        if (object == null) {
            return null;
        }
        return JSON.toJSONString(object);
    }

    public void batchSetAdd(final String key, Set<String> set) {
        if (CollectionUtils.isEmpty(set)) {
            return;
        }

        final String[] values = new String[set.size()];
        set.toArray(values);

        jedisClient.execute(new ShardedJedisAction<String>() {
            @Override
            public String doAction(ShardedJedis jedis) {
                ShardedJedisPipeline pipelined = jedis.pipelined();
                pipelined.sadd(key, values);
                pipelined.sync();
                return null;
            }
        });
    }

    public String srandmember(final String key) {
        return jedisClient.execute(new ShardedJedisAction<String>() {
            @Override
            public String doAction(ShardedJedis shardedJedis) {
                return shardedJedis.srandmember(key);
            }
        });

    }
}
