package cn.bupt.dssc.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("feijicriticalEntity")
public class FijiCriticalEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "eid", type = IdType.AUTO)
    private String eid;

    @TableField(value = "name")
    private String name;

    @TableField(value = "name_cht")
    private String name_cht;

    @TableField(value = "summary")
    private String summary;

    @TableField(value = "gender")
    private String gender;
}
