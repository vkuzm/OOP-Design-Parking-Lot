package com.example;

import com.example.enums.VehicleType;
import com.example.model.ParkingReceipt;
import com.example.model.ParkingTicket;
import com.example.model.Vehicle;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    ParkingLot parkingLot = new ParkingLot();

    Vehicle car1 = new Vehicle("HT2345", VehicleType.CAR);
    Vehicle car2 = new Vehicle("FGH232", VehicleType.CAR);
    Vehicle truck1 = new Vehicle("GHGH32", VehicleType.TRUCK);

    ParkingTicket parkingTicket1 = parkingLot.enterParking(car1);
    ParkingTicket parkingTicket2 = parkingLot.enterParking(car2);
    ParkingTicket parkingTicket3 = parkingLot.enterParking(truck1);

    //System.out.println(parkingLot.getParkingSpot(ParkingSpotType.COMPACT));
    //System.out.println(parkingLot.getParkingSpot(ParkingSpotType.LARGE));

    ParkingReceipt parkingReceipt1 = parkingLot.exitParking(parkingTicket1);
    System.out.println(parkingReceipt1);
  }
}
