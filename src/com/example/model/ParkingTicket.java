package com.example.model;

import java.time.LocalDateTime;

public class ParkingTicket {

  private final ParkingSpot parkingSpot;
  private final LocalDateTime enterDate;
  private final double rate;

  public ParkingTicket(ParkingSpot parkingSpot, LocalDateTime enterDate, double rate) {
    this.parkingSpot = parkingSpot;
    this.enterDate = enterDate;
    this.rate = rate;
  }

  public ParkingSpot getParkingSpot() {
    return parkingSpot;
  }

  public LocalDateTime getEnterDate() {
    return enterDate;
  }

  public double getRate() {
    return rate;
  }

  @Override
  public String toString() {
    return "ParkingTicket{" +
        "parkingSpot=" + parkingSpot +
        ", enterDate=" + enterDate +
        ", rate=" + rate +
        '}';
  }
}
