package edu.mephi.measurement;

import edu.mephi.Exam;
import java.time.LocalDateTime;
import java.util.Random;

public class MeasurementFabric {
  private static final double ALPHA = 0.02;
  private static final double BETA = 0.04;
  Random rand;
  MeasurementBounds bounds;

  public MeasurementFabric() {
    rand = new Random();
    bounds = new MeasurementBounds();
  }

  public Measurement createMeasurement(Measurement old) {
    MeasurementStatus status = old.getMeasurementStatus();
    Measurement measurement = new Measurement();
    measurement.setTemperature(makeNewParameter(
        old.getTemperature(), status.getTemperatureStatus(), ALPHA));
    measurement.setHeartRate((int)makeNewParameter(
        (double)old.getHeartRate(), status.getHeartRateStatus(), BETA));
    measurement.setVenousPressure(
        (int)makeNewParameter((double)old.getVenousPressure(),
                              status.getVenousPressureStatus(), BETA));
    measurement.setTime(LocalDateTime.now());
    return measurement;
  }

  private double makeNewParameter(double old, int status, double coeff) {
    switch (status) {
    case (Exam.DANGER_LOW_ZONE):
      return old + old * coeff;
    case (Exam.LOW_ZONE):
      return chooseDirection(old, 0.52, coeff);
    case (Exam.NORMAL_ZONE):
      return chooseDirection(old, 0.60, coeff);
    case (Exam.HIGH_ZONE):
      return chooseDirection(old, 0.48, coeff);
    default:
      return old - old * coeff;
    }
  }

  private double chooseDirection(double old, double probability, double coeff) {
    boolean prob = rand.nextDouble() < probability;
    if (prob)
      return old + old * coeff;
    return old - old * coeff;
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
  private double round(double value, int precision) {
    int scale = (int)Math.pow(10, precision);
    return (double)Math.round(value * scale) / scale;
  }
}
