package com.lang.engquiz.web;

import com.lang.engquiz.domain.Quiz;
import com.lang.engquiz.service.ChatGptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chatgpt")
public class ChatGptController {
    private static final Logger logger = LoggerFactory.getLogger(ChatGptController.class);
    private final ChatGptService chatGptService;

    public ChatGptController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    // 퀴즈 생성 API
    @GetMapping("/generate")
    public ResponseEntity<Quiz> generateQuiz() {
        String question = chatGptService.generateQuiz();
        Quiz quiz = new Quiz();
        quiz.setQuestion(question);
        logger.info(String.valueOf(ResponseEntity.ok(quiz)));
        return ResponseEntity.ok(quiz);
    }

    // 퀴즈 정답 확인 API
    @PostMapping("/check")
    public ResponseEntity<Quiz> checkAnswer(@RequestParam("quiz") String quiz, @RequestParam("answer") String answer) {
        logger.info("Check quiz: " +quiz,"Check answer: " + answer);
        String result = chatGptService.checkAnswer(quiz, answer);
        Quiz qz = new Quiz();
        qz.setCorrectAnswer(result);
        return ResponseEntity.ok(qz);
    }
}
