package cn.bupt.dssc.controller;

import cn.bupt.dssc.domain.dto.UserMessageDTO;
import cn.bupt.dssc.service.ILlmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "大语言模型管理接口")
@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class LlmChatController {
    private final ILlmService llmService;

    @ApiOperation("与大模型单次对话")
    @PostMapping("/simple")
    public String simpleChat(@RequestBody UserMessageDTO msg) {
        return llmService.simpleChat(msg.getMsg());
    }
}
