package online.proyi.springbot3.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "用户实体")
public class User implements BaseEntity {

    @Schema(description = "id, 主键")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @Schema(description = "用户名")
    private String userName;

    @JsonIgnore
    @Schema(description = "密码")
    private String password;

    @Schema(description = "电子邮箱")
    private String email;

    @Schema(description = "手机号码")
    private long phoneNumber;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "新建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;
}
