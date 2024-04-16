package org.example.Services;

import org.example.Dto.Impl.TotalActivitiesListImpl;
import org.example.Dto.Impl.TotalDestinationsListImpl;
import org.example.Dto.Impl.TotalPackageListImpl;
import org.example.Dto.TotalActivitiesList;
import org.example.Dto.TotalDestinationsList;
import org.example.Dto.TotalPackageList;
import org.example.Models.Activity;
import org.example.Models.Destination;
import org.example.Models.Package;
import org.example.Models.Passenger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class PackageManager {

    private final TotalPackageList totalPackageList;
    private final TotalDestinationsList totalDestinationsList;
    private final TotalActivitiesList totalActivitiesList;

    public PackageManager() {
        totalPackageList = new TotalPackageListImpl();
        totalDestinationsList = new TotalDestinationsListImpl();
        totalActivitiesList = new TotalActivitiesListImpl();
    }

    public void addPackage(String packageName, int passengerCapacity,
                           List<Destination> destinations) {

        Package aPackage = new Package(packageName, passengerCapacity);
        aPackage.getItinerary().addAll(destinations);

        totalPackageList.addPackages(aPackage);
    }


    public List<Package> getPackagesList() {
        return totalPackageList.getPackagesList();
    }
    public List<Destination> getDestinationList() {
        return totalDestinationsList.getDestinationsList();
    }
    public List<Activity> getActivityList() {
        return totalActivitiesList.getActivitiesList();
    }

    public boolean isPassengerCapacityFull(Package aPackage) {
        return aPackage.getPassengerCapacity() <= 0;
    }


    public void addPassengerToPackage(Package aPackage, Passenger passenger) {
        if (isPassengerCapacityFull(aPackage)) {
            //System.out.println("passenger capacity is full, new passenger cannot be added");
            throw new RuntimeException("passenger capacity is full, new passenger cannot be added");
        }
        aPackage.getPassengerList().add(passenger);
        aPackage.setPassengerCapacity(aPackage.getPassengerCapacity() - 1);
    }

    public void addDestinationToPackage(Package aPackage, Destination destination) {
        aPackage.getItinerary().add(destination);
    }

    public boolean isActivityCapacityFull(String activityName){

        Activity activity = totalActivitiesList.getActivitiesList().stream().
                filter(e -> e.getActivityName().equals(activityName)).findAny().orElseThrow();
        return activity.getCapacity() <= 0;
    }

    public  Activity getAnActivity(String activity) {
        return totalActivitiesList.getActivitiesList().stream().
                filter(e -> e.getActivityName().equals(activity)).findAny().orElse(null);
    }

    public Destination getDestinationByName(String name) {
        return totalDestinationsList.getDestinationsList().stream()
                .filter(p -> p.getDestinationName().equals(name)).findFirst().orElse(null);}


    public Package getPackageByName(String packageName) {
        return totalPackageList.getPackagesList().stream()
                .filter(p -> p.getPackageName().equals(packageName)).findFirst().orElse(null);
    }

}
