package edu.mephi.gui.panes;

import edu.mephi.exceptions.WrongLogFileFormatException;
import edu.mephi.gui.Gui;
import edu.mephi.gui.renderModels.MonitorTableModel;
import edu.mephi.gui.renderModels.StatusCellRenderer;
import edu.mephi.human.Human;
import edu.mephi.log.ReadLog;
import edu.mephi.measurement.Measurement;
import edu.mephi.thread.MontitorThread;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
  private ArrayList<Measurement> measureData;
  private Human human;
  Thread monitorThread;

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
    monitorTable.getColumnModel().getColumn(1).setCellRenderer(
        new StatusCellRenderer());
    monitorTable.getColumnModel().getColumn(2).setCellRenderer(
        new StatusCellRenderer());
    monitorTable.getColumnModel().getColumn(3).setCellRenderer(
        new StatusCellRenderer());
    centerPane = new JScrollPane(monitorTable);
    this.add(topPane, BorderLayout.SOUTH);
    this.add(centerPane, BorderLayout.CENTER);
    measureData = new ArrayList<>();
    monitorThread = null;
    human = null;
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
    if (monitorThread != null) {
      JOptionPane.showMessageDialog(this, "Stop monitoring first!", "Wrong",
                                    JOptionPane.ERROR_MESSAGE);
      return;
    }
    mainFrame.switchToIntro();
  }

  private void stopButtonAction() {
    while (monitorThread.getState() == Thread.State.WAITING ||
           monitorThread.getState() == Thread.State.TIMED_WAITING) {
      monitorThread.interrupt();
    }
    monitorThread = null;
  }

  private void startButtonAction() {
    if (monitorThread != null)
      return;
    MontitorThread thread_worker =
        new MontitorThread(human, measureData, tableModel);
    monitorThread = new Thread(thread_worker);
    monitorThread.start();
  }

  public void initPatient(Human human) {
    this.human = human;
    humanLabel.setText(human.toStringShort());
    ReadLog reader = new ReadLog();
    try {
      synchronized (measureData) { measureData = reader.readHumanLogs(human); }
    } catch (IOException e) {
      JOptionPane.showMessageDialog(this, e.getMessage(), "Wrong input",
                                    JOptionPane.ERROR_MESSAGE);
      mainFrame.switchToIntro();
      // TODO add error handling
      return;
    } catch (WrongLogFileFormatException e) {
      JOptionPane.showMessageDialog(
          this, "Can't read log file " + human.getLogFilename(), "Wrong input",
          JOptionPane.ERROR_MESSAGE);
      mainFrame.switchToIntro();
      // TODO add error handling
      return;
    }
    tableModel.updateNewPatient(measureData);
    mainFrame.switchToMonitor();
  }
}
