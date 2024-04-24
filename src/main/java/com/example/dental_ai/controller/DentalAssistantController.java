package com.example.dental_ai.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dental")
@RequiredArgsConstructor
public class DentalAssistantController {
    private final OpenAiChatClient chatClient;


    @GetMapping("/tips")
    public String dentalChat(@RequestParam(value = "message",
    defaultValue = "Quais as 5 melhores dicas sobre higiene bucal? ") String message,
    @RequestParam(value = "namePatient", defaultValue = "Patient") String name){
        return name + ". " + chatClient.call(message);
    }
    @GetMapping("/cares")
    public String bookstoreReview(@RequestParam(value = "treatment",
            defaultValue = "braces") String treatment) {
        PromptTemplate promptTemplate = new PromptTemplate("""
                Por favor, me fale sobre os cuidados que devo ter com {treatment}
                [ limite-se a 5 cuidados]
                """);
        promptTemplate.add("treatment", treatment);

        return this.chatClient.call(promptTemplate.create()).getResult().getOutput().getContent();
    }


}

