package cn.bupt.dssc.domain.po;

import com.agentsflex.core.message.AbstractTextMessage;
import com.agentsflex.core.message.AiMessage;
import com.agentsflex.core.message.FunctionCall;
import com.agentsflex.core.message.MessageStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Accessors(chain = true)
public class AiMessagePO extends AbstractTextMessage {
    private Integer index;
    private MessageStatus status;
    private Integer promptTokens;
    private Integer completionTokens;
    private Integer totalTokens;
    private String fullContent;
    private String reasoningContent;

    public AiMessagePO(AiMessage aiMessage) {
        // 父类的赋值
        this.setMetadataMap(aiMessage.getMetadataMap());
        this.setContent(aiMessage.getContent());

        // 子类的赋值
        this.index = aiMessage.getIndex();
        this.status = aiMessage.getStatus();
        this.promptTokens = aiMessage.getPromptTokens();
        this.completionTokens = aiMessage.getCompletionTokens();
        this.totalTokens = aiMessage.getTotalTokens();
        this.fullContent = aiMessage.getFullContent();
        this.reasoningContent = aiMessage.getFullContent();
    }

    public AiMessage toAiMessage(List<FunctionCall> functionCalls) {
        AiMessage aiMessage = new AiMessage();

        // 父类的赋值
        aiMessage.setMetadataMap(this.getMetadataMap());
        aiMessage.setContent(this.getContent());

        // 子类的赋值
        aiMessage.setIndex(this.getIndex());
        aiMessage.setStatus(this.getStatus());
        aiMessage.setPromptTokens(this.getPromptTokens());
        aiMessage.setCompletionTokens(this.getCompletionTokens());
        aiMessage.setTotalTokens(this.getTotalTokens());
        aiMessage.setFullContent(this.getFullContent());
        aiMessage.setPromptTokens(this.getPromptTokens());
        aiMessage.setCalls(functionCalls);

        return aiMessage;
    }
}
