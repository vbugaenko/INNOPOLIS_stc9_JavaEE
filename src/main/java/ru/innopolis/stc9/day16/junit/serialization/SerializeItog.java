package ru.innopolis.stc9.day16.junit.serialization;

import org.apache.log4j.Logger;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Set;

/**
 * Day 7: Multithreading parser.
 * Сериализация итоговых данных в фаил на диске;
 *
 * @author Victor Bugaenko
 * @since 25.04.2018
 */

public class SerializeItog {
  final Logger logger = Logger.getLogger(SerializeItog.class);

  public SerializeItog(String filename, Set<String> storage) {

    try (ObjectOutputStream bOutputStream = new ObjectOutputStream(new FileOutputStream(filename))) {

      bOutputStream.writeObject(storage);

      logger.info("data saved ["+ filename +"]");

    } catch (Exception e) {
      System.out.println(e);
    }
  }

}