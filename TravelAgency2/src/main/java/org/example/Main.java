package org.example;

import net.minidev.json.parser.ParseException;
import org.example.Services.EnrollmentManager;
import org.example.Services.PackageManager;
import org.example.Services.PassengerManager;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {

        Driver driver = new Driver();

        driver.enrollPassengerToPackage();
        driver.logTravelAgenciesDetails();
    }
}
