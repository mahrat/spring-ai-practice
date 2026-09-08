package com.example.aidemo.controller;

import com.example.aidemo.model.DocRequest;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DocumentController {

    @Autowired
    private VectorStore vectorStore;

    @PostMapping("/api/docs")
    public String addDoc(@RequestBody DocRequest req) {
        String[] chunks = req.getContent().split("\\.");

        List<Document> docs = new ArrayList<>();
        for (String chunk : chunks) {
            String trimmed = chunk.trim();
            if (trimmed.length() < 5) {
                continue;
            }
            Map<String, Object> meta = new HashMap<>();
            meta.put("title", req.getTitle());
            docs.add(new Document(trimmed, meta));
        }

        vectorStore.add(docs);
        System.out.println("added " + docs.size() + " chunks for " + req.getTitle());
        return docs.size() + " chunks added";
    }
}
