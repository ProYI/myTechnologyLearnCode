package online.proyi.springbot3.service;

import online.proyi.springbot3.entity.ai.ChatEntity;

import java.util.List;

public interface IChatService {

    /**
     * @description 保存对话信息
     * @author yb
     * @param[1] sessionId 会话sessionId
     * @param[2] content 会话内容
     * @param[3] type 会话类型（人、AI）
     * @time 2024/11/12 16:22
     */
    void saveMessage(String sessionId, String content, String type);

    /**
     * @description 获取最近几条数据
     * @author yb
     * @param[1] sessionId
     * @param[2] lastN
     * @return List<ChatEntity>
     * @time 2024/11/12 16:46
     */
    List<ChatEntity> getLastN(String sessionId, Integer lastN);

    /**
     * @description 清空会话数据
     * @author yb
     * @param[1] sessionId
     * @time 2024/11/12 16:54
     */
    void clear(String sessionId);

    /**
     * @description 根据对话id（非sessionId）删除数据
     * @author yb
     * @param[1] id
     * @time 2024/11/12 17:02
     */
    void deleteById(String id);
}
