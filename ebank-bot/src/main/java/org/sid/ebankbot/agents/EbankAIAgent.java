package org.sid.ebankbot.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class EbankAIAgent {

    private ChatClient chatClient;

    public EbankAIAgent(ChatClient.Builder chatClient,
                        ChatMemory chatMemory,
                        ToolCallbackProvider tools) {
        this.chatClient = chatClient
                .defaultSystem("""
                        Vous un assistant qui se charge de répondre aux question de l'utilisateur en fonction du contexte fourni. Si aucun contexte n'est fourni, répond avec JE NE PAS VOUS REPONDRE A CE GENRE DE QUETION
                        """)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultTools(tools)
                .build();
    }


    public String chat(Prompt prompt, String conversationId){
        System.out.println("conversationId : "+conversationId);
        return chatClient.prompt(prompt)
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, conversationId))
                .call().content();
    }
}
