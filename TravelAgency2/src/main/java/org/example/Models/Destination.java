package org.example.Models;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Destination {

    @NotNull
    private String destinationName;
    private ArrayList<Activity>  activityList;

    public Destination(String destinationName) {
        this.destinationName = destinationName;
        activityList = new ArrayList<>();
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public ArrayList<Activity> getActivityList() {
        return activityList;
    }

}
