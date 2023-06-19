package edu.mephi.gui.renderModels;

import edu.mephi.measurement.Measurement;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

public class MonitorTableModel extends AbstractTableModel {
  private final int colCnt = 4;
  private final int rowCnt = 10;
  private ArrayList<Measurement> data;

  public MonitorTableModel() {
    data = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Measurement m = new Measurement();
      m.setTime(null);
      data.add(m);
    }
  }

  @Override
  public int getColumnCount() {
    return colCnt;
  }

  @Override
  public int getRowCount() {
    return rowCnt;
  }

  @Override
  public Object getValueAt(int arg0, int arg1) {
    Measurement measurement = data.get(arg0);
    if (measurement.getTime() == null)
      return "-";
    switch (arg1) {
    case (0):
      return measurement.getStringTime();
    case (1):
      return Double.toString(measurement.getTemperature());
    case (2):
      return Integer.toString(measurement.getHeartRate());
    default:
      return Integer.toString(measurement.getVenousPressure());
    }
  }
  // @Override
  // public String getColumnName(int index) {
  //   switch (index) {
  //   case (0):
  //     return "Time";
  //   case (1):
  //     return "Temp";
  //   case (2):
  //     return "Heart";
  //   default:
  //     return "CVP";
  //   }
  // }
  @Override
  public String getColumnName(int index) {
    switch (index) {
    case (0):
      return "Time";
    case (1):
      return "Temperature (celsius)";
    case (2):
      return "Heart race (beats/min)";
    default:
      return "CVP (mmH2O)";
    }
  }
}
