package com.lang.engquiz.web;

import com.lang.engquiz.service.ChatGptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chatgpt")
public class ChatGptController {

    private final ChatGptService chatGptService;

    public ChatGptController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    @GetMapping("/quiz")
    public ResponseEntity<String> getQuiz() {
        // 서비스에서 퀴즈 생성 후 클라이언트에 반환
        String quiz = chatGptService.getQuizQuestion();
        return ResponseEntity.ok(quiz);
    }
}
