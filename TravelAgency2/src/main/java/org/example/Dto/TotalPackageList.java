package org.example.Dto;

import org.example.Models.Package;

import java.util.ArrayList;

public interface TotalPackageList {

    public  void addPackages(Package packages);

    public ArrayList<Package> getPackagesList();

}
