package org.example.Services.Impl;

import org.example.Enum.PassengerCategory;
import org.example.Models.*;

import org.example.Models.Package;
import org.example.Services.EnrollmentManager;
import org.example.Services.Output;
import org.example.Services.PackageManager;
import org.example.Services.PassengerManager;

import java.util.Map;

public class OutputImpl implements Output {

    private EnrollmentManager enrollmentManager;
    private PackageManager packageManager;
    private PassengerManager passengerManager;

    public OutputImpl(EnrollmentManager enrollmentManager, PackageManager packageManager, PassengerManager passengerManager){
        this.enrollmentManager  = enrollmentManager;
        this.packageManager  = packageManager;
        this.passengerManager = passengerManager;
    }
    @Override
    public  void printItinerary(String packageName){

        Package apackage = packageManager.getPackageByName(packageName);
        System.out.println("\nPackage " + apackage.getPackageName());
        for(Destination dest : apackage.getItinerary()) {
            System.out.println("\nDestination : " + dest.getDestinationName());
            System.out.println("Activities Available at this Destination : ");
            System.out.println("Activity Name, Cost, Capacity, Description");
            for ( Activity activity : dest.getActivityList()) {
                System.out.println(
                        activity.getActivityName() + "," +
                        activity.getCost() + "," +
                        activity.getCapacity() + "," +
                        activity.getActivityDescription());
            }
        }
    }

    @Override
    public void printEnrolledPassenger(String packageName){

        Package apackage = packageManager.getPackageByName(packageName);
        System.out.println("\nPackage " + apackage.getPackageName());
        System.out.println("Passenger Capacity : " + apackage.getPassengerCapacity());
        System.out.println("Number of Passenger Enrolled in : " + apackage.getPassengerList().size());
        //System.out.println("Destinations count : " + apackage.getItinerary().size());

        if(apackage.getPassengerList().size() > 0){
            System.out.println("Passenger Details ");
            for (Passenger passenger : apackage.getPassengerList()) {
                System.out.println("Passenger Name : " + passenger.getPassengerName()
                        + " Passenger Number : " + passenger.getPassengerNumber());
            }
        }
        System.out.println();
    }

    @Override
    public void printPassengerEnrollmentDetails(String passengerId) {


        Passenger passenger = passengerManager.getPassenger(passengerId);
        System.out.println("Passenger Enrollment Details");
        System.out.println("Passenger Name : " + passenger.getPassengerName());
        System.out.println("Passenger Number : " + passengerId);
        System.out.println("Passenger Balance : " + passenger.getBalance());

        Enroll enroll = enrollmentManager.getEnrollmentList().stream()
                .filter(e -> e.getPassengerId().equals(passengerId)).findFirst().orElse(null);

        if(enroll != null){
            for (Map.Entry<String, String[]> entry : enroll.getItineraryMap().entrySet()) {
                System.out.println("Destination : " + entry.getKey());
                System.out.println("Enrolled Activities ");
                for (String activityName: entry.getValue()) {
                    System.out.println("Activity Name " + activityName + " Amount paid " +
                            getCostForPassenger(activityName, passenger));
                }

            }
        }
        System.out.println();
    }

    private double getCostForPassenger(String activityName, Passenger passenger) {
        Activity activities = packageManager.getAnActivity(activityName);
        if(passenger.getPassengerCategory().equals(PassengerCategory.STANDARD))
            return activities.getCost();
        else if(passenger.getPassengerCategory().equals(PassengerCategory.GOLD))
            return activities.getCost() - (activities.getCost() * 0.1);
        return 0.0;

    }

    @Override
    public void printUnfilledActivities(){
        for (Activity activity : packageManager.getActivityList()) {
            if(activity.getCapacity() <= 0)
                continue;
            System.out.println("Activity Name : " + activity.getActivityName() +
                    ", Activity Current Capacity " + activity.getCapacity());
            System.out.println();

        }
    }
}
