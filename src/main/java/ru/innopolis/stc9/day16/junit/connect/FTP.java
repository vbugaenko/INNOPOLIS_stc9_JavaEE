package ru.innopolis.stc9.day16.junit.connect;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class FTP {

  final org.apache.log4j.Logger logger = Logger.getLogger(FTP.class);

  String source;

  public FTP(String source) {
    this.source = source;
  }

  public Scanner connect() throws IOException {
    return new Scanner(new URL(source).openStream());
  }
}
