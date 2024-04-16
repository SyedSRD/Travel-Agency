package org.example.Services;

import org.example.Dto.Impl.TotalPassengersListImpl;
import org.example.Dto.TotalPassengersList;
import org.example.Models.Passenger;

import java.util.ArrayList;
import java.util.List;


public class PassengerManager {
    private final TotalPassengersList totalPassengersList ;

    public PassengerManager() {
        totalPassengersList = new TotalPassengersListImpl();
    }

    public void addPassenger(String passengerName, String id ){
        totalPassengersList.addPassengers(new Passenger(passengerName,id));
    }
    public ArrayList<Passenger> getPassengerList(){
        return totalPassengersList.getPassengersList();
    }


    public Passenger getPassenger(String passengerNumber) {
        return totalPassengersList.getPassengersList().stream().filter( p -> p.getPassengerNumber().equals(passengerNumber))
                .findAny().orElse(null);
    }



}
