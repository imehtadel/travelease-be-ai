package com.popeye.rest.webservices.restfulwebservices.controller;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.*;
import com.azure.core.credential.AzureKeyCredential;
import com.popeye.rest.webservices.restfulwebservices.model.Event;
import com.popeye.rest.webservices.restfulwebservices.model.Hotel;
import com.popeye.rest.webservices.restfulwebservices.model.request.ChatRequest;
import com.popeye.rest.webservices.restfulwebservices.model.request.ChatResponse;
import com.popeye.rest.webservices.restfulwebservices.model.request.HotelInfo;
import com.popeye.rest.webservices.restfulwebservices.repository.EventRepository;
import com.popeye.rest.webservices.restfulwebservices.repository.HotelRepository;
import com.popeye.rest.webservices.restfulwebservices.service.ChatRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    EventRepository eventRepository;
    @Autowired
    HotelRepository hotelRepository;

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
        if(null != chatRequest.getMessage()) {
            String prompt = createPrompt(chatRequest);
            chatMessages.add(new ChatRequestUserMessage(prompt));
            ChatCompletionsOptions options = new ChatCompletionsOptions(chatMessages).setDataSources(Arrays.asList(searchConfig));

            response = openAIClient.getChatCompletions("popeye-azure-openai", options);

        }
        if(null != response && !CollectionUtils.isEmpty(response.getChoices()) && null != response.getChoices().get(0).getMessage()){
            chatResponse.setResponse(response.getChoices().get(0).getMessage().getContent());
        } else{
            chatResponse.setResponse("Could you please provide more details on this");
        }

        return chatResponse;
    }

    private String createPrompt(ChatRequest chatRequest) {
        StringBuilder prompt = new StringBuilder();
        String promptContext = "Your role is itinerary planner. ";
        prompt.append(promptContext);
        String promptAudience = "Plan itinerary for " + chatRequest.getNoOfPerson() + " guest who is travelling to attend event or events. ";
        prompt.append(promptAudience);

        List<Integer> eventIds = chatRequest.getEventIds();
        eventIds.stream().forEach(e -> {
            Optional<Event> event = eventRepository.findById(e.longValue());
            if(event.isPresent() && null != event){
                Event guestEvent = event.get();
                String promptAudienceEventDetails = "Guest wants to explore the city, its food, culture, nature and enjoy the " + guestEvent.getName() +
                        ". Guest will attend event " + guestEvent.getName() + ", " + guestEvent.getDescription() + " at " +
                        guestEvent.getCountry() + ", " + guestEvent.getState() + ", " +
                        guestEvent.getZipCode() + ", " + guestEvent.getTimeZone() + " in the timeZone. ";
                prompt.append(promptAudienceEventDetails);
            }
        });

        List<HotelInfo> hotelInfos = chatRequest.getHotelInfo();
        hotelInfos.forEach(hotelInfo -> {
            Optional<Hotel> h = hotelRepository.findById(hotelInfo.getHotelId().longValue());
            if(h.isPresent() && null != h){
                Hotel hotel = h.get();
                String promptAudienceHotelDetails = "Guest will reside in hotel from checkIn on " + hotelInfo.getCheckIn() +" to checkOut on " +
                        hotelInfo.getCheckOut() + " at " + hotel.getName() + ", " +
                        hotel.getCountry() +", " + hotel.getState() + ", " + hotel.getZipCode() + ". ";
                prompt.append(promptAudienceHotelDetails);
            }
        });
        String promptIntent = "Consider the event time zone while creating the itinerary and include hotel activities in the itinerary " +
                "so that guest could enjoy, explore, and experience the city, its architecture, food and culture.";
        prompt.append(promptIntent);
        if(!StringUtils.isEmpty(chatRequest.getMessage())) prompt.append(chatRequest.getMessage());

        return prompt.toString();
    }
}
