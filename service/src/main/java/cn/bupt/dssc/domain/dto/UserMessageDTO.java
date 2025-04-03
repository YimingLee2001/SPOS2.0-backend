package cn.bupt.dssc.domain.dto;

import cn.bupt.dssc.domain.po.HumanMessagePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(description = "用户消息实体")
public class UserMessageDTO {

    @ApiModelProperty(value = "所属对话id")
    private String ChatMemoryId;

    @ApiModelProperty(value = "用户消息")
    private HumanMessagePO humanMessagePO;
}
