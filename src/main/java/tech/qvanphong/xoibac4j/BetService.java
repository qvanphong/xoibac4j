package tech.qvanphong.xoibac4j;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import tech.qvanphong.xoibac4j.model.Bet;
import tech.qvanphong.xoibac4j.model.BetValue;
import tech.qvanphong.xoibac4j.model.Bettor;

import java.util.*;

@Component
public class BetService {
    private final BetRepository betRepository;

    public BetService(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    public Mono<Bettor> makeBet(Bettor bettor, BetValue betValue, double amount) {
        return betRepository.findBettorById(bettor.getId())
                .switchIfEmpty(betRepository.save(bettor))
                .flatMap(savedBettor -> {
                    savedBettor.getBets().removeIf(bet -> bet.getBetValue().equals(betValue));

                    Bet bet = new Bet(betValue, amount);
                    savedBettor.getBets().add(bet);

                    return betRepository.save(savedBettor);
                });
    }

    public Mono<Void> clearBets() {
        return betRepository.deleteAll();
    }

    public Mono<Boolean> hasBet() {
        return betRepository.count().map(amount -> amount > 0);
    }

    public Mono<Set<Bettor>> getBetResult(List<BetValue> selectedBet) {
        Set<Bettor> bettorsResult = new HashSet<>();
        return betRepository.findAll()
                .map(bettor -> {
                    ArrayList<Bet> betsInRound = bettor.getBets();
                    for (Bet betInRound : betsInRound) {
                        int frequency = Collections.frequency(selectedBet, betInRound.getBetValue());
                        if (frequency == 0) {
                            bettor.setTotalWin(bettor.getTotalWin() - betInRound.getAmount());
                        } else {
                            bettor.setTotalWin(bettor.getTotalWin() + betInRound.getAmount() * frequency);
                        }
                    }
                    bettorsResult.add(bettor);
                    return bettor;
                })
                .then(Mono.just(bettorsResult));
    }
}
