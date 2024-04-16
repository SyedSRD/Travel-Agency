package org.example;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import org.example.Enum.PassengerCategory;
import org.example.Models.Activity;
import org.example.Models.Destination;
import org.example.Models.Package;
import org.example.Models.Passenger;
import org.example.Services.EnrollmentManager;

import org.example.Services.Impl.OutputImpl;
import org.example.Services.Output;
import org.example.Services.PackageManager;

import org.example.Services.PassengerManager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Driver {

    private EnrollmentManager enrollmentManager;
    private PackageManager packageManager;
    private PassengerManager passengerManager;

    private Output output;


    public Driver(){
        this.packageManager  = new PackageManager();
        this.passengerManager = new PassengerManager();
        this.enrollmentManager  = new EnrollmentManager(passengerManager,packageManager) ;
        output = new OutputImpl(enrollmentManager,packageManager,passengerManager);

    }
    public Driver(EnrollmentManager enrollmentManager, PackageManager packageManager, PassengerManager passengerManager) {
        this.enrollmentManager  = enrollmentManager;
        this.packageManager  = packageManager;
        this.passengerManager = passengerManager;
        output = new OutputImpl(enrollmentManager,packageManager,passengerManager);

    }
    public void createDto() throws IOException, ParseException {

        addAllPassenger();
        addAllDestinationsWithActivities();
        addAllPackage();

    }

    public void addAllPassenger() throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(
                "C:\\Users\\syed\\Downloads\\collgproject\\TravelAgency2\\src\\main\\resources\\allPassengers.json"));

        for (Object o : jsonArray) {
            JSONObject person = (JSONObject) o;

            String passengerName = (String) person.get("passengerName");
            String passengerNumber = (String) person.get("passengerNumber");
            int balance = (int) person.get("balance");
            PassengerCategory passengerCategory = PassengerCategory.valueOf((String) person.get("passengerCategory"));

            passengerManager.getPassengerList().add(
                    new Passenger(passengerName,passengerNumber,passengerCategory,balance));
        }

    }

    public void addAllDestinationsWithActivities() throws FileNotFoundException, ParseException {

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(
                "C:\\Users\\syed\\Downloads\\collgproject\\TravelAgency2\\src\\main\\resources\\allDestinationsWithActivities.json"));
        Activity activities ;

        for (Object o : jsonArray) {
            JSONObject dest = (JSONObject) o;

            String destinationName = (String) dest.get("destinationName");

            Destination destination = new Destination(destinationName);

            JSONArray activitiesList = (JSONArray) dest.get("activityList");
            for( Object activity: activitiesList ){
                JSONObject activityObj = (JSONObject) activity;

                String activityName = (String) activityObj.get("activityName");
                String activityDescription = (String) activityObj.get("activityDescription");
                int cost = (int) activityObj.get("cost");
                int capacity = (int) activityObj.get("capacity");
                activities = new Activity(activityName,activityDescription,cost,capacity);

                packageManager.getActivityList().add(activities);
                destination.getActivityList().add(activities);
            }
            packageManager.getDestinationList().add(destination);
        }
    }

    public void addAllPackage() throws FileNotFoundException, ParseException {

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(
                "C:\\Users\\syed\\Downloads\\collgproject\\TravelAgency2\\src\\main\\resources\\allPackages.json"));

        Destination destination;
        Activity activities;
        List<Destination> destinations = new ArrayList<>();

        for( Object o : jsonArray ) {
            JSONObject person = (JSONObject) o;

            String packageName = (String) person.get("packageName");
            int passengerCapacity = (int) person.get("passengerCapacity");

            JSONArray destinationList = (JSONArray) person.get("itinerary");

            for( Object o1 : destinationList ) {
                JSONObject dest = (JSONObject) o1;

                String destinationName = (String) dest.get("destinationName");
                //System.out.println("destinationName : " + destinationName);
                destination = new Destination(destinationName);

                JSONArray activitiesList = (JSONArray) dest.get("activityList");
                for( Object activity : activitiesList ) {
                    JSONObject activityObj = (JSONObject) activity;

                    String activityName = (String) activityObj.get("activityName");
                    String activityDescription = (String) activityObj.get("activityDescription");
                    int cost = (int) activityObj.get("cost");
                    int capacity = (int) activityObj.get("capacity");
                    activities = new Activity(activityName, activityDescription, cost, capacity);

                    destination.getActivityList().add(activities);
                }
                destinations.add(destination);

            }
            Package aPackage = new Package(packageName, passengerCapacity);
            aPackage.getItinerary().addAll(destinations);
            packageManager.getPackagesList().add(aPackage);
            destinations.clear();
        }
    }


    public void enrollPassengerToPackage() throws FileNotFoundException, ParseException {
        Map<String, String[]> itMap = new HashMap<>();
        JSONArray activityArray ;
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(
                "C:\\Users\\syed\\Downloads\\collgproject\\TravelAgency2\\src\\main\\resources\\enrollPassengers.json"));

        for (Object o : jsonArray) {
            JSONObject record = (JSONObject) o;

            String passengerId = (String) record.get("passengerId");
            String packageName = (String) record.get("packageName");
            JSONObject itineraryMap = (JSONObject) record.get("itineraryMap");
            for (Map.Entry<String, Object> entry : itineraryMap.entrySet()){
                activityArray = (JSONArray) entry.getValue();
                itMap.put(entry.getKey(), activityArray.stream().toArray(String[]::new));
            }

            enrollmentManager.enrollPassenger(passengerId, packageName,itMap);
        }
    }

    public void logTravelAgenciesDetails() throws FileNotFoundException, ParseException{
        output.printItinerary("RT-003");
        output.printItinerary("RT-001");
        output.printEnrolledPassenger("RT-001");
        output.printEnrolledPassenger("RT-004");

        output.printPassengerEnrollmentDetails("P001");
        output.printPassengerEnrollmentDetails("P002");

        output.printUnfilledActivities();

    }



}
