package com.popeye.rest.webservices.restfulwebservices.controller;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.*;
import com.azure.core.credential.AzureKeyCredential;
import com.popeye.rest.webservices.restfulwebservices.model.request.ChatRequest;
import com.popeye.rest.webservices.restfulwebservices.model.request.ChatResponse;
import com.popeye.rest.webservices.restfulwebservices.service.ChatRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class ChatController {
	
	@Value("${azure.openai.url}")
	private String openAIUrl;

	@Value("${azure.openai.key}")
	private String openAIKey;

	@Value("${azure.openai.search.url}")
	private String openAISearchUrl;

	@Value("${azure.openai.search.key}")
	private String openAISearchKey;

	@Value("${azure.openai.search.csr.index}")
	private String openAISearchCSRIndex;

    @Autowired
    ChatRestService chatRestService;

    @PostMapping(value = "/chat")
    public ChatResponse getChatModels(@RequestBody ChatRequest chatRequest) {

    	OpenAIClient openAIClient = new OpenAIClientBuilder().endpoint(openAIUrl)
    	        .credential(new AzureKeyCredential(openAIKey))
    	        .buildClient();

    	AzureCognitiveSearchChatExtensionConfiguration searchConfig = new AzureCognitiveSearchChatExtensionConfiguration(
    	        new AzureCognitiveSearchChatExtensionParameters(openAISearchUrl, openAISearchCSRIndex)
    	                .setAuthentication(new OnYourDataApiKeyAuthenticationOptions(openAISearchKey))
    	                .setQueryType(AzureCognitiveSearchQueryType.SIMPLE) // SIMPLE, VECTOR, or Hybrid
    	                .setInScope(true)
    	                .setTopNDocuments(2));
    	        
        List<ChatRequestMessage> chatMessages = new ArrayList<>();
        ChatCompletions response = null;
        ChatResponse chatResponse = new ChatResponse();
        if(null != chatRequest.getChatRequestMessage()) {
            chatMessages.add(new ChatRequestUserMessage(chatRequest.getChatRequestMessage()));
            ChatCompletionsOptions options = new ChatCompletionsOptions(chatMessages).setDataSources(Arrays.asList(searchConfig));

            response = openAIClient.getChatCompletions("popeye-azure-openai", options);
            System.out.println("Response ID:" + response.getId());
            System.out.println("Response created at:" + response.getCreatedAt());
            System.out.println("Response role:" + response.getChoices().get(0).getMessage().getRole());
            System.out.println("Response choices:" + response.getChoices().get(0).getMessage().getContent());

        }
        if(null != response && !CollectionUtils.isEmpty(response.getChoices()) && null != response.getChoices().get(0).getMessage()){
            chatResponse.setResponse(response.getChoices().get(0).getMessage().getContent());
        } else{
            chatResponse.setResponse("Could you please provide more details on this");
        }

        return chatResponse;
    }
}
