package com.ngn.spring.project.cdb.common.dto;

/**
 * Created by USER on 09-Dec-20.
 */
public class VehicleDetails {
    private String registrationNo;
    private String ownerName;
    private String registeredRegion;
    private String vehicleType;

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getRegisteredRegion() {
        return registeredRegion;
    }

    public void setRegisteredRegion(String registeredRegion) {
        this.registeredRegion = registeredRegion;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}
