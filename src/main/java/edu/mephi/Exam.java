package edu.mephi;

import edu.mephi.gui.Gui;

public class Exam {
  public static final int DANGER_LOW_ZONE = 0;
  public static final int LOW_ZONE = 1;
  public static final int NORMAL_ZONE = 2;
  public static final int HIGH_ZONE = 3;
  public static final int DANGER_HIGH_ZONE = 4;

  public static void main(String[] args) {
    Gui gui = new Gui("Patient simulation");
    gui.setVisible(true);
  }
}
