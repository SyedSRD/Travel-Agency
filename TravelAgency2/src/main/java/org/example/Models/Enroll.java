package org.example.Models;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Enroll {

    @NotNull
    private String passengerId;
    @NotNull
    private String packageName;
    private Map<String,String[]> itineraryMap;

    public Enroll(String passengerId, String packageName){
        this.passengerId = passengerId;
        this.packageName = packageName;
        this.itineraryMap = new HashMap<String,String[]>();
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Map<String, String[]> getItineraryMap() {
        return itineraryMap;
    }
}
