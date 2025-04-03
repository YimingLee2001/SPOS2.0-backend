package cn.bupt.dssc.service.impl;

import cn.bupt.dssc.common.exception.LlmException;
import cn.bupt.dssc.service.ILlmService;
import com.agentsflex.core.llm.Llm;
import com.agentsflex.core.llm.response.AiMessageResponse;
import com.agentsflex.core.message.AiMessage;
import com.agentsflex.core.prompt.Prompt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LlmServiceImpl implements ILlmService {

    private final Llm llm;

    @Override
    public String simpleChat(String msg) {
        return llm.chat(msg);
    }

    @Override
    public AiMessage chat(Prompt prompt) {
        AiMessageResponse response = llm.chat(prompt);
        if (response.isError()) {
            throw new LlmException("大模型调用出错");
        }

        return response.getMessage();
    }
}
