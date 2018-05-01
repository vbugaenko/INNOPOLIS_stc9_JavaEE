package ru.innopolis.stc9.day16.junit.connect;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Day 7: Multithreading parser
 * Распознание типа ресурса (фаил, ftp, web);
 * Распознание кодировки и открытие потока для чтения файла;
 * Уточнение размера ресурса и вообще возможности его обработать целиком;
 *
 * @author Victor Bugaenko
 * @since 25.04.2018
 */

public class Connect {

  final org.apache.log4j.Logger logger = Logger.getLogger(Connect.class);

  Scanner scan;

  enum ConnectionType { FTP, WEB, LOCAL }

  public ConnectionType connectionType;

  private String source;

  public Connect(String source) {
    this.source = source;

    String protocol = source.substring(0, 4);

    try {
      if (protocol.equals("ftp:")) {
        this.connectionType = ConnectionType.FTP;
        scan = new FTP(source).connect();
        return;
      }
      if ((protocol.equals("http")) | (protocol.equals("www."))) {
        this.connectionType = ConnectionType.WEB;
        scan = new WEB(source).connect();
        return;
      }
      if (Files.exists(Paths.get(source))) {
        this.connectionType = ConnectionType.LOCAL;
        scan = new LOCAL(source).connect();
      }
      else
        logger.warn("Не могу понять, что за источник [" + source + "]");

    } catch (IOException e) {
      logger.error(e.toString());
    }
  }

  public Scanner getIn() throws IOException {
    return scan;
  }


}