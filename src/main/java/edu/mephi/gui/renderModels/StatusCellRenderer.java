package edu.mephi.gui.renderModels;

import edu.mephi.Exam;
import edu.mephi.measurement.MeasurementStatus;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class StatusCellRenderer extends DefaultTableCellRenderer {
  @Override
  public Component
  getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                boolean hasFocus, int row, int col) {

    JLabel l = (JLabel)super.getTableCellRendererComponent(
        table, value, isSelected, hasFocus, row, col);

    if (col == 0)
      return l;
    MonitorTableModel tableModel = (MonitorTableModel)table.getModel();
    MeasurementStatus status = tableModel.getRowStatus(row);
    if (status == null)
      return l;

    return getColourFullCellBackground(
        l, status.getParameterStatusByIndex(col - 1));
  }

  private Component getColourFullCellBackground(JLabel l, int status) {
    switch (status) {
    case Exam.NORMAL_ZONE:
      l.setBackground(Color.WHITE);
      break;
    case Exam.DANGER_LOW_ZONE:
      l.setBackground(new Color(153, 0, 0));
      break;
    case Exam.LOW_ZONE:
      l.setBackground(new Color(153, 51, 51));
      break;
    case Exam.HIGH_ZONE:
      l.setBackground(new Color(153, 51, 51));
      break;
    case Exam.DANGER_HIGH_ZONE:
      l.setBackground(new Color(153, 0, 0));
      break;
    }

    return l;
  }
}
