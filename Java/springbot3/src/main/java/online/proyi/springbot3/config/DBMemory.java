package online.proyi.springbot3.config;

import jakarta.annotation.Resource;
import online.proyi.springbot3.entity.ai.ChatEntity;
import online.proyi.springbot3.service.IChatService;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.MessageType;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class DBMemory implements ChatMemory {
    @Resource
    private IChatService chatService;

    @Override
    public void add(String conversationId, List<Message> messages) {
        for (Message message : messages) {
            chatService.saveMessage(conversationId, message.getContent(), message.getMessageType().getValue());
        }
    }

    @Override
    public List<Message> get(String conversationId, int lastN) {
        List<ChatEntity> list = chatService.getLastN(conversationId, lastN);
        if(list != null && !list.isEmpty()) {
            return list.stream().map(l -> {
                Message message = null;
                if (MessageType.ASSISTANT.getValue().equals(l.getType())) {
                    message = new AssistantMessage(l.getContent());
                } else if (MessageType.USER.getValue().equals(l.getType())) {
                    message = new UserMessage(l.getContent());
                }
                return message;
            }).collect(Collectors.<Message>toList());
        }else {
            return new ArrayList<>();
        }
    }

    @Override
    public void clear(String conversationId) {
        chatService.clear(conversationId);
    }

}
