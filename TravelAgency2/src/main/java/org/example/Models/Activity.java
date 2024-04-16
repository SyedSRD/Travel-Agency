package org.example.Models;

import org.jetbrains.annotations.NotNull;

public class Activity {

    @NotNull
    private String activityName;
    private String activityDescription;
    @NotNull
    private int cost;
    @NotNull
    private int capacity;

    public Activity(String activityName, String activityDescription, int cost, int capacity) {
        this.activityName = activityName;
        this.activityDescription = activityDescription;
        this.cost = cost;
        this.capacity = capacity;
    }

    public Activity(String activityName, int cost, int capacity) {
        this.activityName = activityName;
        this.activityDescription = "";
        this.cost = cost;
        this.capacity = capacity;
    }

    public Activity(String activityName) {
        this.activityName = activityName;
        this.activityDescription = "";
        this.cost = 0;
        this.capacity = 0;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
