package cn.bupt.dssc.utils;

import cn.bupt.dssc.common.exception.UnauthorizedException;
import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.time.Duration;
import java.util.Date;

@Component
public class JwtTool {

    private final JWTSigner jwtSigner;

    public  JwtTool(KeyPair keyPair) {
        this.jwtSigner = JWTSignerUtil.createSigner("rs256", keyPair);
    }

    /**
     * 创建 access-token
     *
     * @param userId 用户信息
     * @param ttl 过期时间
     * @return access-token
     */
    public String createToken(String userId, Duration ttl) {
        return JWT.create()
                .setPayload("user", userId)
                .setExpiresAt(new Date(System.currentTimeMillis() + ttl.toMillis()))
                .setSigner(jwtSigner)
                .sign();
    }

    public String parseToken(String token) {
        // 1.校验token是否为空
        if (token == null) {
            throw new UnauthorizedException("未登录");
        }

        // 2.校验并解析jwt
        JWT jwt;
        try {
            jwt = JWT.of(token).setSigner(jwtSigner);
        } catch (Exception e) {
            throw new UnauthorizedException("无效的token", e);
        }

        // 3.校验jwt是否有效
        if (!jwt.verify()) {
            throw new UnauthorizedException("无效的token");
        }

        // 4.校验是否过期
        try {
            JWTValidator.of(jwt).validateDate();
        } catch (ValidateException e) {
            throw new UnauthorizedException("token已经过期");
        }

        // 5.数据格式校验
        Object userPayload = jwt.getPayload("user");
        if (userPayload == null) {
            throw new UnauthorizedException("无效的token");
        }

        // 6.数据解析
        try {
            return userPayload.toString();
        } catch (RuntimeException e) {
            throw new UnauthorizedException("无效的token");
        }
    }
}
