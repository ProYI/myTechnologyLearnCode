package online.proyi.springbot3.service.impl;

import online.proyi.springbot3.entity.ai.ChatEntity;
import online.proyi.springbot3.service.IChatService;
import org.springframework.ai.chat.messages.MessageType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ChatServiceImpl implements IChatService {
    Map<String, List<ChatEntity>> map = new HashMap<>();

    @Override
    public void saveMessage(String sessionId, String content, String type) {
        ChatEntity entity = new ChatEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setContent(content);
        entity.setSessionId(sessionId);
        entity.setType(type);
        entity.setTime(new Date());
        //改成常量
        entity.setBeDeleted("N");
        if(MessageType.ASSISTANT.getValue().equals(type)){
            entity.setUserChatId(getLastN(sessionId, 1).get(0).getId());
        }
        //todo 保存数据库
        //模拟保存到数据库
        List<ChatEntity> list = map.getOrDefault(sessionId, new ArrayList<>());
        list.add(entity);
        map.put(sessionId, list);
    }

    @Override
    public List<ChatEntity> getLastN(String sessionId, Integer lastN) {
        //todo 从数据库获取
        //模拟从数据库获取
        List<ChatEntity> list = map.get(sessionId);
        return list != null ? list.stream().skip(Math.max(0, list.size() - lastN)).toList() : List.of();
    }

    @Override
    public void clear(String sessionId) {
        //todo 数据库更新 beDeleted 字段
        map.put(sessionId, new ArrayList<>());
    }

    @Override
    public void deleteById(String id) {
        //todo 数据库直接将该 id 数据 beDeleted 改成 Y
        for (Map.Entry<String, List<ChatEntity>> next : map.entrySet()) {
            List<ChatEntity> list = next.getValue();
            list.removeIf(chat -> id.equals(chat.getId()) || id.equals(chat.getUserChatId()));
        }
    }
}
