package ru.innopolis.stc9.day16.junit;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.day16.junit.exceptions.NotWorkingWithNullException;
import ru.innopolis.stc9.day16.junit.parser.Parser;
import ru.innopolis.stc9.day16.junit.serialization.PrepareForSerialization;
import ru.innopolis.stc9.day16.junit.serialization.SerializeItog;
import ru.innopolis.stc9.day16.junit.util.Check;
import ru.innopolis.stc9.day16.junit.util.CounterThreads;
import ru.innopolis.stc9.day16.junit.util.PatternsArray;

import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

/**
 * Day 7: Multithreading parser.
 * Организует работу других классов;
 *
 * @author Victor Bugaenko
 * @since 25.04.2018
 */

public class Occurencies implements Responsibilities {

  final Logger logger = Logger.getLogger(Occurencies.class);

  private final long startTime = System.currentTimeMillis();
  private ReentrantLock locker = new ReentrantLock();
  private CounterThreads countThreads = new CounterThreads();
  private Parser[] parsers;

  /**
   * На каждый ресурс порождается отдельный поток.
   * Общее количество открытых потоков ограничено.
   * @param sources    масив ресурсов для поиска в них целевых слов;
   * @param words      слова для поиска в ресурсах;
   * @param resultFile имя файла для сохранения;
   * @throws NotWorkingWithNullException данные из вне проверяются на корректность;
   */

  public void getOccurencies(
          String[] sources, String[] words, String resultFile)
          throws NotWorkingWithNullException {

    checkForNull(sources, words, resultFile);
    runNewParserForEachSource(sources, words);
    sleepIfSomeParserWorking();
    save(resultFile, parsers);
    printTotalTimeWork();
  }

  private void runNewParserForEachSource(String[] sources, String[] words) {
    parsers = new Parser[sources.length];

    for (int i = 0; i < parsers.length; i++) {
      new Thread(parsers[i] = new Parser(
              sources[i], patterns(words), countThreads, locker)).start();
      addCountThreads();
      sleepIfTreadsMoreThan(3);
    }
  }

  private void addCountThreads() {
    locker.lock();
    countThreads.count++;
    locker.unlock();
  }

  private Pattern[] patterns(String[] words) {
    return new PatternsArray(words).getArray();
  }

  private void sleep() {
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void sleepIfSomeParserWorking() {
    while (countThreads.count > 0)
      sleep();
  }

  private void sleepIfTreadsMoreThan(int limit) {
    while (countThreads.count >= limit)
      sleep();
  }

  private void save(String resultFile, Parser[] parsers) {
    Set<String> storage = new PrepareForSerialization(parsers).convertToSentencesSet();
    new SerializeItog(resultFile, storage);
  }

  private void checkForNull(
          String[] sources, String[] words, String resultFile)
          throws NotWorkingWithNullException {

    new Check().forNull(sources);
    new Check().forNull(words);
    new Check().forNull(resultFile);
  }

  private void printTotalTimeWork() {
    logger.info("Closed all treads with "+ (System.currentTimeMillis() - startTime) + " millis");
  }

}
