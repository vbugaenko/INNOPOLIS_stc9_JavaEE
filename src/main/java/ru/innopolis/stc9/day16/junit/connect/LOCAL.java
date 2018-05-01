package ru.innopolis.stc9.day16.junit.connect;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LOCAL {

  final org.apache.log4j.Logger logger = Logger.getLogger(LOCAL.class);

  private String source;

  public LOCAL(String source) {
    this.source = source;
  }

  public Scanner connect() throws IOException {
    return new Scanner(new File(source));
  }

}
