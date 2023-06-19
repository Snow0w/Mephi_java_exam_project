package edu.mephi.gui;

import edu.mephi.gui.panes.IntroPane;
import edu.mephi.gui.panes.NewPatientPane;
import edu.mephi.gui.panes.SelectPane;
import edu.mephi.gui.renderModels.HumanComboBoxRender;
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
  private JComboBox<Human> humanComboBox;

  private JPanel mainPane;
  private JPanel Intro;
  private JPanel NewPatient;
  private JPanel Select;
  private CardLayout layout;

  public Gui(String name) {
    super(name);
    initHumanComboBox();
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
    mainPane.add(Intro, INTRO);
    mainPane.add(NewPatient, NEWPATIENT);
    mainPane.add(Select, SELECT);
    this.add(mainPane, BorderLayout.CENTER);
    layout.show(mainPane, INTRO);
  }

  private void initHumanComboBox() {
    this.humanComboBox = new JComboBox<>();
    humanComboBox.setRenderer(new HumanComboBoxRender());
    // humanComboBox.setSize(400, 10);
  }

  public void exitAction() { this.dispose(); }

  public void switchToNewPatient() { layout.show(mainPane, NEWPATIENT); }

  public void switchToIntro() { layout.show(mainPane, INTRO); }
  public void switchToSelect() { layout.show(mainPane, SELECT); }
  public JComboBox<Human> getHumanComboBox() { return humanComboBox; }

  public void setHumanComboBox(JComboBox<Human> humanComboBox) {
    this.humanComboBox = humanComboBox;
  }
}
