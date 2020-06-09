package com.york.checkReq.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author york
 * @create 2020-06-08 16:24
 **/
@Configuration
@ComponentScan("com.york.checkReq.")
@Conditional(CheckReqCondition.class)
public class CheckReqConf {
}
