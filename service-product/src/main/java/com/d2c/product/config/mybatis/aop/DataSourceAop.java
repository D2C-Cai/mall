package com.d2c.product.config.mybatis.aop;

import com.d2c.product.config.mybatis.bean.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAop {

    @Pointcut("!@annotation(com.d2c.product.config.mybatis.annotation.Master) " +
            "&& (execution(* com.d2c.product.business.service..*.select*(..)) " +
            "|| execution(* com.d2c.product.business.service..*.find*(..))) " +
            "|| execution(* com.d2c.product.business.service..*.count*(..))) " +
            "|| execution(* com.d2c.product.business.service..*.list*(..))) " +
            "|| execution(* com.d2c.product.business.service..*.search*(..)))")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.d2c.product.config.mybatis.annotation.Master) " +
            "|| execution(* com.d2c.product.business.service..*.insert*(..)) " +
            "|| execution(* com.d2c.product.business.service..*.update*(..)) " +
            "|| execution(* com.d2c.product.business.service..*.delete*(..)) " +
            "|| execution(* com.d2c.product.business.service..*.do*(..)) " +
            "|| execution(* com.d2c.product.business.service..*.create*(..)) " +
            "|| execution(* com.d2c.product.business.service..*.save*(..)) " +
            "|| execution(* com.d2c.product.business.service..*.remove*(..))")
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