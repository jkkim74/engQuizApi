package com.lang.engquiz.service;

import com.lang.engquiz.domain.ChatGptRequest;
import com.lang.engquiz.domain.ChatGptResponse;
import com.lang.engquiz.domain.Message;
import com.lang.engquiz.openAi.OpenAiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatGptService {

    private final OpenAiClient openAiClient;

    @Value("${openai.api.key}")
    private String apiKey; // API 키는 환경 변수 또는 application.yml에서 가져옵니다.

    public ChatGptService(OpenAiClient openAiClient) {
        this.openAiClient = openAiClient;
    }

    public String getQuizQuestion() {
        // ChatGPT API 요청을 위한 데이터 구성
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("user", "Please create a simple, fun, and age-appropriate quiz question for a preschool child."));
        ChatGptRequest request = new ChatGptRequest("gpt-3.5-turbo", messages, 50, 0.5f);

        // OpenAI API 호출
        ChatGptResponse response = openAiClient.sendMessage("Bearer " + apiKey, request);

        // 응답에서 퀴즈 질문 추출
        return response.getChoices().get(0).getMessage().getContent();
    }
}

