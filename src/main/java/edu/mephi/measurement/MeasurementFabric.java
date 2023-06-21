package edu.mephi.measurement;

import edu.mephi.exceptions.WrongLogFileFormatException;
import edu.mephi.stats.Calculate;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MeasurementFabric {
  private static final double TEMPERATURE_STEP = 0.1;
  private static final double HEART_RATE_STEP = 1.0;
  private static final double VENOUS_PRESSURE_STEP = 1.0;
  private SecureRandom rand;
  private MeasurementBounds bounds;

  public MeasurementFabric() {
    rand = new SecureRandom();
    bounds = new MeasurementBounds();
  }

  public Measurement createMeasurement(Measurement old) {
    if (old == null)
      return createMeasurement();
    MeasurementStatus status = old.getMeasurementStatus();
    Measurement measurement = new Measurement();
    measurement.setTemperature(makeNewParameter(
        old.getTemperature(), status.getTemperatureStatus(), TEMPERATURE_STEP));
    measurement.setTemperature(
        Calculate.round(measurement.getTemperature(), 1));
    measurement.setHeartRate((int)makeNewParameter((double)old.getHeartRate(),
                                                   status.getHeartRateStatus(),
                                                   HEART_RATE_STEP));
    measurement.setVenousPressure((int)makeNewParameter(
        (double)old.getVenousPressure(), status.getVenousPressureStatus(),
        VENOUS_PRESSURE_STEP));
    measurement.setTime(LocalDateTime.now());
    return measurement;
  }

  private double makeNewParameter(double old, int status, double coeff) {
    switch (status) {
    case (MeasurementStatus.DANGER_LOW_ZONE):
      return chooseDirection(old, 0.80, coeff);
    case (MeasurementStatus.LOW_ZONE):
      return chooseDirection(old, 0.53, coeff);
    case (MeasurementStatus.NORMAL_ZONE):
      return chooseDirection(old, 0.55, coeff);
    case (MeasurementStatus.HIGH_ZONE):
      return chooseDirection(old, 0.47, coeff);
    default:
      return chooseDirection(old, 0.20, coeff);
    }
  }

  private double chooseDirection(double old, double probability, double coeff) {
    double first = rand.nextDouble();
    double second = rand.nextDouble();
    if (first < 0.05)
      return old;
    boolean prob = second < probability;
    if (prob)
      return old + coeff;
    return old - coeff;
  }

  public Measurement createMeasurement() {
    Measurement measurement = new Measurement();
    measurement.setTemperature(rand.nextDouble(bounds.temperatureBounds[2] -
                                               bounds.temperatureBounds[1]) +
                               bounds.temperatureBounds[1]);
    measurement.setTemperature(
        Calculate.round(measurement.getTemperature(), 1));
    measurement.setHeartRate(
        rand.nextInt(bounds.heartRateBounds[2] - bounds.heartRateBounds[1]) +
        bounds.heartRateBounds[1]);
    measurement.setVenousPressure(rand.nextInt(bounds.venousPressureBounds[2] -
                                               bounds.venousPressureBounds[1]) +
                                  bounds.venousPressureBounds[1]);
    measurement.setTime(LocalDateTime.now());
    return measurement;
  }

  public Measurement createMeasurementFromLogLine(String line)
      throws WrongLogFileFormatException {
    Measurement measurement = new Measurement();
    DateTimeFormatter formater =
        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    try {
      String[] splited = line.split("\\s+");
      measurement.setTime(
          LocalDateTime.parse(splited[0] + " " + splited[1], formater));
      measurement.setTemperature(Double.parseDouble(splited[2]));
      measurement.setHeartRate(Integer.parseInt(splited[3]));
      measurement.setVenousPressure(Integer.parseInt(splited[4]));
    } catch (Exception e) {
      e.printStackTrace();
      throw new WrongLogFileFormatException("Wrong log file format");
    }

    return measurement;
  }
}
