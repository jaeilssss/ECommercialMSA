package com.ecommercial.shopping.adminservice.admin.application;

public interface BlackListService {
    public boolean isBlackListToken(String token);
    public void save(String token, long expirationTime);
}
