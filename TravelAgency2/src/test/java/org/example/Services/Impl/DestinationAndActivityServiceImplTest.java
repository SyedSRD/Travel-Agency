package org.example.Services.Impl;

import org.example.Driver;

import org.example.Models.Activity;
import org.example.Models.Destination;

import org.example.Services.PackageManager;
import org.junit.jupiter.api.*;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DestinationAndActivityServiceImplTest {

    public PackageManager packageManager;
    public Destination destination;
    public Activity activity, activity2;

    public Driver driver;

    @BeforeEach
    public void setUp() throws Exception {
        packageManager = new PackageManager();
        driver  = new Driver();
        driver.addAllDestinationsWithActivities();
        destination = new Destination("Amsterdam");
        activity = new Activity("Paintball", "FPS game with paintballs", 10000,3);
        activity2 = new Activity("Paintball1", "FPS game with paintballs", 10000,0);
    }


    @Test
    void addAllDestinationsWithActivities() {
        Assertions.assertEquals(8,packageManager.getDestinationList().size());
    }

    @Test
    void getDestination() {
        assertEquals("London", packageManager.getDestinationByName("London").getDestinationName());
    }

    @Test
    void testDestinationExists()  {
        Assertions.assertNull(
            packageManager.getDestinationByName("Swiz")
        );
    }

    @Test
    void testAddDestination() {
        packageManager.getDestinationList().add(destination);
        Assertions.assertNotNull(packageManager.getDestinationByName("Amsterdam"));
    }

    @Test
    void testActivityListEmpty() {
        packageManager.getDestinationList().add(destination);
        assertTrue(packageManager.getDestinationByName("Amsterdam").getActivityList().isEmpty());
    }

    @Test
    void testActivitySize() {
        assertEquals(4,packageManager.getDestinationByName("Area83").getActivityList().size());
    }

    @Test
    void testAnActivityNull() {
        Assertions.assertNull(packageManager.getAnActivity("Rocket Man1"));
    }

    @Test
    void testAnActivityExist() {
        Assertions.assertNotNull(packageManager.getAnActivity("Rocket Man"));
    }

    @Test
    void testAnActivityCapacityFilled() {
        packageManager.getActivityList().add(activity);
        Assertions.assertFalse(packageManager.isActivityCapacityFull("Paintball"));
    }

    @Test
    void testAnActivityCapacityFilled2() {
        packageManager.getActivityList().add(activity2);
        Assertions.assertTrue(packageManager.isActivityCapacityFull("Paintball1"));
    }

    @Test
    void testAnActivityCapacityFilled3() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            packageManager.isActivityCapacityFull("Paintball2");
        });
    }

}