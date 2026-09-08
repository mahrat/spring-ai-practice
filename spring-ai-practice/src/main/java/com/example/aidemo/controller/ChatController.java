package com.example.aidemo.controller;

import com.example.aidemo.model.ChatRequest;
import com.example.aidemo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public String chat(@RequestBody ChatRequest req) {
        if (req.getUserId() == null) {
            req.setUserId("default");
        }
        String reply = chatService.chat(req.getUserId(), req.getMessge());
        return reply;
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @PostMapping("/quick")
    public String quickChat(@RequestParam String msg) {
        return chatService.simplePrompt(msg);
    }

    @DeleteMapping("/history/{userId}")
    public void clear(@PathVariable String userId) {
        chatService.clearHistory(userId);
    }
}
