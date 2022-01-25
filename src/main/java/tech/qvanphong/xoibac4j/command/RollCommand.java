package tech.qvanphong.xoibac4j.command;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.core.spec.legacy.LegacyEmbedCreateSpec;
import discord4j.rest.util.Color;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import tech.qvanphong.xoibac4j.BetService;
import tech.qvanphong.xoibac4j.BetUtility;
import tech.qvanphong.xoibac4j.model.Bet;
import tech.qvanphong.xoibac4j.model.BetValue;
import tech.qvanphong.xoibac4j.model.Bettor;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

import static tech.qvanphong.xoibac4j.model.BetValue.*;

@Component
public class RollCommand implements SlashCommand {
    private final BetService betService;

    public RollCommand(BetService betService) {
        this.betService = betService;
    }

    @Override
    public String getName() {
        return "xoibac";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        if (BetUtility.isRolling)
            return event.reply("https://media.discordapp.net/stickers/881690336261451836.webp?size=160").then();

        if (!BetUtility.isOnGame) return event.reply("Chưa có cái, ai muốn làm cái gõ /batdau");


        return event.deferReply()
                .then(betService.hasBet()).flatMap(hasBet -> {
                    if (!hasBet) {
                        return event.editReply("Chưa có ai đặt cược, kéo người lên đi <:camnin:828369142649192478>").then();
                    } else {
                        BetValue[] bets = {DUC_MEO, FAN_CAO, NUA, HUONG, PILL, VIET_MY};
                        List<BetValue> selected = new ArrayList<>();
                        BetUtility.isRolling = true;

                        return event.editReply("<:soyboy2:898561790592548925> Giờ lành đã điểm, các con nghiện buông đôi tay ra nào")
                                .thenMany(event.getInteraction().getChannel().flatMap(messageChannel -> {
                                            BetValue selectedBet = bets[new Random().nextInt(bets.length)];
                                            selected.add(selectedBet);

                                            EmbedCreateSpec embedMessage = EmbedCreateSpec.create().withColor(Color.MOON_YELLOW).withTitle("Kết quả xới bạc lần " + selected.size()).withDescription(selectedBet.getName());

                                            return messageChannel.createMessage(embedMessage);
                                        })
                                        .then()
                                        .delaySubscription(Duration.ofSeconds(3))
                                        .repeat(2))
                                .then(betService.getBetResult(selected))
                                .flatMap(bettors -> event.getInteraction()
                                        .getChannel()
                                        .flatMap(messageChannel -> {
                                            StringBuilder messageContentBuilder = new StringBuilder("**Kết quả**\n");
                                            BetUtility.isRolling = false;
                                            BetUtility.isOnGame = false;

                                            if (bettors.isEmpty()) {
                                                return messageChannel.createMessage("**Kết quả** \nKhông ai thắng ván này").then();
                                            }

                                            for (Bettor winner : bettors) {
                                                messageContentBuilder.append("<@!").append(winner.getId()).append(winner.getTotalWin() >= 0 ? "> đã thắng " : "> đã thua ").append(winner.getTotalWin()).append("\n");
                                            }
                                            String result = selected.stream().map(BetValue::getName).collect(Collectors.joining("\n"));

                                            return betService.clearBets()
                                                    .then(messageChannel
                                                            .createMessage(EmbedCreateSpec.builder().color(Color.MOON_YELLOW).title("Kết quả").description(result).build())
                                                            .then(messageChannel.createMessage(messageContentBuilder.toString())))
                                                    .then();
                                        })
                                        .then())
                                .onErrorResume(throwable -> betService.clearBets());
                    }
                });


    }
}
