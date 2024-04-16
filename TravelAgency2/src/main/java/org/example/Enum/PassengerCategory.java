package org.example.Enum;

public enum PassengerCategory {
    STANDARD("STANDARD"),
    GOLD("GOLD"),
    PREMIUM("PREMIUM");

    public final String label;

    private PassengerCategory(String label) {
        this.label = label;
    }
}
