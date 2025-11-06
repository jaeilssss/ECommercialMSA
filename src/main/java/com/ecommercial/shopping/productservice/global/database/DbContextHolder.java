package com.ecommercial.shopping.productservice.global.database;

import com.ecommercial.shopping.productservice.global.enums.DbType;

public class DbContextHolder {
    private static final ThreadLocal<DbType> contextHolder = new ThreadLocal<>();

    public static void setDbType(DbType dbType) {
        contextHolder.set(dbType);
    }
    public static DbType getDbType() {
        return contextHolder.get();
    }

    public static void clearDbType() {
        contextHolder.remove();
    }
}
