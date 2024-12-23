package online.proyi.springbot3.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import online.proyi.springbot3.entity.User;
import online.proyi.springbot3.entity.query.UserQueryBean;
import online.proyi.springbot3.entity.response.ResponseResult;
import online.proyi.springbot3.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "用户模块")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * @param user user param
     * @return user
     */
    @PostMapping("add")
    @Parameters(
            {
            @Parameter(name = "id", description = "主键id", allowEmptyValue = true, schema = @Schema(type = "Long")),
            @Parameter(name = "userName", description = "用户名", schema = @Schema(type = "String"), required = true),
            @Parameter(name = "password", description = "密码", schema = @Schema(type = "String"), required = true),
            @Parameter(name = "email", description = "电子邮箱", schema = @Schema(type = "String"), required = true),
            @Parameter(name = "phoneNumber", description = "手机号码", schema = @Schema(type = "Long"), required = true),
            @Parameter(name = "description", description = "描述", schema = @Schema(type = "String"), required = true)
            }
    )
    public ResponseResult<Boolean> add(User user) {
        boolean result = false;
        if (user.getId() == null) {
            result = userService.save(user) > 0;
        } else {
            result = userService.update(user) > 0;
        }
        return  ResponseResult.success(result);
    }

    /**
     * @return user list
     */
    @GetMapping("edit/{userId}")
    public  ResponseResult<User> edit(@PathVariable("userId") Long userId) {
        return  ResponseResult.success(userService.findById(userId));
    }

    /**
     * @return user list 2
     */
    @GetMapping("edit2/{userId}")
    public  ResponseResult<User> edit2(@PathVariable("userId") Long userId) {
        return  ResponseResult.success(userService.findById2(userId));
    }

    /**
     * @return user list
     */
    @GetMapping("list")
    @Parameters(
            {
                    @Parameter(name = "userName", description = "用户名", schema = @Schema(type = "String")),
                    @Parameter(name = "email", description = "电子邮箱", schema = @Schema(type = "String")),
                    @Parameter(name = "phoneNumber", description = "手机号码", schema = @Schema(type = "Long")),
                    @Parameter(name = "description", description = "描述", schema = @Schema(type = "String"))
            }
    )
    public  ResponseResult<List<User>> list(UserQueryBean userQueryBean) {
        return  ResponseResult.success(userService.findList(userQueryBean));
    }

    @PostMapping("delete")
    public  ResponseResult<Integer> delete(Long userId) {
        return  ResponseResult.success(userService.deleteById(userId));
    }
}
