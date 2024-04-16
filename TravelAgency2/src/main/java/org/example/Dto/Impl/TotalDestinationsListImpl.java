package org.example.Dto.Impl;

import org.example.Dto.TotalDestinationsList;
import org.example.Models.Destination;

import java.util.ArrayList;

public class TotalDestinationsListImpl implements TotalDestinationsList {

    public static ArrayList<Destination> destinationsList;

    public TotalDestinationsListImpl() {
        destinationsList = new ArrayList<>();
    }
    @Override
    public void addDestinations(Destination destination) {
        destinationsList.add(destination);
    }

    @Override
    public ArrayList<Destination> getDestinationsList() {
        return destinationsList;
    }


}
