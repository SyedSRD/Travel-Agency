package org.example.Dto;

import org.example.Models.Enroll;

import java.util.List;

public interface TotalEnrollmentList {

    public  void addEnrollment(Enroll enroll);
    public List<Enroll> getEnrollment();


}
