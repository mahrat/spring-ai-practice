package com.example.aidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AiRagDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiRagDemoApplication.class, args);
        System.out.println("started, vector store is empty till you post some docs");
    }

}
