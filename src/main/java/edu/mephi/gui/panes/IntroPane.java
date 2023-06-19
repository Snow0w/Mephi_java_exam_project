package edu.mephi.gui.panes;

import edu.mephi.gui.Gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class IntroPane extends JPanel implements ActionListener {
  private JButton newButton;
  private JButton selectButton;
  private JButton exitButton;
  private Gui mainFrame;

  public IntroPane(Gui parent) {
    mainFrame = parent;
    newButton = new JButton("New patient");
    selectButton = new JButton("Select patient");
    exitButton = new JButton("Exit");
    newButton.addActionListener(this);
    selectButton.addActionListener(this);
    exitButton.addActionListener(this);
    this.add(newButton);
    this.add(selectButton);
    this.add(exitButton);
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == newButton) {
      this.newButtonAction();
    }
    if (event.getSource() == selectButton) {
      this.selectButtonAction();
    }
    if (event.getSource() == exitButton) {
      this.exitButtonAction();
    }
  }

  private void newButtonAction() { mainFrame.switchToNewPatient(); }

  private void selectButtonAction() { mainFrame.switchToSelect(); }

  private void exitButtonAction() { mainFrame.exitAction(); }
}
