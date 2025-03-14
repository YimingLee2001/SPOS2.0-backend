package cn.bupt.dssc.domain.vo;

import lombok.Data;

@Data
public class UserLoginVO {
    private String token;
    private String userId;
    private String username;
    private String email;
}
