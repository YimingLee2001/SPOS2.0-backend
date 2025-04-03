package cn.bupt.dssc.domain.po;

import com.agentsflex.core.llm.functions.Function;
import com.agentsflex.core.message.AbstractTextMessage;
import com.agentsflex.core.message.HumanMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Accessors(chain = true)
public class HumanMessagePO extends AbstractTextMessage {
    private String toolChoice;

    public HumanMessagePO(HumanMessage humanMessage) {
        // 父类的赋值
        this.setMetadataMap(humanMessage.getMetadataMap());
        this.setContent(humanMessage.getContent());

        // 子类的赋值
        this.toolChoice = humanMessage.getToolChoice();
    }

    public HumanMessage toHumanMessage(List<Function> functions) {
        HumanMessage humanMessage = new HumanMessage();

        // 父类的赋值
        humanMessage.setMetadataMap(this.getMetadataMap());
        humanMessage.setContent(this.getContent());

        // 子类的赋值
        humanMessage.setToolChoice(this.getToolChoice());
        humanMessage.setFunctions(functions);

        return humanMessage;
    }
}
