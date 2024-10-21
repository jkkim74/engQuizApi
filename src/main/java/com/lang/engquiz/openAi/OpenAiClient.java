package com.lang.engquiz.openAi;

import com.lang.engquiz.domain.ChatGptRequest;
import com.lang.engquiz.domain.ChatGptResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "openai", url = "https://api.openai.com")
public interface OpenAiClient {
    @PostMapping("/v1/chat/completions")
    ChatGptResponse sendMessage(@RequestHeader("Authorization") String token, @RequestBody ChatGptRequest request);
}

