package com.example.model;

import java.time.LocalDateTime;

public class ParkingReceipt {
  private final ParkingSpot parkingSpot;
  private final double totalCost;
  private final LocalDateTime enterDate;
  private final LocalDateTime exitDate;

  public ParkingReceipt(ParkingSpot parkingSpot, double totalCost, LocalDateTime enterDate, LocalDateTime exitDate) {
    this.parkingSpot = parkingSpot;
    this.totalCost = totalCost;
    this.enterDate = enterDate;
    this.exitDate = exitDate;
  }

  @Override
  public String toString() {
    return "ParkingReceipt{" +
        "parkingSpot=" + parkingSpot +
        ", totalCost=" + totalCost +
        ", enterDate=" + enterDate +
        ", exitDate=" + exitDate +
        '}';
  }
}
