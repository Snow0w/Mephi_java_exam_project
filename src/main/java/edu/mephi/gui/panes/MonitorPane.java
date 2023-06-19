package edu.mephi.gui.panes;

import edu.mephi.gui.Gui;
import edu.mephi.gui.renderModels.MonitorTableModel;
import edu.mephi.human.Human;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

public class MonitorPane extends JPanel implements ActionListener {
  private JButton startButton;
  private JButton stopButton;
  private JButton backButton;
  private JTextField humanLabel;
  private JTable monitorTable;
  private Gui mainFrame;
  private MonitorTableModel tableModel;
  private JScrollPane centerPane;

  public MonitorPane(Gui parent) {
    mainFrame = parent;
    startButton = new JButton("Start");
    startButton.addActionListener(this);
    stopButton = new JButton("Stop");
    stopButton.addActionListener(this);
    backButton = new JButton("To main window");
    backButton.addActionListener(this);
    humanLabel = new JTextField();
    humanLabel.setColumns(40);
    humanLabel.setEditable(false);
    this.setLayout(new BorderLayout());
    JPanel topPane = new JPanel();
    topPane.add(humanLabel);
    topPane.add(startButton);
    topPane.add(stopButton);
    topPane.add(backButton);
    tableModel = new MonitorTableModel();
    monitorTable = new JTable(tableModel);
    centerPane = new JScrollPane(monitorTable);
    this.add(topPane, BorderLayout.SOUTH);
    this.add(centerPane, BorderLayout.CENTER);
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == startButton) {
      this.startButtonAction();
    }
    if (event.getSource() == stopButton) {
      this.stopButtonAction();
    }
    if (event.getSource() == backButton) {
      this.backButtonAction();
    }
  }

  private void backButtonAction() {
    // TODO add protect from monitoring thread
    mainFrame.switchToIntro();
  }

  private void stopButtonAction() {}

  private void startButtonAction() {}

  public void initPatient(Human human) {}
}
