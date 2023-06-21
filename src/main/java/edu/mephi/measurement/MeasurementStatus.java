package edu.mephi.measurement;

public class MeasurementStatus {
  public static final int DANGER_LOW_ZONE = 0;
  public static final int LOW_ZONE = 1;
  public static final int NORMAL_ZONE = 2;
  public static final int HIGH_ZONE = 3;
  public static final int DANGER_HIGH_ZONE = 4;
  public final static int TEMPERATURE = 0;
  public final static int HEARTRATE = 1;
  public final static int VENOUSPRESSURE = 2;
  public final static int INVALIDPARAMETER = -1;
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

  public boolean isOk() {
    if (temperatureStatus != NORMAL_ZONE)
      return false;
    if (heartRateStatus != NORMAL_ZONE)
      return false;
    if (venousPressureStatus != NORMAL_ZONE)
      return false;
    return true;
  }

  public int getParameterStatusByIndex(int index) {
    switch (index) {
    case TEMPERATURE:
      return temperatureStatus;
    case HEARTRATE:
      return heartRateStatus;
    case VENOUSPRESSURE:
      return venousPressureStatus;
    default:
      return INVALIDPARAMETER;
    }
  }
  public String toString() {
    return new String(Integer.toString(temperatureStatus) + " " +
                      Integer.toString(heartRateStatus) + " " +
                      Integer.toString(venousPressureStatus));
  }
}
