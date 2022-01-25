package tech.qvanphong.xoibac4j.command;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class TestCommand implements SlashCommand{
    @Override
    public String getName() {
        return "test";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return event.deferReply()
                .then(event.editReply("Xin chào"))
                .then()
                .doOnError(throwable -> {
                    System.out.println(throwable.getMessage());
                });
    }
}
