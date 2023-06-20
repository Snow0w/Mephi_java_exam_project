package edu.mephi.measurement;

import edu.mephi.Exam;
import edu.mephi.exceptions.WrongLogFileFormatException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class MeasurementFabric {
  private static final double TEMPERATURE_STEP = 0.1;
  private static final double HEART_RATE_STEP = 1.0;
  private static final double VENOUS_PRESSURE_STEP = 1.0;
  Random rand;
  MeasurementBounds bounds;

  public MeasurementFabric() {
    rand = new Random();
    bounds = new MeasurementBounds();
  }

  public Measurement createMeasurement(Measurement old) {
    if (old == null)
      return createMeasurement();
    MeasurementStatus status = old.getMeasurementStatus();
    Measurement measurement = new Measurement();
    measurement.setTemperature(makeNewParameter(
        old.getTemperature(), status.getTemperatureStatus(), TEMPERATURE_STEP));
    measurement.setTemperature(round(measurement.getTemperature(), 1));
    measurement.setHeartRate((int)makeNewParameter((double)old.getHeartRate(),
                                                   status.getHeartRateStatus(),
                                                   HEART_RATE_STEP));
    measurement.setVenousPressure((int)makeNewParameter(
        (double)old.getVenousPressure(), status.getVenousPressureStatus(),
        VENOUS_PRESSURE_STEP));
    measurement.setTime(LocalDateTime.now());
    System.out.println("old " + old);
    System.out.println("new " + measurement);
    return measurement;
  }

  private double makeNewParameter(double old, int status, double coeff) {
    switch (status) {
    case (Exam.DANGER_LOW_ZONE):
      return chooseDirection(old, 0.80, coeff);
    case (Exam.LOW_ZONE):
      return chooseDirection(old, 0.52, coeff);
    case (Exam.NORMAL_ZONE):
      return chooseDirection(old, 0.60, coeff);
    case (Exam.HIGH_ZONE):
      return chooseDirection(old, 0.48, coeff);
    default:
      return chooseDirection(old, 0.20, coeff);
    }
  }

  private double chooseDirection(double old, double probability, double coeff) {
    if (rand.nextDouble() < 0.05)
      return old;
    boolean prob = rand.nextDouble() < probability;
    if (prob)
      return old + coeff;
    return old - coeff;
  }

  public Measurement createMeasurement() {
    Measurement measurement = new Measurement();
    measurement.setTemperature(rand.nextDouble(bounds.temperatureBounds[2] -
                                               bounds.temperatureBounds[1]) +
                               bounds.temperatureBounds[1]);
    measurement.setTemperature(round(measurement.getTemperature(), 1));
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
      // System.out.println(line);
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
  private double round(double value, int precision) {
    int scale = (int)Math.pow(10, precision);
    return (double)Math.round(value * scale) / scale;
  }
}
