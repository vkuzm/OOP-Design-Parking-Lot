package com.example.model;

import com.example.enums.VehicleType;

public class Vehicle {
  private final String licenseNumber;
  private final VehicleType type;

  public Vehicle(String licenseNumber, VehicleType type) {
    this.licenseNumber = licenseNumber;
    this.type = type;
  }

  public String getLicenseNumber() {
    return licenseNumber;
  }

  public VehicleType getType() {
    return type;
  }

  @Override
  public String toString() {
    return "Vehicle{" +
        "licenseNumber='" + licenseNumber + '\'' +
        ", type=" + type +
        '}';
  }
}