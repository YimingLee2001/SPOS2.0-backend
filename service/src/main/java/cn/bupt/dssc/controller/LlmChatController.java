package cn.bupt.dssc.controller;

import cn.bupt.dssc.domain.dto.UserMessageDTO;
import cn.bupt.dssc.domain.po.AiMessagePO;
import cn.bupt.dssc.domain.po.DatabaseChatMemory;
import cn.bupt.dssc.domain.po.HumanMessagePO;
import cn.bupt.dssc.domain.vo.LlmMessageVO;
import cn.bupt.dssc.service.IChatMemoryService;
import cn.bupt.dssc.service.ILlmService;
import com.agentsflex.core.memory.ChatMemory;
import com.agentsflex.core.memory.DefaultChatMemory;
import com.agentsflex.core.message.AiMessage;
import com.agentsflex.core.message.HumanMessage;
import com.agentsflex.core.message.Message;
import com.agentsflex.core.prompt.HistoriesPrompt;
import com.agentsflex.core.prompt.Prompt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = "大语言模型管理接口")
@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class LlmChatController {
    private final ILlmService llmService;
    private final IChatMemoryService memoryService;

    @ApiOperation("与大模型单次对话")
    @PostMapping("/simple")
    public String simpleChat(@RequestBody UserMessageDTO msg) {
        String msgString = msg.getHumanMessagePO().getContent();
        return llmService.simpleChat(msgString);
    }

    @ApiOperation("与大模型多轮对话")
    @PostMapping("/multiple")
    public LlmMessageVO multipleChat(@RequestBody UserMessageDTO messageDTO) {
        // 1.尝试获取本条信息的对话id和DTO
        String chatMemoryId = messageDTO.getChatMemoryId();
        HumanMessagePO humanMessagePO = messageDTO.getHumanMessagePO();

        // 2.将所属对话加载进内存
        DatabaseChatMemory chatMemory = memoryService.findById(chatMemoryId);
        if (chatMemory == null) {
            chatMemory = new DatabaseChatMemory();
            chatMemoryId = chatMemory.getId();
        }

        // 3.在历史中加入新消息
        chatMemory.addMessage(humanMessagePO);

        // 4.构造交互消息
        ChatMemory llmChatMemory = new DefaultChatMemory();
        for (Message message : chatMemory.getMessages()) {
            if (message instanceof HumanMessagePO) {
                HumanMessage humanMessageTem = ((HumanMessagePO) message).toHumanMessage(null);
                llmChatMemory.addMessage(humanMessageTem);
            } else if (message instanceof AiMessagePO) {
                AiMessage aiMessageTem = ((AiMessagePO) message).toAiMessage(null);
                llmChatMemory.addMessage(aiMessageTem);
            }
        }
        Prompt prompt = new HistoriesPrompt(llmChatMemory);

        // 5.和大模型api进行交互
        AiMessage aiMessage = llmService.chat(prompt);

        // 6.保存Ai消息
        AiMessagePO aiMessagePO = new AiMessagePO(aiMessage);
        aiMessagePO.setFullContent(aiMessage.getContent());
        chatMemory.addMessage(aiMessagePO);

        // 7.持久化进数据库
        memoryService.save(chatMemory);

        // 8.构造返回值
        LlmMessageVO llmMessageVo = new LlmMessageVO();
        llmMessageVo.setChatMemoryId(chatMemoryId);
        llmMessageVo.setAiMessagePO(aiMessagePO);
        return llmMessageVo;
    }

    @ApiOperation("根据对话id查询完整对话")
    @GetMapping("")
    public DatabaseChatMemory getChatMemoryById(@RequestParam(required = true) String id) {
        return memoryService.findById(id);
    }
}
