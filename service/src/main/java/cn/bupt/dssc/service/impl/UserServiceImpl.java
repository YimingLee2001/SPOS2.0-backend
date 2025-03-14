package cn.bupt.dssc.service.impl;

import cn.bupt.dssc.common.exception.BadRequestException;
import cn.bupt.dssc.config.JwtProperties;
import cn.bupt.dssc.domain.dto.LoginFormDTO;
import cn.bupt.dssc.domain.po.User;
import cn.bupt.dssc.domain.vo.UserLoginVO;
import cn.bupt.dssc.mapper.UserMapper;
import cn.bupt.dssc.service.IUserService;
import cn.bupt.dssc.utils.JwtTool;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final PasswordEncoder passwordEncoder;

    private final JwtTool jwtTool;

    private final JwtProperties jwtProperties;

    @Override
    public UserLoginVO login(LoginFormDTO loginDTO) {
        // 1.数据校验
        String username = loginDTO.getUserName();
        String passowrd = loginDTO.getPassword();
        // 2.根据用户名或手机号查询
        User user = lambdaQuery().eq(User::getUserName, username).one();
        Assert.notNull(user, "用户名错误");
        // 3.校验密码
        /* 由于数据库目前是明文存储密码，所以这个用不了
        if (!passwordEncoder.matches(passowrd, user.getPassWord())) {
            throw new BadRequestException("用户名或密码错误");
        }
         */
        if (!passowrd.equals(user.getPassWord())) {
            throw new BadRequestException("用户名或密码错误");
        }
        // 4.生成TOKEN
        String token = jwtTool.createToken(user.getUserId(), jwtProperties.getTokenTTL());
        // 5.封装VO返回
        UserLoginVO vo = new UserLoginVO();
        vo.setUserId(user.getUserId());
        vo.setUsername(user.getUserName());
        vo.setEmail(user.getEmail());
        vo.setToken(token);
        return vo;
    }
}
