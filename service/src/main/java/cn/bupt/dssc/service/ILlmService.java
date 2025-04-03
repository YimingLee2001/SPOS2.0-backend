package cn.bupt.dssc.service;

import com.agentsflex.core.message.AiMessage;
import com.agentsflex.core.prompt.Prompt;

public interface ILlmService {

    String simpleChat(String msg);

    AiMessage chat(Prompt prompt);
}
