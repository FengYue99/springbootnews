package com.zl.service.impl;

import com.zl.dao.TypeDao;
import com.zl.pojo.Type;
import com.zl.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author fy
 */
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeDao td;
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate rt;

    /**
     * 查询新闻类型的所有信息
     */
    @Override
    @Transactional()
    public List<Type> queryType() {
        ValueOperations vo = rt.opsForValue();
        List<Type> types = (List<Type>) vo.get("types");
        if (types==null){
            types = td.queryType();
            vo.set("types",types);
        }

        return types;
    }
}
