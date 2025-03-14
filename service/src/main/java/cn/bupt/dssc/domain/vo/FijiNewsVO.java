package cn.bupt.dssc.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "新闻vo实体")
public class FijiNewsVO {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("标题")
    private String header;

    @ApiModelProperty("发布时间")
    private String time;

    @ApiModelProperty("新闻来源")
    private String source;

    @ApiModelProperty("新闻作者")
    private String author;

    @ApiModelProperty("Base64编码的新闻插图")
    private String pic;

    @ApiModelProperty("新闻插图文本")
    private String pic_text;

    @ApiModelProperty("新闻内容")
    private String article;
}
