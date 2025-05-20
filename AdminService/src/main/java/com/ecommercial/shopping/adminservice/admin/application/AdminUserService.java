package com.ecommercial.shopping.adminservice.admin.application;

import com.ecommercial.shopping.adminservice.admin.application.dto.AdminSignUpCommend;
import com.ecommercial.shopping.adminservice.admin.application.dto.AdminUserLoginCommand;
import com.ecommercial.shopping.adminservice.global.jwt.dto.JwtTokenResponse;
import org.springframework.stereotype.Service;

public interface AdminUserService {
    void signUp(AdminSignUpCommend.Req request);
    JwtTokenResponse login(AdminUserLoginCommand.Req request);

}
