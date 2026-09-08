package com.example.aidemo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AskController {

    @Autowired
    private ChatClient.Builder builder;

    @Autowired
    private VectorStore vectorStore;

    @GetMapping("/ask")
    public String ask(@RequestParam String q) {
        List<Document> results = vectorStore.similaritySearch(
                SearchRequest.query(q).withTopK(3)
        );

        String context = results.stream()
                .map(doc -> doc.getContent())
                .collect(Collectors.joining("\n---\n"));

        String finalPrompt = "You are a helpful assistant. Use the context below to answer the question, "
                + "if the answer isn't in the context just say you dont know.\n\n"
                + "Context:\n" + context + "\n\nQuestion: " + q;

        ChatClient client = builder.build();

        String answer = client.prompt()
                .user(finalPrompt)
                .call()
                .content();

        return answer;
    }
}
