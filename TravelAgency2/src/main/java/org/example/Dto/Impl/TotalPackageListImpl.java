package org.example.Dto.Impl;

import org.example.Dto.TotalPackageList;
import org.example.Models.Package;

import java.util.ArrayList;

public class TotalPackageListImpl implements TotalPackageList {

    public static ArrayList<Package> packagesList;

    public TotalPackageListImpl(){
        packagesList = new ArrayList<>();
    }
    @Override
    public void addPackages(Package packages) {
        packagesList.add(packages);
    }

    @Override
    public ArrayList<Package> getPackagesList() {
        return packagesList;
    }


}
