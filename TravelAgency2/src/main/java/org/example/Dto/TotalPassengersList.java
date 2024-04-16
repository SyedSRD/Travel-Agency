package org.example.Dto;

import org.example.Models.Passenger;

import java.util.ArrayList;

public interface TotalPassengersList {

    public  void addPassengers(Passenger passengers);

    public ArrayList<Passenger> getPassengersList();


}
