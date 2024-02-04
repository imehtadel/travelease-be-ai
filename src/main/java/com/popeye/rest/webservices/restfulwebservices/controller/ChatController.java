package com.popeye.rest.webservices.restfulwebservices.controller;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.ChatCompletions;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatMessage;
import com.azure.ai.openai.models.ChatRole;
import com.azure.core.credential.AzureKeyCredential;
import com.popeye.rest.webservices.restfulwebservices.service.ChatRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

public class ChatController {

    @Autowired
    ChatRestService chatRestService;

//    @GetMapping(value = "/chat/models")
//	public String getChatModels() {
//
//		OpenAIClient openAIClient = new OpenAIClientBuilder().endpoint("https://popeye-openai.openai.azure.com/")
//				.credential(new AzureKeyCredential("8bf5874340a64fadab1a75cb4f8123a9"))
//				.buildClient();
//		List<ChatMessage> chatMessages = new ArrayList<>();
//		chatMessages.add(new ChatMessage(ChatRole.SYSTEM, "You are a data science tutor"));
//		chatMessages.add(new ChatMessage(ChatRole.USER, "What is AI"));
//		ChatCompletionsOptions options = new ChatCompletionsOptions(chatMessages);
//
//		ChatCompletions response = openAIClient.getChatCompletions("popeye-azure-openai", options);
//		System.out.println("Response ID:" + response.getId());
//		System.out.println("Response created at:" + response.getCreatedAt());
//		System.out.println("Response role:" + response.getChoices().get(0).getMessage().getRole());
//		System.out.println("Response choices:" + response.getChoices().get(0).getMessage().getContent());
//		return response.getId();
//	}
}
