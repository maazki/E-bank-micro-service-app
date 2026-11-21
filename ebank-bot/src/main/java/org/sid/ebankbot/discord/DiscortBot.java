package org.sid.ebankbot.discord;

import com.zgamelogic.discord.annotations.DiscordController;
import com.zgamelogic.discord.annotations.DiscordMapping;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.sid.ebankbot.agents.EbankAIAgent;
import org.springframework.ai.chat.prompt.Prompt;

@DiscordController
public class DiscortBot {
    private EbankAIAgent ebankAIAgent;

    public DiscortBot(EbankAIAgent ebankAIAgent) {
        this.ebankAIAgent = ebankAIAgent;
    }

    @DiscordMapping
    private void perform(MessageReceivedEvent event){
        System.out.println("perform event : "+event);
        if (event.getAuthor().isBot()) return;
        String query = event.getMessage().getContentRaw();
        String response = ebankAIAgent.chat(new Prompt(query),"");
        event.getChannel().sendMessage(response).queue();
    }
}
