package org.example.Services;


import org.example.Dto.Impl.TotalEnrollmentListImpl;
import org.example.Dto.TotalEnrollmentList;
import org.example.Models.Activity;
import org.example.Models.Enroll;
import org.example.Models.Package;
import org.example.Models.Passenger;

import java.util.List;
import java.util.Map;


public class EnrollmentManager {

        private final TotalEnrollmentList totalEnrollmentList;
        private final PackageManager packageManager;
        private final PassengerManager passengerManager;

        public EnrollmentManager(PassengerManager passengerManager,PackageManager packageManager){
            totalEnrollmentList = new TotalEnrollmentListImpl();
            this.packageManager = packageManager;
            this.passengerManager = passengerManager;
        }
        public Enroll getEnrollmentById(String passengerId) {
            return totalEnrollmentList.getEnrollment().stream().filter(p -> p.getPassengerId().equals(passengerId)).findFirst().orElse(null);
        }

        public List<Enroll> getEnrollmentList() {
        return totalEnrollmentList.getEnrollment();
    }
        public void enrollPassenger(String passengerId, String packageName, Map<String, String[]> itMap){
            Enroll enroll = new Enroll(passengerId, packageName);
            Passenger passenger = passengerManager.getPassenger(passengerId);
            if (passenger == null){
                System.out.println("Passenger not found");
                return;
            }
            Package aPackage = packageManager.getPackageByName(packageName);
            if(aPackage.getPassengerCapacity() <= 0 ){
                System.out.println("Passenger capacity is full, cannot allocate more passengers to this package");
                return;
            }

            if (calculateActivityCapacityAndCost(passenger, itMap)) {
                enroll.getItineraryMap().putAll(itMap);
                aPackage.getPassengerList().add(passenger);
                totalEnrollmentList.addEnrollment(enroll);
            }
        }

        private boolean calculateActivityCapacityAndCost(Passenger passenger, Map<String, String[]> itineraryMap) {
            Activity activities;

            for (Map.Entry<String, String[]> entry : itineraryMap.entrySet()) {
                for ( String activity :  entry.getValue() ) {
                    if(packageManager.isActivityCapacityFull(activity)) {
                        System.out.println("The requested activity for Enrollment has exceeded its capacity");
                        return false;
                    }
                    activities = packageManager.getAnActivity(activity);
                    if (activities.getCost() > passenger.getBalance()) {
                        System.out.println("The passenger doesn't have enough balance for the requested activity");
                        return false;
                    }
                    activities.setCapacity(activities.getCapacity() - 1);
                    getADiscountIfAny(activities,passenger);

                }
            }
            return true;

        }

        private void getADiscountIfAny(Activity activities, Passenger passenger) {
            switch(passenger.getPassengerCategory()){
                case STANDARD ->  {
                    passenger.setBalance(passenger.getBalance() - activities.getCost());
                    break;
                }
                case GOLD -> {
                    double discount = activities.getCost() * 0.1;
                    passenger.setBalance((int) (passenger.getBalance() - (activities.getCost() - discount)));
                    break;
                }
            }

        }

    }

