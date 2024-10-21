package com.lang.engquiz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatGptRequest {
    private String model;
    private List<Message> messages;
    private int max_tokens;
    private float temperature;
}
