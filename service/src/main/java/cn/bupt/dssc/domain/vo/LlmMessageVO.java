package cn.bupt.dssc.domain.vo;

import cn.bupt.dssc.domain.po.AiMessagePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "大模型响应消息实体")
public class LlmMessageVO {

    @ApiModelProperty(value = "所属对话id")
    private String ChatMemoryId;

    @ApiModelProperty(value = "大模型消息")
    private AiMessagePO aiMessagePO;
}
