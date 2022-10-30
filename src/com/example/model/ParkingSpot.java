package com.example.model;

import com.example.enums.ParkingSpotType;

public class ParkingSpot {

  private final int number;
  private final ParkingSpotType type;
  private boolean isFree = true;
  private Vehicle vehicle;

  public boolean isFree() {
    return isFree;
  }

  public ParkingSpot(int number, ParkingSpotType type) {
    this.number = number;
    this.type = type;
  }

  public void assignVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
    this.isFree = false;
  }

  public void removeVehicle() {
    this.vehicle = null;
    this.isFree = true;
  }

  public int getNumber() {
    return number;
  }

  public ParkingSpotType getType() {
    return type;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  @Override
  public String toString() {
    return "ParkingSpot{" +
        "number=" + number +
        ", type=" + type +
        ", isFree=" + isFree +
        ", vehicle=" + vehicle +
        '}';
  }
}