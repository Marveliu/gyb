package cn.wizzer.framework.ig;

import cn.wizzer.framework.util.DateUtil;
import org.nutz.integration.jedis.JedisAgent;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.List;

@IocBean
public class RedisIdGenerator implements IdGenerator {

    @Inject
    protected JedisAgent jedisAgent;

    public RedisIdGenerator() {
    }

    public RedisIdGenerator(JedisAgent jedisAgent) {
        this.jedisAgent = jedisAgent;
    }

    public String next(String tableName, String prefix) {
        String key = prefix.toUpperCase();
        if (key.length() > 16) {
            key = key.substring(0, 16);
        }
        try (Jedis jedis = jedisAgent.getResource()) {
            String ym = DateUtil.format(new Date(), "yyyyMM");
            String id = String.valueOf(jedis.incr("ig:" + tableName.toUpperCase() + ym));
            return key + ym + Strings.alignRight(id, 10, '0');
        }
    }

    public Object run(List<Object> fetchParam) {
        return next((String) fetchParam.get(0), (String) fetchParam.get(1));
    }

    public String fetchSelf() {
        return "ig";
    }

}
