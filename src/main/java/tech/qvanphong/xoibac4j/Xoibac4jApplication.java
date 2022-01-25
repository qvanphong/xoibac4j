package tech.qvanphong.xoibac4j;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.qvanphong.xoibac4j.listener.SlashCommandListener;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class Xoibac4jApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext springContext = SpringApplication.run(Xoibac4jApplication.class, args);
		String token = "TOKEN";
		// Login
		DiscordClientBuilder.create(token).build()
				.gateway()
				.withEventDispatcher(eventDispatcher -> {
					SlashCommandListener slashCommandListener = new SlashCommandListener(springContext);

					Flux<Void> slashCommandFlux = eventDispatcher
							.on(ChatInputInteractionEvent.class)
							.flatMap(slashCommandListener::handle);

					return Mono.when(slashCommandFlux);
				})
				.login()
				.block();
	}

}
