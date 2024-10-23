package com.lang.engquiz.service;

import com.lang.engquiz.domain.ChatGptRequest;
import com.lang.engquiz.domain.ChatGptResponse;
import com.lang.engquiz.domain.Message;
import com.lang.engquiz.openAi.OpenAiClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class ChatGptService {
    private static final Logger logger = LoggerFactory.getLogger(ChatGptService.class);

    private final OpenAiClient openAiClient;

    @Value("${openai.api.key}")
    private String apiKey; // API 키는 환경 변수 또는 application.yml에서 가져옵니다.

    @Value("${openai.model}")
    private String apiModel;

    public ChatGptService(OpenAiClient openAiClient) {
        this.openAiClient = openAiClient;
    }

    public String generateQuiz() {
        // ChatGPT API 요청을 위한 데이터 구성
        List<Message> messages = new ArrayList<>();
        String category = getRandomCategory();
        messages.add(new Message("user", "Please create a fun and simple English word quiz for a preschool child about " + category + "."));
        ChatGptRequest request = new ChatGptRequest(apiModel, messages, 50, 0.9f);

        // OpenAI API 호출
        ChatGptResponse response = openAiClient.sendMessage("Bearer " + apiKey, request);

        String quiz = response.getChoices().get(0).getMessage().getContent();

        logger.info("Generated Quiz: {}", quiz);  // 로그로 ChatGPT 응답을 기록

        // 응답에서 퀴즈 질문 추출
        return quiz;
    }

    // ChatGPT API를 호출하여 사용자의 답을 확인
    public String checkAnswer(String quiz, String answer) {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("user", "The quiz question is: '" + quiz + "'. The child answered: '" + answer + "'. Is this answer correct?"));

        ChatGptRequest request = new ChatGptRequest(apiModel, messages, 50, 0.5f);
        ChatGptResponse response = openAiClient.sendMessage("Bearer " + apiKey, request);
        String result = response.getChoices().get(0).getMessage().getContent();

        logger.info("Quiz: {}, Answer: {}, Result: {}", quiz, answer, result);  // 로그로 결과를 기록
        return result;
    }

    private List<String> categories = Arrays.asList(
            "animals", "colors", "fruits", "numbers", "shapes", "weather", "family"
    );
    private Random random = new Random();

    public String getRandomCategory() {
        int randomIndex = random.nextInt(categories.size());
        return categories.get(randomIndex);
    }
}

