package cn.bupt.dssc.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "用户消息实体")
public class UserMessageDTO {

    @ApiModelProperty(value = "用户消息")
    private String msg;
}
