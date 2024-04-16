package org.example.Dto.Impl;

import org.example.Dto.TotalEnrollmentList;
import org.example.Models.Enroll;

import java.util.ArrayList;
import java.util.List;

public class TotalEnrollmentListImpl implements TotalEnrollmentList {
    public static ArrayList<Enroll> enrollmentList;
    public TotalEnrollmentListImpl(){
        enrollmentList = new ArrayList<>();
    }

    @Override
    public void addEnrollment(Enroll enroll) {
        enrollmentList.add(enroll);
    }

    @Override
    public List<Enroll> getEnrollment() {
        return enrollmentList;
    }


}
