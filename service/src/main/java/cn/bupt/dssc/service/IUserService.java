package cn.bupt.dssc.service;

import cn.bupt.dssc.domain.dto.LoginFormDTO;
import cn.bupt.dssc.domain.po.User;
import cn.bupt.dssc.domain.vo.UserLoginVO;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IUserService extends IService<User> {

    UserLoginVO login(LoginFormDTO loginFormDTO);
}
