package edu.mephi.human;

import edu.mephi.exceptions.WrongNameLengthException;

public class HumanFabric {
  public Human createHuman(String idText, String name, String secondName,
                           String fathersName)
      throws NumberFormatException, WrongNameLengthException {
    if (idText == null)
      throw new NumberFormatException();
    checkNameLength(idText);
    int id = Integer.parseInt(idText);
    if (id < 0)
      throw new NumberFormatException();
    checkNameLength(name);
    checkNameLength(secondName);
    checkNameLength(fathersName);
    return new Human(id, name, secondName, fathersName);
  }

  private void checkNameLength(String name) throws WrongNameLengthException {
    if (name == null)
      throw new WrongNameLengthException(name);
    int size = name.length();
    if (size > 30 || size < 1)
      throw new WrongNameLengthException(name);
  }
}
