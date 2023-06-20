package edu.mephi.gui.renderModels;

import edu.mephi.stats.Result;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class StatisticsTableModel extends DefaultTableModel {
  private final int colCnt = 2;
  private final int rowCnt = 5;
  private ArrayList<Result> statData;

  public StatisticsTableModel(ArrayList<Result> statData) {
    this.statData = statData;
  }

  @Override
  public int getColumnCount() {
    return colCnt;
  }

  @Override
  public int getRowCount() {
    return rowCnt;
  }

  @Override
  public Object getValueAt(int arg0, int arg1) {
    if (statData.size() == 0) {
      return "-";
    }
    Result res = statData.get(arg0);
    if (arg1 == 0)
      return res.name;
    return res.value;
  }

  @Override
  public String getColumnName(int index) {
    switch (index) {
    case (0):
      return "Parameter";
    default:
      return "Value";
    }
  }
}
