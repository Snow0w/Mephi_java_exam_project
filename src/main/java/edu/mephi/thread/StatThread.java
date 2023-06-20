package edu.mephi.thread;

import edu.mephi.gui.renderModels.StatisticsTableModel;
import edu.mephi.measurement.Measurement;
import edu.mephi.stats.DataToTableTransformer;
import java.util.ArrayList;

public class StatThread implements Runnable {
  private DataToTableTransformer trans;
  private ArrayList<Measurement> data;

  public StatThread(StatisticsTableModel model, ArrayList<Measurement> data) {
    trans = new DataToTableTransformer(model);
    this.data = data;
  }

  @Override
  public void run() {
    try {
      while (!Thread.currentThread().isInterrupted()) {
        synchronized (data) {
          data.wait();
          trans.updateTable(data);
        }
      }
    } catch (InterruptedException e) {
    }
  }
}
