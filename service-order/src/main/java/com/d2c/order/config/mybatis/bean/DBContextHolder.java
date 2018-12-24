package com.d2c.order.config.mybatis.bean;


import com.d2c.order.config.mybatis.enums.DBTypeEnum;

import java.util.concurrent.atomic.AtomicInteger;

public class DBContextHolder {

    private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<>();

    private static final AtomicInteger counter = new AtomicInteger(-1);

    public static void set(DBTypeEnum dbType) {
        contextHolder.set(dbType);
    }

    public static DBTypeEnum get() {
        return contextHolder.get();
    }

    public static void master() {
        set(DBTypeEnum.MASTER);
        // System.out.println("Using datasource config master...");
    }

    public static void slave() {
        // 轮询
        int index = counter.getAndIncrement() % 2;
        if (counter.get() > 9999) {
            counter.set(-1);
        }
        if (index == 0) {
            set(DBTypeEnum.SLAVE1);
            // System.out.println("Using datasource config slave1...");
        } else {
            set(DBTypeEnum.SLAVE2);
            // System.out.println("Using datasource config slave2...");
        }
    }

}