package edu.mephi.thread;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class InformThread implements Runnable {

  @Override
  public void run() {
    try {
      JOptionPane.showMessageDialog(null, "Problem");
    } catch (Exception e) {
    }
  }
}
