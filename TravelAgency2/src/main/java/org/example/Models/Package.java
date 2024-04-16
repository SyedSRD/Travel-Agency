package org.example.Models;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Package {

    @NotNull
    private String packageName;
    @NotNull
    private int passengerCapacity;

    private ArrayList<Destination> itinerary;
    private ArrayList<Passenger> passengerList;

    public Package(String packageName, int passengerCapacity) {
        this.packageName = packageName;
        this.passengerCapacity = passengerCapacity;

        itinerary = new ArrayList<>();
        passengerList = new ArrayList<>();
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public ArrayList<Destination> getItinerary() {
        return itinerary;
    }

    public ArrayList<Passenger> getPassengerList() {
        return passengerList;
    }

}
