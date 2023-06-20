package edu.mephi.stats;

import edu.mephi.gui.renderModels.StatisticsTableModel;
import edu.mephi.measurement.Measurement;
import java.util.ArrayList;

public class DataToTableTransformer {

  private StatisticsTableModel model;

  public DataToTableTransformer(StatisticsTableModel model) {
    this.model = model;
  }

  public void updateTable(ArrayList<Measurement> data) {
    int size = data.size();
    if (size < 2) {
      model.updateTabelData(new ArrayList<Result[]>());
      return;
    }
    double[][] arr = convertToTwoDArray(data, size);
    Calculate calc = new Calculate();
    ArrayList<Result[]> res = calc.calcForArrays(arr, 3);
    model.updateTabelData(res);
  }

  private double[][] convertToTwoDArray(ArrayList<Measurement> data, int size) {
    double[][] arr = new double[3][size];
    int cnt = 0;
    for (Measurement m : data) {
      arr[0][cnt] = m.getTemperature();
      arr[1][cnt] = (double)m.getHeartRate();
      arr[2][cnt] = (double)m.getVenousPressure();
      cnt++;
    }
    return arr;
  }
}
