package edu.mephi.log;

import edu.mephi.exceptions.WrongLogFileFormatException;
import edu.mephi.human.Human;
import edu.mephi.human.HumanFabric;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.io.filefilter.WildcardFileFilter;

public class ReadLog {
  private BufferedReader reader;

  public ArrayList<Human> readAllLogs()
      throws IOException, WrongLogFileFormatException {
    ArrayList<Human> list = new ArrayList<>();
    File dir = new File(".");
    FileFilter fileFilter =
        WildcardFileFilter.builder().setWildcards("*.medlog").get();
    File[] files = dir.listFiles(fileFilter);
    for (File file : files) {
      list.add(getHumanFromFile(file.toString()));
    }

    return list;
  }

  private Human getHumanFromFile(String filename)
      throws IOException, WrongLogFileFormatException {
    HumanFabric fabric = new HumanFabric();
    reader = new BufferedReader(new FileReader(filename));
    String line = reader.readLine();
    reader.close();
    Human human;
    try {
      String[] splited = line.split("\\s+");
      String idText = splited[0];
      String name = splited[1];
      String secondName = splited[2];
      String fathersName = splited[3];
      human = fabric.createHuman(idText, name, secondName, fathersName);
    } catch (Exception e) {
      throw new WrongLogFileFormatException(filename);
    }
    return human;
  }
}
