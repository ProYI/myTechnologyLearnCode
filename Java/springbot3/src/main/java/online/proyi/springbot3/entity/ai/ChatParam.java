package online.proyi.springbot3.entity.ai;

import lombok.Data;

@Data
public class ChatParam {
    /** 随机id */
    private String id;

    /** 用户输入文本 */
    private String userMsg;

    /** 会话id */
    private String sessionId;
}
