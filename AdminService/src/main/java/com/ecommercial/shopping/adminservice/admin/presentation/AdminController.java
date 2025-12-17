package com.ecommercial.shopping.adminservice.admin.presentation;

import com.ecommercial.shopping.adminservice.admin.application.AdminUserService;
import com.ecommercial.shopping.adminservice.admin.presentation.dto.AdminLoginBody;
import com.ecommercial.shopping.adminservice.admin.presentation.dto.AdminSignUpBody;
import com.ecommercial.shopping.adminservice.global.dto.BaseResponse;
import com.ecommercial.shopping.adminservice.global.error.AdminError;
import com.ecommercial.shopping.adminservice.global.exception.MyException;
import com.ecommercial.shopping.adminservice.global.jwt.JwtProviders;
import com.ecommercial.shopping.adminservice.global.jwt.dto.JwtTokenResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminUserService adminUserService;
    private final JwtProviders jwtProviders;
    @PostMapping("/signUp")
    public ResponseEntity<BaseResponse<String>> signUp(@RequestBody AdminSignUpBody adminSignUpBody) {
        adminUserService.signUp(adminSignUpBody.toDto());
        return ResponseEntity.ok(new BaseResponse<>("OK", "회원 가입이 왼료 됐습니다"));
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<JwtTokenResponse>> login(
            HttpServletResponse httpServletResponse,
            @RequestBody AdminLoginBody adminLoginBody
    ) {
        JwtTokenResponse jwtTokenResponse = adminUserService.login(adminLoginBody.toDto());
        httpServletResponse.addCookie(createCookieToInsertRefreshToken(jwtTokenResponse.getRefreshToken()));
        return ResponseEntity.ok(new BaseResponse<>("OK", jwtTokenResponse));
    }

    @PostMapping("/logout")
    public ResponseEntity<BaseResponse<String>> logout(@RequestHeader("Authorization") String authorization) {
        String accessToken = extractToken(authorization);
        adminUserService.logout(accessToken);
        return ResponseEntity.ok(new BaseResponse<>("OK", "로그아웃이 완료 됐습니다."));
    }

    private Cookie createCookieToInsertRefreshToken(String refreshToken) {
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(Integer.parseInt(String.valueOf(jwtProviders.getRefreshExpiration())));
        return cookie;
    }

    private String extractToken(String authorization) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new MyException(
                    AdminError.INVALID_TOKEN.getCode(),
                    AdminError.INVALID_TOKEN.getMessage()
            );
        }
        return authorization.substring(7);
    }
}
