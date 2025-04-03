package cn.bupt.dssc.config;


import com.agentsflex.llm.openai.OpenAILlm;
import com.agentsflex.llm.openai.OpenAILlmConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LlmApiConfig {

    @Bean
    public OpenAILlmConfig openAILlmConfig() {
        OpenAILlmConfig config = new OpenAILlmConfig();
        config.setEndpoint("https://chatapi.littlewheat.com");
        config.setApiKey("sk-A8HeUFy7TeTLNyfeeDN2xO7XSnyzSLWUyVBmOBfVWUz4F3Wv");
        config.setModel("gpt-4o-mini");
        return config;
    }

    @Bean
    public OpenAILlm openAILlm(OpenAILlmConfig config) {
        OpenAILlm openAILlm = new OpenAILlm(config);
        return openAILlm;
    }
}
