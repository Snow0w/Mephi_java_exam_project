package edu.mephi.thread;

import edu.mephi.gui.renderModels.MonitorTableModel;
import edu.mephi.human.Human;
import edu.mephi.log.WriteLog;
import edu.mephi.measurement.Measurement;
import edu.mephi.measurement.MeasurementFabric;
import edu.mephi.measurement.MeasurementStatus;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Writer;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

public class MontitorThread implements Runnable {
  private static final int SECONDSSLEPP = 1;
  private Human human;
  private ArrayList<Measurement> data;
  private MonitorTableModel tableModel;
  private boolean isSick;
  private Thread inform;
  private LocalDateTime startSickTime;
  private JTextField durationLabel;

  public MontitorThread(Human human, ArrayList<Measurement> data,
                        MonitorTableModel tableModel,
                        JTextField durationLabel) {
    this.human = human;
    this.data = data;
    this.tableModel = tableModel;
    this.isSick = false;
    this.inform = null;
    this.startSickTime = null;
    this.durationLabel = durationLabel;
  }

  @Override
  public void run() {
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
        // e.printStackTrace();
      }
    }
  }

  private void monitoring(WriteLog writer)
      throws IOException, InterruptedException {
    MeasurementFabric fabric = new MeasurementFabric();
    Measurement measurement = null;
    Measurement prev = null;
    try {
      synchronized (data) { prev = data.get(data.size() - 1); }
    } catch (IndexOutOfBoundsException e) {
      prev = null;
    }
    while (true) {
      measurement = fabric.createMeasurement(prev);
      prev = measurement;
      checkHumanStatus(measurement);
      synchronized (data) { data.add(measurement); } // TODO add notify for stat
      writer.writeMeasurementInLogFile(measurement);
      synchronized (tableModel) { tableModel.updateTable(measurement); }
      TimeUnit.SECONDS.sleep(SECONDSSLEPP);
    }
  }

  private void checkHumanStatus(Measurement measurement) {
    MeasurementStatus status = measurement.getMeasurementStatus();
    if (!status.isOk() && !isSick) {
      if (inform == null || inform.getState() == Thread.State.TERMINATED) {
        InformThread task = new InformThread(measurement.getStringTime());
        inform = new Thread(task);
        inform.start();
      }
      startSickTime = measurement.getTime();
      isSick = true;
    }
    if (status.isOk() && isSick) {
      updateLabel(LocalDateTime.now());
      isSick = false;
    }
  }

  private void updateLabel(LocalDateTime now) {
    String start = startSickTime.format(
        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    Duration dur = Duration.between(startSickTime, now);
    durationLabel.setText("Start illness: " + start +
                          ", duration: " + dur.getSeconds() + " seconds");
  }
}
