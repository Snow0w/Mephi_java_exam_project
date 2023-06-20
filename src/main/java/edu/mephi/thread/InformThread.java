package edu.mephi.thread;

import javax.swing.JOptionPane;

public class InformThread implements Runnable {
  private String time;

  public InformThread(String time) { this.time = time; }

  @Override
  public void run() {
    try {
      JOptionPane.showMessageDialog(null, "Problem in " + time);
    } catch (Exception e) {
    }
  }
}
