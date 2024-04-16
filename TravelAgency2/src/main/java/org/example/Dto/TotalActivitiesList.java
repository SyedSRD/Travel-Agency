package org.example.Dto;

import org.example.Models.Activity;

import java.util.ArrayList;

public interface TotalActivitiesList {

    public void addActivities(Activity activity);
    public ArrayList<Activity> getActivitiesList();

}
