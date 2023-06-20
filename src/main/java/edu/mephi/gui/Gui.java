package edu.mephi.gui;

import edu.mephi.gui.panes.IntroPane;
import edu.mephi.gui.panes.MonitorPane;
import edu.mephi.gui.panes.NewPatientPane;
import edu.mephi.gui.panes.SelectPane;
import edu.mephi.human.Human;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gui extends JFrame {
  private static final int HEIGHT = 600;
  private static final int WIDTH = 800;
  private static final String INTRO = "1";
  private static final String NEWPATIENT = "2";
  private static final String SELECT = "3";
  private static final String MONITOR = "4";

  private JPanel mainPane;
  private JPanel Intro;
  private JPanel NewPatient;
  private SelectPane Select;
  private MonitorPane Monitor;
  private CardLayout layout;

  public Gui(String name) {
    super(name);
    this.setSize(WIDTH, HEIGHT);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setLayout(new BorderLayout());
    mainPane = new JPanel();
    layout = new CardLayout();
    mainPane.setLayout(layout);
    Intro = new IntroPane(this);
    NewPatient = new NewPatientPane(this);
    Select = new SelectPane(this);
    Monitor = new MonitorPane(this);
    mainPane.add(Intro, INTRO);
    mainPane.add(NewPatient, NEWPATIENT);
    mainPane.add(Select, SELECT);
    mainPane.add(Monitor, MONITOR);
    this.add(mainPane, BorderLayout.CENTER);
    layout.show(mainPane, INTRO);
  }

  public boolean idExist(int id) {
    JComboBox<Human> humanComboBox = Select.getHumanComboBox();
    int num = humanComboBox.getItemCount();
    Human h = null;
    for (int i = 0; i < num; i++) {
      h = humanComboBox.getItemAt(i);
      if (id == h.getId())
        return true;
    }
    return false;
  }

  public void exitAction() { this.dispose(); }

  public void switchToNewPatient() { layout.show(mainPane, NEWPATIENT); }
  public void switchToMonitor() { layout.show(mainPane, MONITOR); }

  public void switchToIntro() { layout.show(mainPane, INTRO); }
  public void switchToSelect() { layout.show(mainPane, SELECT); }
  public JComboBox<Human> getHumanComboBox() {
    return Select.getHumanComboBox();
  }

  public void showPatient(Human human) { Monitor.initPatient(human); }
}
