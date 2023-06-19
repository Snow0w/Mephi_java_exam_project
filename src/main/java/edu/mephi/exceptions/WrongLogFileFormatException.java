package edu.mephi.exceptions;

public class WrongLogFileFormatException extends Exception {
  public WrongLogFileFormatException(String errorMessage) {
    super(errorMessage);
  }
}
