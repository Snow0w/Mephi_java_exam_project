package edu.mephi.log;

import edu.mephi.human.Human;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteLog {
  private BufferedWriter writer;

  public void createNewLogFile(Human human) throws IOException {
    String filename = Integer.toString(human.getId()) + ".medlog";
    writer = new BufferedWriter(new FileWriter(filename));
    writer.write(human.toString());
    writer.close();
  }
}
