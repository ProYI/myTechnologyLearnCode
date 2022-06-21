package org.example.mock;

import org.assertj.core.api.Assertions;
import org.example.AppTest;
import org.example.mock.service.BookService;
import org.example.mock.service.entity.Book;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

public class BookControllerMock extends AppTest {
    @Resource
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    /**
     * 参数在路径上
     * @throws Exception
     */
    @Test
    public void bookApiTest() throws Exception {
        String title = "红楼梦";
        // mockbean 开始模拟
        bookServiceMockBean(title);
        // mockbean 模拟完成

        String expect = "{\"title\":\"红楼梦\",\"author\":\"曹雪芹\",\"type\":\"四大名著\"}";
        mockMvc.perform(MockMvcRequestBuilders.get("/book/{title}", title))
                .andExpect(MockMvcResultMatchers.content().json(expect))
                .andDo(MockMvcResultHandlers.print());
        // mockbean 重置
    }

    /**
     * 参数在uri后面
     * @throws Exception
     */
    @Test
    public void bookApi2Test() throws Exception {
        String title = "红楼梦";
        // mockbean 开始模拟
        bookServiceMockBean(title);
        // mockbean 模拟完成

        String expect = "{\"title\":\"红楼梦\",\"author\":\"曹雪芹\",\"type\":\"四大名著\"}";
        mockMvc.perform(MockMvcRequestBuilders.get("/book/get")
                .param("title", title))
                .andExpect(MockMvcResultMatchers.content().json(expect))
                .andDo(MockMvcResultHandlers.print());
        // mockbean 重置
    }

    @Test
    public void bookServiceTest() {

        String title = "java learning";
        bookServiceMockBean(title);

        Assertions.assertThat(bookService.queryByTitle("java learning").getType()).isEqualTo("code");

    }
    /**
     * Mock打桩
     * @param title title
     */
    private void bookServiceMockBean(String title) {
        Book book = new Book();
        book.setAuthor("xx");
        book.setType("code");
        book.setTitle(title);
//        BDDMockito.when(bookService.queryByTitle(title)).thenReturn(book);
        BDDMockito.given(bookService.queryByTitle(title)).willReturn(book);
    }
}
