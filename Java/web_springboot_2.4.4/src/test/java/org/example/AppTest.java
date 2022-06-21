package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple App.
 *
 * webEnvironment:
 * MOCK: 加载Web ApplicationContext并提供模拟Web环境
 * RANDOM_PORT: 加载 WebServerApplicationContext 并提供真实的Web环境,启用的是随机web容器端口
 * DEFINED_PORT: 加载 WebServerApplicationContext 并提供真实的Web环境 和 RANDOM_PORT 不同的是启用你激活的SpringBoot应用端口，通常都声明在application.yml配置文件中
 * NONE: 通过SpringApplication加载一个ApplicationContext。但不提供 任何 Web环境
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}
