package com.zl.quartz;

import com.zl.dao.TypeDao;
import com.zl.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author fy
 */
@Component
public class QueryTypeToRedis {
    @Autowired
    private TypeDao td;
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate rt;

    /**
     * 查询新闻类型
     */
    @Scheduled(fixedDelay = 5000)
    public void queryType(){
        System.out.println("..."+ new Date());
        ValueOperations vo = rt.opsForValue();
        List<Type> types = td.queryType();
        vo.set("types",types);
    }
}
