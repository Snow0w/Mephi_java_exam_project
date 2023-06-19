package edu.mephi.gui.panes;

import edu.mephi.gui.Gui;
import edu.mephi.gui.renderModels.HumanComboBoxRender;
import edu.mephi.human.Human;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class SelectPane extends JPanel implements ActionListener {
  private JButton showButton;
  private JButton backButton;
  private Gui mainFrame;
  private JComboBox<Human> humanComboBox;

  public JComboBox<Human> getHumanComboBox() { return humanComboBox; }

  public SelectPane(Gui parent) {
    mainFrame = parent;
    this.setLayout(new BorderLayout());
    JPanel centerPane = new JPanel();
    JPanel bottomPane = new JPanel();
    showButton = new JButton("Show patient");
    backButton = new JButton("Back");
    showButton.addActionListener(this);
    backButton.addActionListener(this);
    bottomPane.add(showButton);
    bottomPane.add(backButton);
    this.add(bottomPane, BorderLayout.SOUTH);
    initHumanComboBox();
    centerPane.add(humanComboBox);
    this.add(centerPane, BorderLayout.CENTER);
  }

  private void initHumanComboBox() {
    this.humanComboBox = new JComboBox<>();
    humanComboBox.setRenderer(new HumanComboBoxRender());
    // humanComboBox.setSize(400, 10);
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == showButton) {
      this.showButtonAction();
    }
    if (event.getSource() == backButton) {
      this.backButtonAction();
    }
  }

  private void backButtonAction() { mainFrame.switchToIntro(); }

  private void showButtonAction() {
    // TODO Change this method
    Human h = (Human)humanComboBox.getSelectedItem();
    if (h == null)
      System.out.println("null");
  }
}
