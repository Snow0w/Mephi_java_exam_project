package edu.mephi.measurement;

import edu.mephi.Exam;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Measurement {
  private LocalDateTime time;
  private double temperature;
  private int heartRate;
  private int venousPressure;

  public Measurement() {}

  public Measurement(LocalDateTime time, double temperature, int heartRate,
                     int venousPressure) {
    this.time = time;
    this.temperature = temperature;
    this.heartRate = heartRate;
    this.venousPressure = venousPressure;
  }

  public MeasurementStatus getMeasurementStatus() {
    MeasurementBounds bounds = new MeasurementBounds();
    MeasurementStatus status = new MeasurementStatus(
        Exam.DANGER_HIGH_ZONE, Exam.DANGER_HIGH_ZONE, Exam.DANGER_HIGH_ZONE);
    for (int i = 0; i < 4; i++) {
      if (bounds.temperatureBounds[i] - temperature > 0) {
        status.setTemperatureStatus(i);
        break;
      }
    }
    for (int i = 0; i < 4; i++) {
      if (bounds.heartRateBounds[i] - heartRate > 0) {
        status.setHeartRateStatus(i);
        break;
      }
    }
    for (int i = 0; i < 4; i++) {
      if (bounds.venousPressureBounds[i] - venousPressure > 0) {
        status.setVenousPressureStatus(i);
        break;
      }
    }
    return status;
  }
  public LocalDateTime getTime() { return time; }
  public void setTime(LocalDateTime time) { this.time = time; }
  public double getTemperature() { return temperature; }
  public void setTemperature(double temperature) {
    this.temperature = temperature;
  }
  public int getHeartRate() { return heartRate; }
  public void setHeartRate(int heartRate) { this.heartRate = heartRate; }
  public int getVenousPressure() { return venousPressure; }
  public void setVenousPressure(int venousPressure) {
    this.venousPressure = venousPressure;
  }
  public String getStringTime() {
    return time.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
  }
  public String toString() {
    return new String(
        this.getStringTime() + " " + Double.toString(temperature) + " " +
        Integer.toString(heartRate) + " " + Integer.toString(venousPressure));
  }
}
