package online.proyi.springbot3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class User implements BaseEntity {

    private Long id;

    private String userName;

    @JsonIgnore
    private String password;

    private String email;

    private long phoneNumber;

    private String description;

    private Date createTime;

    private Date updateTime;
}
