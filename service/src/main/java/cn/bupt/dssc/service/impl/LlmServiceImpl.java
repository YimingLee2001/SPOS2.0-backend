package cn.bupt.dssc.service.impl;

import cn.bupt.dssc.service.ILlmService;
import com.agentsflex.core.llm.Llm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LlmServiceImpl implements ILlmService {

    private final Llm llm;

    @Override
    public String simpleChat(String msg) {
        return llm.chat(msg);
    }
}
