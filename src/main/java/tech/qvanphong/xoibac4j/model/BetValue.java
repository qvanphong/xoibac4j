package tech.qvanphong.xoibac4j.model;

public enum BetValue {
    HUONG("Hương"),
    NUA("Nứa"),
    PILL("Pill"),
    FAN_CAO("Falcao"),
    VIET_MY("Việt Mỹ"),
    DUC_MEO("Đức Meo");

    private String name;

    BetValue(String name) {
        this.name = name;
   }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
