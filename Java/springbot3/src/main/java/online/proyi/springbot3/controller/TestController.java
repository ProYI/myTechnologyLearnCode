package online.proyi.springbot3.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import online.proyi.springbot3.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "测试模块")
public class TestController {

    @GetMapping("/test/record")
    @Operation(summary = "测试Record")
    public String testRecord() {
        var param1 = new Student("小明", 16);

        return param1 + " : " + Student.ADDRESS;
    }
}
