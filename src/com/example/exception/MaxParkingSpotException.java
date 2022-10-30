package com.example.exception;

public class MaxParkingSpotException extends RuntimeException {

  public MaxParkingSpotException(String message) {
    super(message);
  }
}
