package cn.bupt.dssc.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "名人vo实体")
public class FijiCriticalEntityVO {

    @ApiModelProperty("id")
    private String eid;

    @ApiModelProperty("名人姓名")
    private String name;

    @ApiModelProperty("外语名人姓名")
    private String name_cht;

    @ApiModelProperty("概述")
    private String summary;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("Base64编码的照片")
    private String image;
}
