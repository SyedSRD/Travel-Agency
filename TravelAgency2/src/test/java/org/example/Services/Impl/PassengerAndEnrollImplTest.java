package org.example.Services.Impl;


import org.example.Driver;
import org.example.Models.Enroll;
import org.example.Models.Passenger;

import org.example.Services.EnrollmentManager;
import org.example.Services.PackageManager;

import org.example.Services.PassengerManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class PassengerAndEnrollImplTest {

    PassengerManager passengerManager;
    PackageManager packageManager;
    EnrollmentManager enrollmentManager;

    Driver driver;
    Enroll enroll;
    Passenger passenger;
    String passengerId;
    String packageName;
    Map<String,String[]> itMap;

    PrintStream save_out;
    ByteArrayOutputStream out;

    @BeforeEach
    void setup() throws Exception {
        passengerManager = new PassengerManager();
        packageManager  = new PackageManager();
        enrollmentManager = new EnrollmentManager(passengerManager,packageManager);
        driver = new Driver(enrollmentManager,packageManager,passengerManager);

        passenger = new Passenger("Test1", "TestPassenger");
        passengerId = "Test1";
        packageName = "RT-001";
        itMap = new HashMap<>();
        itMap.put("London", new String[]{"Buckingham Palace","The Royal Mews"});
        itMap.put("Area83", new String[]{ "Rocket Man", "Archery"});
        itMap.put("London", new String[]{"Deep-sea fishing", "Dolphin sightings", "Sea kayaking"});
        driver.addAllPackage();
        driver.addAllDestinationsWithActivities();

    }

    @BeforeEach
    void setupPrintStream() {
         save_out = System.out;
         out = new ByteArrayOutputStream();
         System.setOut(new PrintStream(out));

    }

    @Test
    void enrollPassenger() {
       assertThrows(NoSuchElementException.class, () -> {
           enrollmentManager.enrollPassenger(passengerId, packageName, itMap);
       });
    }

    @Test
    void enrollPassenger1() {
        passengerManager.addPassenger(passengerId,"Test3");
        enrollmentManager.enrollPassenger(passengerId, packageName, itMap);
        assertEquals("The passenger doesn't have enough balance for the requested activity\r\n"
                , out.toString());
        System.setOut(save_out);
    }

    @Test
    void enrollPassenger2() {
        passengerManager.addPassenger(passengerId,"Test3");
        enrollmentManager.enrollPassenger(passengerId, packageName, itMap);
        assertFalse(enrollmentManager.getEnrollmentList().isEmpty());
    }

    @Test
    void enrollPassenger3() {
        passengerManager.addPassenger(passengerId,"Test3");
        enrollmentManager.enrollPassenger(passengerId, packageName, itMap);
        assertEquals(passengerId,enrollmentManager.getEnrollmentById(passengerId).getPassengerId());
    }

    @Test
    void isPassengerListEmpty() {
        assertTrue(passengerManager.getPassengerList().isEmpty());
    }

    @Test
    void addPassenger(){
        passengerManager.addPassenger("Test2", "TestPassenger2");
        assertFalse(passengerManager.getPassengerList().isEmpty());
    }

    @Test
    void getPassengerByName() {
        assertThrows(NoSuchElementException.class,() -> {
            passengerManager.getPassenger("Test3");
        });
    }

}