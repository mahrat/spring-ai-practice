package com.example.aidemo.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatService {

    private ChatClient chatClient;

    private Map<String, List<Message>> history = new HashMap<>();

    @Autowired
    public ChatService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String chat(String userId, String message) {
        List<Message> userHistory = history.get(userId);
        if (userHistory == null) {
            userHistory = new ArrayList<>();
            history.put(userId, userHistory);
        }

        userHistory.add(new UserMessage(message));

        String response = "";
        try {
            Prompt prompt = new Prompt(userHistory);
            response = chatClient.prompt(prompt)
                    .call()
                    .content();
        } catch (Exception e) {
            e.printStackTrace();
            response = "something went wrong, try again";
        }

        return response;
    }

    public void clearHistory(String userId) {
        history.remove(userId);
    }

    public String simplePrompt(String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }
}
