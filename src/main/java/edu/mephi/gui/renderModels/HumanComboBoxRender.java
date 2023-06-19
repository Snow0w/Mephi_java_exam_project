package edu.mephi.gui.renderModels;

import edu.mephi.human.Human;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class HumanComboBoxRender extends DefaultListCellRenderer {

  @Override
  public Component getListCellRendererComponent(JList<?> list, Object value,
                                                int index, boolean isSelected,
                                                boolean cellHasFocus) {

    if (value instanceof Human) {
      Human h = (Human)value;
      value = "Id: " + h.getId() + ". " + h.getSecondName();
    }

    return super.getListCellRendererComponent(list, value, index, isSelected,
                                              cellHasFocus);
  }
}
