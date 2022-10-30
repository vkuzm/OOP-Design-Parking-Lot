package com.example;

import com.example.enums.ParkingSpotType;
import com.example.enums.VehicleType;
import com.example.exception.MaxParkingSpotException;
import com.example.exception.ParkingFullException;
import com.example.model.ParkingReceipt;
import com.example.model.ParkingSpot;
import com.example.model.ParkingTicket;
import com.example.model.Vehicle;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ParkingLot {

  private final double parkingRate = 3.0;

  private int compactSpotCount = 0;
  private int largeSpotCount = 0;
  private int motorbikeSpotCount = 0;
  private int electricSpotCount = 0;
  private final int maxCompactCount = 20;
  private final int maxLargeCount = 10;
  private final int maxMotorbikeCount = 5;
  private final int maxElectricCount = 5;

  private final Map<ParkingSpotType, List<ParkingSpot>> parkingSpots = Map.of(
      ParkingSpotType.COMPACT, new ArrayList<>(),
      ParkingSpotType.LARGE, new ArrayList<>(),
      ParkingSpotType.MOTORBIKE, new ArrayList<>(),
      ParkingSpotType.ELECTRIC, new ArrayList<>()
  );

  public ParkingLot() {
    createParkingSpots();
  }

  public void addParkingSpot(ParkingSpot parkingSpot) {
    if (compactSpotCount >= maxCompactCount
        || largeSpotCount >= maxLargeCount
        || motorbikeSpotCount >= maxMotorbikeCount
        || electricSpotCount >= maxElectricCount) {
      throw new MaxParkingSpotException("Max parking spot exception: " + parkingSpot.getType());
    }

    parkingSpots.get(parkingSpot.getType()).add(parkingSpot);
  }

  public ParkingTicket enterParking(Vehicle vehicle) {
    Optional<ParkingSpot> freeParkingSpot = findFreeParkingSpot(vehicle.getType());
    if (freeParkingSpot.isEmpty()) {
      throw new ParkingFullException("Parking is full for vehicle type: " + vehicle.getType());
    }

    assignVehicleToSpot(vehicle, freeParkingSpot.get());
    return new ParkingTicket(freeParkingSpot.get(), LocalDateTime.now().minusHours(2), parkingRate);
  }

  public ParkingReceipt exitParking(ParkingTicket parkingTicket) {
    ParkingSpot parkingSpot = parkingTicket.getParkingSpot();
    freeSpot(parkingSpot);

    LocalDateTime enterDate = parkingTicket.getEnterDate();
    LocalDateTime exitDate = LocalDateTime.now();

    long seconds = Duration.between(enterDate, exitDate).toSeconds();
    double totalCost = (seconds * parkingTicket.getRate()) / 3600;

    return new ParkingReceipt(parkingSpot, totalCost, enterDate, exitDate);
  }

  public List<ParkingSpot> getParkingSpot(ParkingSpotType parkingSpotType) {
    return parkingSpots.get(parkingSpotType);
  }

  private void assignVehicleToSpot(Vehicle vehicle, ParkingSpot parkingSpot) {
    parkingSpot.assignVehicle(vehicle);
    updateParkingSpotCount(vehicle.getType(), 1);
  }

  private void freeSpot(ParkingSpot parkingSpot) {
    updateParkingSpotCount(parkingSpot.getVehicle().getType(), -1);
    parkingSpot.removeVehicle();
  }

  private Optional<ParkingSpot> findFreeParkingSpot(VehicleType vehicleType) {
    switch (vehicleType) {
      case CAR:
        return getParkingSpot(parkingSpots.get(ParkingSpotType.COMPACT));
      case TRUCK:
        return getParkingSpot(parkingSpots.get(ParkingSpotType.LARGE));
      case MOTORBIKE:
        return getParkingSpot(parkingSpots.get(ParkingSpotType.MOTORBIKE));
      case ELECTRIC:
        return getParkingSpot(parkingSpots.get(ParkingSpotType.ELECTRIC));
      default:
        return Optional.empty();
    }
  }

  private Optional<ParkingSpot> getParkingSpot(List<ParkingSpot> parkingSpots) {
    if (Objects.isNull(parkingSpots)) {
      return Optional.empty();
    }

    return parkingSpots.stream()
        .filter(ParkingSpot::isFree)
        .findFirst();
  }

  private void updateParkingSpotCount(VehicleType vehicleType, int quantity) {
    switch (vehicleType) {
      case CAR:
        compactSpotCount += quantity;
        break;
      case TRUCK:
        largeSpotCount += quantity;
        break;
      case MOTORBIKE:
        motorbikeSpotCount += quantity;
        break;
      case ELECTRIC:
        electricSpotCount += quantity;
        break;
    }
  }

  private void createParkingSpots() {
    int spotNumber = 1;

    for (int i = 0; i <= maxCompactCount; i++) {
      addParkingSpot(new ParkingSpot(spotNumber++, ParkingSpotType.COMPACT));
    }

    for (int i = 0; i <= maxLargeCount; i++) {
      addParkingSpot(new ParkingSpot(spotNumber++, ParkingSpotType.LARGE));
    }

    for (int i = 0; i <= maxMotorbikeCount; i++) {
      addParkingSpot(new ParkingSpot(spotNumber++, ParkingSpotType.MOTORBIKE));
    }

    for (int i = 0; i <= maxElectricCount; i++) {
      addParkingSpot(new ParkingSpot(spotNumber++, ParkingSpotType.ELECTRIC));
    }
  }
}
