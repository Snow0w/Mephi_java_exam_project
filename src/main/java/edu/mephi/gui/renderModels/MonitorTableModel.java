package edu.mephi.gui.renderModels;

import edu.mephi.measurement.Measurement;
import edu.mephi.measurement.MeasurementStatus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class MonitorTableModel extends DefaultTableModel {
  private final int colCnt = 4;
  private final int rowCnt = 10;
  private LinkedList<Measurement> data;

  public Measurement getLastMeasurement() { return data.get(0); }

  public MonitorTableModel() {
    data = new LinkedList<>();
    for (int i = 0; i < rowCnt; i++) {
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

  public void updateNewPatient(ArrayList<Measurement> measureData) {
    int len = measureData.size();
    if (len >= rowCnt) {
      data =
          new LinkedList<>(measureData.subList(Math.max(len - rowCnt, 0), len));
      Collections.reverse(data);
      fireTableDataChanged();
      return;
    }
    data.clear();
    len = rowCnt - len;
    for (int i = 0; i < len; i++) {
      Measurement m = new Measurement();
      m.setTime(null);
      data.add(m);
    }
    for (Measurement m : measureData) {
      data.add(m);
    }
    Collections.reverse(data);
    fireTableDataChanged();
  }

  public void updateTable(Measurement measurement) {
    data.removeLast();
    data.addFirst(measurement);
    System.out.println(measurement);
    System.out.println(measurement.getMeasurementStatus());
    fireTableDataChanged();
  }

  public MeasurementStatus getRowStatus(int row) {
    Measurement m = data.get(row);
    if (m.getTime() == null)
      return null;
    return m.getMeasurementStatus();
  }
}
