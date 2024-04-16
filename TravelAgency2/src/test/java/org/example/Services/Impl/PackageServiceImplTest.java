package org.example.Services.Impl;

import org.example.Models.Destination;
import org.example.Models.Package;
import org.example.Models.Passenger;
import org.example.Services.PackageManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class PackageServiceImplTest {

    PackageManager packageManager;
    Package aPackage;
    Passenger passenger;
    Destination destination ;



    @BeforeEach
    void setUp() {
        packageManager  = new PackageManager();
        aPackage= new Package("RT-001", 0);
        passenger = new Passenger("testNum","TestName");
        destination = new Destination("Japan");

    }

    @Test
    void getPackagesList() {
        assertTrue(packageManager.getPackagesList().isEmpty());
    }

    @Test
    void addPackage() {
        packageManager.addPackage("RT-002",5,new ArrayList<>());
        assertNotNull(packageManager.getPackageByName("RT-002"));
    }

    @Test
    void getPackageByName2() {
        packageManager.getPackagesList().add(aPackage);
        assertEquals(aPackage,packageManager.getPackageByName("RT-001"));
    }

    @Test
    void getPackageByName() {
        assertNull(packageManager.getPackageByName("RT-002"));
    }

    @Test
    void isPassengerCapacityFull() {
        assertTrue(packageManager.isPassengerCapacityFull(aPackage));
    }

    @Test
    void isPassengerCapacityFull1() {
        aPackage.setPassengerCapacity(5);
        assertFalse(packageManager.isPassengerCapacityFull(aPackage));
    }

    @Test
    void addPassengerToPackage() {
        assertThrows(RuntimeException.class,
                () -> {
            packageManager.addPassengerToPackage(aPackage,passenger);
        });

    }

}