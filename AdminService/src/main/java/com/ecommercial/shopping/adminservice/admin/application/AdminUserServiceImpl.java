package com.ecommercial.shopping.adminservice.admin.application;

import com.ecommercial.shopping.adminservice.admin.application.dto.AdminSignUpCommend;
import com.ecommercial.shopping.adminservice.admin.application.dto.AdminUserLoginCommand;
import com.ecommercial.shopping.adminservice.admin.domain.entity.Admin;
import com.ecommercial.shopping.adminservice.admin.domain.repository.AdminQueryRepository;
import com.ecommercial.shopping.adminservice.admin.domain.repository.AdminRepository;
import com.ecommercial.shopping.adminservice.company.domain.entity.Company;
import com.ecommercial.shopping.adminservice.company.domain.repository.CompanyQueryRepository;
import com.ecommercial.shopping.adminservice.global.error.AdminError;
import com.ecommercial.shopping.adminservice.global.error.CompanyError;
import com.ecommercial.shopping.adminservice.global.exception.MyException;
import com.ecommercial.shopping.adminservice.global.jwt.JwtProviders;
import com.ecommercial.shopping.adminservice.global.jwt.dto.JwtTokenResponse;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class AdminUserServiceImpl implements AdminUserService{
    private final AdminRepository adminRepository;
    private final AdminQueryRepository adminQueryRepository;
    private final CompanyQueryRepository companyQueryRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProviders jwtProviders;
    private final BlackListService blackListService;

    @Transactional
    @Override
    public void signUp(AdminSignUpCommend.Req request) {
        isSignUpUser(request.getEmail());
        adminRepository.save(request.toEntity(
                getCompanyByCompanyId(request.getCompanyId()),
                passwordEncoder.encode(request.getPassword())
        ));
    }

    @Override
    public void logout(String token) {
        long remainTime = jwtProviders.getRemainExpirationTime(token);
        blackListService.save(token, remainTime);
    }

    @Override
    public JwtTokenResponse login(AdminUserLoginCommand.Req request) {
        Admin admin = getAdminUser(request.getEmail());
        isCheckPassword(request.getPassword(), admin.getPassword());
        return jwtProviders.createToken(admin);
    }

    private void isCheckPassword(String rawPassword, String encodedPassword) {
        if(!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new MyException(
                    AdminError.NOT_AVAILABLE_LOGIN.getCode(),
                    AdminError.NOT_AVAILABLE_LOGIN.getMessage()
            );
        }
    }
    private Admin getAdminUser(String email) {
        return adminQueryRepository.findByEmail(email)
                .orElseThrow(
                        () -> new MyException(
                                AdminError.NOT_FOUND_EMAIL.getCode(),
                                AdminError.IS_SIGNUP_EMAIL.getMessage()
                        )
                );
    }
    private void isSignUpUser(String email) {
        adminQueryRepository.findByEmail(email)
                .ifPresent(
                        admin -> {
                            throw new MyException(
                                    AdminError.IS_SIGNUP_EMAIL.getCode(),
                                    AdminError.IS_SIGNUP_EMAIL.getMessage()
                            );
                        }
                );
    }
    private Company getCompanyByCompanyId(Long companyId) {
        return companyQueryRepository.findById(companyId)
                .orElseThrow(() ->
                        new MyException(
                                CompanyError.NOT_FOUND_COMPANY_BY_ID.getCode(),
                                CompanyError.NOT_FOUND_COMPANY_BY_ID.getMessage()
                        )
                );
    }

    public long getRemainExpirationTime(String token) {
        Date expiration = jwtProviders.getTokenExpiration(token);
        return expiration.getTime() - System.currentTimeMillis();
    }

}
