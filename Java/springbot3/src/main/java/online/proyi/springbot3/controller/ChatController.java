package online.proyi.springbot3.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import online.proyi.springbot3.entity.ai.ChatParam;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Tag(name = "AI聊天模块")
@Controller
public class ChatController {

    //注入模型，配置文件中的模型，或者可以在方法中指定模型
    @Resource
    private OllamaChatModel ollamaChatModel;

    //聊天 client
    private ChatClient chatClient;

    // 模拟数据库存储会话和消息
    private final ChatMemory chatMemory = new InMemoryChatMemory();

    //首页
    @GetMapping("/index")
    public String index(ModelAndView view){
        return "index";
    }

    //开始聊天，生成唯一 sessionId
    @GetMapping("/start")
    public String start(Model model){
        //创建随机会话 ID
        String sessionId = UUID.randomUUID().toString();
        model.addAttribute("sessionId", sessionId);
        //创建聊天client
        chatClient = ChatClient.builder(this.ollamaChatModel).defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory, sessionId, 10)).build();
        return "chatPage";
    }

    //聊天
    @PostMapping("/chat")
    @ResponseBody
    public String chat(@RequestBody ChatParam param){
        //直接返回
        return chatClient.prompt(param.getUserMsg()).call().content();
    }

    //删除聊天
    @DeleteMapping("/clear/{id}")
    @ResponseBody
    public void clear(@PathVariable("id") String sessionId){
        chatMemory.clear(sessionId);
    }
}
