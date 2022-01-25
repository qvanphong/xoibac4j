package tech.qvanphong.xoibac4j;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.guild.GuildCreateEvent;
import discord4j.core.event.domain.guild.GuildDeleteEvent;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.rest.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.qvanphong.xoibac4j.listener.SlashCommandListener;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public RestClient restClient() {
        return RestClient.create("TOKEN");
    }

}
