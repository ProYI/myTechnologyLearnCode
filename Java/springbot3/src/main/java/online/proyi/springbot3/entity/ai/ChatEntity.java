package online.proyi.springbot3.entity.ai;

import lombok.Data;

import java.util.Date;

@Data
public class ChatEntity {

    private String id;

    /** 会话id */
    private String sessionId;

    /** 会话内容 */
    private String content;

    /** AI、人 */
    private String type;

    /** 创建时间 */
    private Date time;

    /** 是否删除，Y-是 */
    private String beDeleted;

    /** AI会话时，获取人对话ID */
    private String userChatId;

}

