package com.york.checkReq.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.util.StringUtils;


/**
 * @author york
 * @create 2020-06-08 16:18
 **/
public class CheckReqCondition implements Condition {

    Logger logger = LoggerFactory.getLogger(CheckReqCondition.class);
    /**
     * Determine if the condition matches.
     *
     * @param context  the condition context
     * @param metadata metadata of the {@link AnnotationMetadata class}
     *                 or {@link MethodMetadata method} being checked
     * @return {@code true} if the condition matches and the component can be registered,
     * or {@code false} to veto the annotated component's registration
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String property = context.getEnvironment().getProperty("spring.redis.key");
        String clusterProperty = context.getEnvironment().getProperty("spring.redis.cluster.nodes");
        if (StringUtils.isEmpty(property) && StringUtils.isEmpty(clusterProperty)){
            logger.warn("Need to configure redis!");
            return false ;
        }else {
            return true;
        }
    }
}
