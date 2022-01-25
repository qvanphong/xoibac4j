package tech.qvanphong.xoibac4j.model;

public class Bet {
    private BetValue betValue;
    private double amount;

    public Bet() {
    }

    public Bet(BetValue betValue, double amount) {
        this.betValue = betValue;
        this.amount = amount;
    }

    public BetValue getBetValue() {
        return betValue;
    }

    public void setBetValue(BetValue betValue) {
        this.betValue = betValue;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
