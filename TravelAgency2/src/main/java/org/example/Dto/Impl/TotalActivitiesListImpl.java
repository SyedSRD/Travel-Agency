package org.example.Dto.Impl;

import org.example.Dto.TotalActivitiesList;

import org.example.Models.Activity;

import java.util.ArrayList;

public class TotalActivitiesListImpl implements TotalActivitiesList {

    public static ArrayList<Activity> activitiesList;

    public TotalActivitiesListImpl() {
        activitiesList = new ArrayList<>();
    }
    @Override
    public void addActivities(Activity activity) {
        activitiesList.add(activity);
    }

    @Override
    public ArrayList<Activity> getActivitiesList() {
        return activitiesList;
    }




}
