package com.d2c.order.config.mybatis.aop;

import com.d2c.order.config.mybatis.bean.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAop {

    @Pointcut("!@annotation(com.d2c.order.config.mybatis.annotation.Master) " +
            "&& (execution(* com.d2c.order.business.service..*.select*(..)) " +
            "|| execution(* com.d2c.order.business.service..*.find*(..))) " +
            "|| execution(* com.d2c.order.business.service..*.count*(..))) " +
            "|| execution(* com.d2c.order.business.service..*.list*(..))) " +
            "|| execution(* com.d2c.order.business.service..*.search*(..)))")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.d2c.order.config.mybatis.annotation.Master) " +
            "|| execution(* com.d2c.order.business.service..*.insert*(..)) " +
            "|| execution(* com.d2c.order.business.service..*.update*(..)) " +
            "|| execution(* com.d2c.order.business.service..*.delete*(..)) " +
            "|| execution(* com.d2c.order.business.service..*.do*(..)) " +
            "|| execution(* com.d2c.order.business.service..*.create*(..)) " +
            "|| execution(* com.d2c.order.business.service..*.save*(..)) " +
            "|| execution(* com.d2c.order.business.service..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }

}