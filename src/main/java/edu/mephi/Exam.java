package edu.mephi;

import edu.mephi.gui.Gui;
import edu.mephi.measurement.Measurement;
import edu.mephi.measurement.MeasurementFabric;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Exam {
  public static final int DANGER_LOW_ZONE = 0;
  public static final int LOW_ZONE = 1;
  public static final int NORMAL_ZONE = 2;
  public static final int HIGH_ZONE = 3;
  public static final int DANGER_HIGH_ZONE = 4;

  public static void main(String[] args) {
    Gui gui = new Gui("Patient simulation");
    gui.setVisible(true);
    // MeasurementFabric fabric = new MeasurementFabric();
    // Measurement m = fabric.createMeasurement();
    // System.out.println(m);
    // Measurement m2 = null;
    // for (int i = 0; i < 100; i++) {
    //   m2 = fabric.createMeasurement(m);
    //   System.out.println(m2);
    //   // System.out.println(m2.getMeasurementStatus().toString());
    //   m = m2;
    // }
    // LocalDateTime x = null;
    // try {
    //
    //   x = LocalDateTime.now();
    //   System.out.println(
    //       x.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    //   Thread.sleep(3000);
    //
    // } catch (Exception e) {
    //   // TODO: handle exception
    // }
    // LocalDateTime y = LocalDateTime.now();
    // System.out.println(
    //     y.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    // System.out.println(Duration.between(x, y).getSeconds());
  }
}
