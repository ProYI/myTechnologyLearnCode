package online.proyi.springbot3.controller;

import online.proyi.springbot3.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test/record")
    @ResponseBody
    public String testRecord() {
        var param1 = new Student("小明", 16);

        return param1 + " : " + Student.ADDRESS;
    }
}
