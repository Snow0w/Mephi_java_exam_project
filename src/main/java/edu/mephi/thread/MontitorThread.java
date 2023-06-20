package edu.mephi.thread;

import edu.mephi.gui.renderModels.MonitorTableModel;
import edu.mephi.human.Human;
import edu.mephi.log.WriteLog;
import edu.mephi.measurement.Measurement;
import edu.mephi.measurement.MeasurementFabric;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;

public class MontitorThread implements Runnable {
  private static final int SECONDSSLEPP = 2;
  private Human human;
  private ArrayList<Measurement> data;
  private MonitorTableModel tableModel;

  public MontitorThread(Human human, ArrayList<Measurement> data,
                        MonitorTableModel tableModel) {
    this.human = human;
    this.data = data;
    this.tableModel = tableModel;
  }

  @Override
  public void run() {
    synchronized (data) {
      WriteLog writer = null;
      try {
        writer = new WriteLog(human);
        monitoring(writer);
      } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Can't write to log file",
                                      "Can'r write", JOptionPane.ERROR_MESSAGE);
      } catch (InterruptedException e) {
      } finally {
        if (writer == null)
          return;
        try {
          writer.closeLogFile();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private void monitoring(WriteLog writer)
      throws IOException, InterruptedException {
    MeasurementFabric fabric = new MeasurementFabric();
    Measurement measurement = null;
    Measurement prev = null;
    try {
      prev = data.get(data.size() - 1);
    } catch (IndexOutOfBoundsException e) {
      prev = null;
    }
    while (true) {
      measurement = fabric.createMeasurement(prev);
      data.add(measurement);
      writer.writeMeasurementInLogFile(measurement);
      synchronized (tableModel) { tableModel.updateTable(measurement); }
      TimeUnit.SECONDS.sleep(SECONDSSLEPP);
    }
  }
}
