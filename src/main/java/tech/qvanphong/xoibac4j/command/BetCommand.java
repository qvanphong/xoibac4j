package tech.qvanphong.xoibac4j.command;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.spec.InteractionCallbackSpec;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import tech.qvanphong.xoibac4j.BetService;
import tech.qvanphong.xoibac4j.BetUtility;
import tech.qvanphong.xoibac4j.model.BetValue;
import tech.qvanphong.xoibac4j.model.Bettor;

@Component
public class BetCommand implements SlashCommand{
    private final BetService betService;

    public BetCommand(BetService betService) {
        this.betService = betService;
    }

    @Override
    public String getName() {
        return "dat";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return event.deferReply()
                .flatMap(unused -> {
                    System.out.println("Hello");
                    return Mono.empty();
                });

//        if (BetUtility.isRolling)
//            return event.reply("https://media.discordapp.net/stickers/881690336261451836.webp?size=160").then();
//
//        if (!BetUtility.isOnGame)
//            return event.reply("Chưa có cái, ai muốn làm cái gõ /batdau");
//
//        String userId = event.getInteraction().getUser().getId().asString();
//        String userName = event.getInteraction().getUser().getUsername();
//        String betName = event.getOption("bet").get().getValue().get().asString();
//        double amount = event.getOption("amount").get().getValue().get().asDouble();
//
//        return event.deferReply()
//                .then(betService.makeBet(new Bettor(userId, userName), BetValue.valueOf(betName), amount))
//                .flatMap(bettor -> event.editReply("<:conganwoke:828370046208311296> **<@!" + userId + "> đã đặt " + BetValue.valueOf(betName).getName() + " " + amount + "**"))
//                .then();
    }
}
