package edu.mephi.gui.panes;

import edu.mephi.exceptions.WrongNameLengthException;
import edu.mephi.gui.Gui;
import edu.mephi.human.Human;
import edu.mephi.human.HumanFabric;
import edu.mephi.log.WriteLog;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    // mainFrame.getHumanComboBox().addItem(new Human(1, "123", "123", "123"));

    HumanFabric fabric = new HumanFabric();
    Human human;
    try {
      human = fabric.createHuman(idTextField.getText(), nameTextField.getText(),
                                 secondNameTextField.getText(),
                                 fathersNameTextField.getText());
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Id should be a number > 0",
                                    "Wrong input", JOptionPane.ERROR_MESSAGE);
      return;
    } catch (WrongNameLengthException e) {
      JOptionPane.showMessageDialog(this, e.getMessage() + " - wrong length",
                                    "Wrong input", JOptionPane.ERROR_MESSAGE);
      return;
    }
    if (mainFrame.idExist(human.getId())) {
      JOptionPane.showMessageDialog(this, "Id already exists", "Wrong input",
                                    JOptionPane.ERROR_MESSAGE);
      return;
    }
    try {
      WriteLog log = new WriteLog(human);
      log.createNewLogFile();
    } catch (IOException e) {
      JOptionPane.showMessageDialog(this,
                                    "Can't write logs. Patient isn't added",
                                    "Wrong input", JOptionPane.ERROR_MESSAGE);
      return;
    }
    mainFrame.getHumanComboBox().addItem(human);
    mainFrame.showPatient(human);
    clearFields();
  }

  private void clearFields() {
    idTextField.setText("");
    nameTextField.setText("");
    secondNameTextField.setText("");
    fathersNameTextField.setText("");
  }

  private void backButtonAction() {
    clearFields();
    mainFrame.switchToIntro();
  }
}
