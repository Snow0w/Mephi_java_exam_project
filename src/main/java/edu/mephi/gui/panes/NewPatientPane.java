package edu.mephi.gui.panes;

import edu.mephi.gui.Gui;
import edu.mephi.human.Human;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewPatientPane extends JPanel implements ActionListener {
  private JLabel idLabel;
  private JLabel nameLabel;
  private JLabel secondNameLabel;
  private JLabel fathersNameLabel;
  private JTextField idTextField;
  private JTextField nameTextField;
  private JTextField secondNameTextField;
  private JTextField fathersNameTextField;
  private JButton addButton;
  private JButton backButton;
  private Gui mainFrame;

  public NewPatientPane(Gui parent) {
    mainFrame = parent;
    JPanel centerPane = new JPanel(new GridLayout(2, 4));
    JPanel bottomPane = new JPanel();
    idLabel = new JLabel("Id:");
    nameLabel = new JLabel("Name:");
    secondNameLabel = new JLabel("Second name:");
    fathersNameLabel = new JLabel("Fathers name:");
    idTextField = new JTextField();
    nameTextField = new JTextField();
    secondNameTextField = new JTextField();
    fathersNameTextField = new JTextField();
    addButton = new JButton("Add patient");
    backButton = new JButton("Back");
    addButton.addActionListener(this);
    backButton.addActionListener(this);
    centerPane.add(idLabel);
    centerPane.add(idTextField);
    centerPane.add(nameLabel);
    centerPane.add(nameTextField);
    centerPane.add(secondNameLabel);
    centerPane.add(secondNameTextField);
    centerPane.add(fathersNameLabel);
    centerPane.add(fathersNameTextField);
    bottomPane.add(addButton);
    bottomPane.add(backButton);

    this.setLayout(new BorderLayout());
    this.add(centerPane, BorderLayout.CENTER);
    this.add(bottomPane, BorderLayout.SOUTH);
  }

  @Override
  public void actionPerformed(ActionEvent event) {

    if (event.getSource() == addButton) {
      this.addButtonAction();
    }
    if (event.getSource() == backButton) {
      this.backButtonAction();
    }
  }

  private void addButtonAction() {
    // TODO change this method
    mainFrame.getHumanComboBox().addItem(new Human(1, "123", "123", "123"));
  }

  private void backButtonAction() { mainFrame.switchToIntro(); }
}
