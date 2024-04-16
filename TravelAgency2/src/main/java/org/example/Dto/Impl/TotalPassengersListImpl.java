package org.example.Dto.Impl;

import org.example.Dto.TotalPassengersList;
import org.example.Models.Passenger;

import java.util.ArrayList;

public class TotalPassengersListImpl implements TotalPassengersList {

    private static ArrayList<Passenger> passengersList ;

    public TotalPassengersListImpl(){
        passengersList = new ArrayList<>();
    }
    @Override
    public  void addPassengers(Passenger passengers) {
        passengersList.add(passengers);

    }

    @Override
    public  ArrayList<Passenger> getPassengersList() {
        return passengersList;
    }


}
