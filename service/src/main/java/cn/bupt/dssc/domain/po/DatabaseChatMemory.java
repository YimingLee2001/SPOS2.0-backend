package cn.bupt.dssc.domain.po;

import cn.hutool.core.util.IdUtil;
import com.agentsflex.core.memory.ChatMemory;
import com.agentsflex.core.message.Message;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Document(collection = "ChatMemory")
public class DatabaseChatMemory implements ChatMemory {

    @MongoId
    @Field("_id")
    private String id;

    private String userId;

    private List<Message> messages = new ArrayList<>();

    public DatabaseChatMemory() {
        this.id = IdUtil.objectId();
    }

    public DatabaseChatMemory(String id) {
        this.id = id;
    }

    @Override
    public String id() {
        return getId();
    }

    @Override
    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public void addMessage(Message message) {
        messages.add(message);
    }
}
