package tech.qvanphong.xoibac4j.command;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import tech.qvanphong.xoibac4j.BetService;
import tech.qvanphong.xoibac4j.BetUtility;

@Component
public class NewCommand implements  SlashCommand {
    private final BetService betService;

    public NewCommand(BetService betService) {
        this.betService = betService;
    }

    @Override
    public String getName() {
        return "batdau";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        if (BetUtility.isRolling)
            return event.reply("https://media.discordapp.net/stickers/881690336261451836.webp?size=160").then();

        BetUtility.isOnGame = true;
        return betService.clearBets()
                .then(event.reply("<:soyboy2:898561790592548925> Mở bát cược mới, các con bạc đặt cược bằng lệnh `/dat`\nhttps://media.discordapp.net/attachments/932640939917332540/934361335901347860/unknown.png"));
    }
}
