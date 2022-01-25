package tech.qvanphong.xoibac4j.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document("bets")
public class Bettor {
    @Id
    private String id;

    private String name;

    private double totalWin = 0;

    private ArrayList<Bet> bets = new ArrayList<>();

    public Bettor(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Bettor() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalWin() {
        return totalWin;
    }

    public void setTotalWin(double totalWin) {
        this.totalWin = totalWin;
    }

    public ArrayList<Bet> getBets() {
        return bets;
    }

    public void setBets(ArrayList<Bet> bets) {
        this.bets = bets;
    }

    public boolean hadBet(BetValue betValue) {
        for (Bet bet : bets) {
            if (bet.getBetValue().equals(betValue)) return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Bettor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", totalWin=" + totalWin +
                ", bets=" + bets +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bettor bettor = (Bettor) o;
        return Objects.equals(id, bettor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
