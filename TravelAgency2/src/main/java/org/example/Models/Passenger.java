package org.example.Models;

import org.example.Enum.PassengerCategory;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class Passenger {

    @NotNull
    private String passengerName;
    @NotNull
    private final String passengerNumber;  // we can also use UUID
    private int balance;
    private PassengerCategory passengerCategory;

    public Passenger(String passengerName, String passengerNumber, PassengerCategory passengerCategory, int balance){
        this.passengerName = passengerName;
        this.passengerNumber = passengerNumber;
        this.passengerCategory = passengerCategory;
        this.balance = balance;
    }

    public Passenger(String passengerName, String passengerNumber) {
        this.passengerName = passengerName;
        this.passengerNumber = passengerNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }

//    public void setPassengerName(String passengerName) {
//        this.passengerName = passengerName;
//    }

    public String getPassengerNumber() {
        return passengerNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public PassengerCategory getPassengerCategory() {
        return passengerCategory;
    }

    public void setPassengerCategory(PassengerCategory passengerCategory) {
        this.passengerCategory = passengerCategory;
    }
}
