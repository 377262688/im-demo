package com.york.checkReq.interceptor;

import com.york.checkReq.properties.CheckReqProperties;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author york
 * @create 2020-06-09 09:28
 **/
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ReqNoDrcAspect {
    @Autowired
    private CheckReqProperties checkReqProperties;

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Pointcut("@annotation(com.york.checkReq.annotation.CheckReqNo)")
    public void checkRepeat() {

    }

    @Before("checkRepeat()")
    public void before(JoinPoint joinPoint) throws Exception {
        String reqNo = getReqNo(joinPoint);
        if (reqNo != null) {
            if (StringUtils.isEmpty(reqNo)) {
                throw new Exception("");
            } else {
                String temReqNo = redisTemplate.opsForValue().get(checkReqProperties.getRedisKey() + reqNo);
                if (StringUtils.isEmpty(temReqNo)) {
                    redisTemplate.opsForValue().set(checkReqProperties.getRedisKey() + reqNo,reqNo,checkReqProperties.getRedisTimeOut(), TimeUnit.DAYS);
                } else {
                    throw new Exception("reqNo重复");
                }

            }
        }
    }

    private String getReqNo(JoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();
        if (arguments != null && arguments.length > 0) {
            return (String) arguments[0];
        }
        return null;
    }
}
