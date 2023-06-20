package edu.mephi.log;

import edu.mephi.human.Human;
import edu.mephi.measurement.Measurement;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteLog {
  private BufferedWriter writer;
  private Human human;

  public WriteLog(Human human) throws IOException {
    this.human = human;
    String filename = human.getLogFilename();
    writer = new BufferedWriter(new FileWriter(filename, true));
  }

  public void createNewLogFile() throws IOException {
    writer.write(human.toString());
    writer.close();
  }

  public void writeMeasurementInLogFile(Measurement measurement)
      throws IOException {
    String newString = new String("\n" + measurement.toString());
    writer.append(newString);
  }

  public void closeLogFile() throws IOException { writer.close(); }
}
