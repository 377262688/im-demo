package com.york.checkReq.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author york
 * @create 2020-06-09 09:34
 **/
@Component
@ConfigurationProperties(prefix = "request.check")
public class CheckReqProperties {

    private String redisKey;

    private Long redisTimeOut;

    public String getRedisKey() {
        return redisKey;
    }

    public void setRedisKey(String redisKey) {
        this.redisKey = redisKey;
    }

    public Long getRedisTimeOut() {
        return redisTimeOut;
    }

    public void setRedisTimeOut(Long redisTimeOut) {
        this.redisTimeOut = redisTimeOut;
    }
}
