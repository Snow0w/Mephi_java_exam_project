package edu.mephi.measurement;

public class MeasurementStatus {
  private int temperatureStatus;
  private int heartRateStatus;
  private int venousPressureStatus;

  public MeasurementStatus(int temperatureStatus, int heartRateStatus,
                           int venousPressureStatus) {
    this.venousPressureStatus = venousPressureStatus;
    this.heartRateStatus = heartRateStatus;
    this.temperatureStatus = temperatureStatus;
  }
  public int getTemperatureStatus() { return temperatureStatus; }
  public void setTemperatureStatus(int temperatureStatus) {
    this.temperatureStatus = temperatureStatus;
  }
  public int getHeartRateStatus() { return heartRateStatus; }
  public void setHeartRateStatus(int heartRateStatus) {
    this.heartRateStatus = heartRateStatus;
  }
  public int getVenousPressureStatus() { return venousPressureStatus; }
  public void setVenousPressureStatus(int venousPressureStatus) {
    this.venousPressureStatus = venousPressureStatus;
  }
  public String toString() {
    return new String(Integer.toString(temperatureStatus) + " " +
                      Integer.toString(heartRateStatus) + " " +
                      Integer.toString(venousPressureStatus));
  }
}
