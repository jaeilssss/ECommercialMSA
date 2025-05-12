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
//    @Before("@annotation(tx)")
//    public void setDataSourceType(Transactional tx) {
//        if (tx.readOnly()) {
//            DbContextHolder.setDbType(DbType.READ);
//        } else {
//            DbContextHolder.setDbType(DbType.READ);
//        }
//    }
//
//    @After("@annotation(tx)")
//    public void clearDataSourceType(Transactional tx) {
//        DbContextHolder.clearDbType();
//    }
}
