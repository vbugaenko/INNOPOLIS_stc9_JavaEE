package ru.innopolis.stc9.day16.junit.parser;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.day16.junit.connect.Connect;
import ru.innopolis.stc9.day16.junit.util.CounterThreads;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

/**
 * Day 7: Multithreading parser
 * Каждый парсер принимает массив паттернов (целевых слов) для поиска в ресурсе;
 * Сепаратор запускает в цикле несколько Analyser'ов (одновременно не более 4х):
 * - основные затраты времени - анализ, поэтому огромный текст делится на кусочки,
 * - попытка же чтения файлов в несколько соединений все равно упрется в HDD;
 *
 * @author Victor Bugaenko
 * @since 25.04.2018
 */

public class Parser implements Runnable {
  public int analyzerCounter = 0;
  final Logger logger = Logger.getLogger(Parser.class);
  private Pattern[] patterns;
  private String source;
  private ParserStorage parserStorage = new ParserStorage();
  private CounterThreads countThreads;
  private ReentrantLock locker;
  private ReentrantLock lockerForAnalyzers = new ReentrantLock();
  private CounterAnalyzers countAnalyzers = new CounterAnalyzers();
  private int limitLinesForOneAnalyzer = 5000;
  private int i = 0;
  private String[] linesForAnalysis = new String[limitLinesForOneAnalyzer];

  public Parser(String source, Pattern[] patterns, CounterThreads countThreads, ReentrantLock locker) {

    this.patterns = patterns;
    this.source = source;
    this.countThreads = countThreads;
    this.locker = locker;
  }

  public ParserStorage getParserStorage() {
    return parserStorage;
  }

  @Override
  public void run() {
    logger.info("Parser running");
    //addCount();
    reedSentencesFromConnect();
    sleepIfSomeTreadWorking();
    minusCount();
    logger.info("Parser finished");
  }



  private void reedSentencesFromConnect(){

    try (Scanner in = new Connect(source).getIn()) {

      while (in.hasNext()) {
        combineLinesForAnalyzers(in.nextLine());
        sleepIfTreadsMoreThan(4);
      }
      checkTail();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void combineLinesForAnalyzers(String lineFromScanner ){
    lineFromScanner.toLowerCase();
    linesForAnalysis[i] = lineFromScanner;
    i++;
    if (i == limitLinesForOneAnalyzer) {
      newAnalyzerThread(linesForAnalysis);
      linesForAnalysis = new String[limitLinesForOneAnalyzer];
      i = 0;
    }
  }

  private void checkTail() {
    if (i > 0) {
      String[] tailLinesForAnalisys = Arrays.copyOf(linesForAnalysis, i);
      newAnalyzerThread(tailLinesForAnalisys);
    }
  }

  private void newAnalyzerThread(String[] sentencesForAnalisys) {
    analyzerCounter++;
    new Thread(
      new Analyzer(sentencesForAnalisys, patterns, parserStorage, countAnalyzers, lockerForAnalyzers)
    ).start();
  }

  private void sleepIfSomeTreadWorking() {
    while (countAnalyzers.count > 0)
      sleep();
  }

  private void sleepIfTreadsMoreThan(int limitThreads) {
    while (countAnalyzers.count >= limitThreads)
      sleep();
  }

  private void sleep() {
    try {
      Thread.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void minusCount() {
    locker.lock();
    countThreads.count--;
    locker.unlock();
  }
}
