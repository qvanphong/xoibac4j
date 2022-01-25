package tech.qvanphong.xoibac4j;

import tech.qvanphong.xoibac4j.model.Bet;
import tech.qvanphong.xoibac4j.model.BetValue;
import tech.qvanphong.xoibac4j.model.Bettor;

import java.util.*;

public class BetUtility {
//    private static List<Bettor> bets = Collections.synchronizedList(new ArrayList<>());
    public static boolean isRolling = false;
    public static boolean isOnGame = false;

//    public static void makeBet(Bettor bettor, BetValue betValue, double amount) {
//        if (bets.contains(bettor)) {
//            int index = bets.indexOf(bettor);
//            Bettor existingBettor = bets.get(index);
//            if (existingBettor.hadBet(betValue)) {
//                existingBettor.getBets().removeIf(bet -> bet.getBetValue().equals(betValue));
//            }
//            Bet bet = new Bet(betValue, amount);
//            existingBettor.getBets().add(bet);
//        } else {
//            Bet bet = new Bet(betValue, amount);
//            bettor.getBets().add(bet);
//
//            bets.add(bettor);
//        }
//    }
//
//    public static void clearBets() {
//        bets.clear();
//    }
//
//    public static boolean hasBet() { return !bets.isEmpty(); }
//
//    public static Set<Bettor> getWinners(List<BetValue> selectedBet) {
//        Set<Bettor> bettors = new HashSet<>();
//        for (Bettor bettor : bets) {
//            ArrayList<Bet> existingBets = bettor.getBets();
//            for (Bet existingBet : existingBets) {
//                int frequency = Collections.frequency(selectedBet, existingBet.getBetValue());
//                if (frequency == 0) {
//                    bettor.setTotalWin(bettor.getTotalWin() - existingBet.getAmount());
//                } else {
//                    bettor.setTotalWin(bettor.getTotalWin() + existingBet.getAmount() * frequency);
//                }
//            }
//            bettors.add(bettor);
//        }
//
//        return bettors;
//    }
}
