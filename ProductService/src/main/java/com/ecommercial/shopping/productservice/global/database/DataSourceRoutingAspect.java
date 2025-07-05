package com.ecommercial.shopping.adminservice.global.database;

import com.ecommercial.shopping.adminservice.global.enums.DbType;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

//@Aspect
//@Component
public class DataSourceRoutingAspect {
//
//    @Before("@annotation(transactional)")
//    public void setDataSourceType(Transactional transactional) {
//        System.out.println("aop 실행");
//        if (transactional.readOnly()) {
//            DbContextHolder.setDbType(DbType.READ);
//        } else {
//            DbContextHolder.setDbType(DbType.WRITE);
//        }
//    }
//
//    @After("@annotation(transactional)")
//    public void clearDataSourceType(Transactional transactional) {
//        DbContextHolder.clearDbType();
//    }
}
