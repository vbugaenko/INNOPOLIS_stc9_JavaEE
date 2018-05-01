package ru.innopolis.stc9.day16.junit.parser;

import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Day 7: Multithreading parser.
 * Для большей корректности лучше, когда ресурсы нормализованы
 * (предложения не прерываются переводом строки);
 *
 * @author Victor Bugaenko
 * @since 25.04.2018
 */

public class Analyzer implements Runnable {

  private String[] linesForAnalysis;
  private Pattern[] patterns;
  private ParserStorage parserStorage;
  private ReentrantLock lockerForAnalyzers;
  private CounterAnalyzers counterAnalyzers;

  public Analyzer(String[] linesForAnalysis, Pattern[] patterns, ParserStorage parserStorage, CounterAnalyzers counterAnalyzers, ReentrantLock lockerForAnalyzers) {
    this.linesForAnalysis = linesForAnalysis;
    this.patterns = patterns;
    this.parserStorage = parserStorage;
    this.lockerForAnalyzers = lockerForAnalyzers;
    this.counterAnalyzers = counterAnalyzers;
  }

  @Override
  public void run() {

    addCount();

    lookLinesForSentences();

    minusCount();
  }

  private void lookLinesForSentences() {
    for (String line : linesForAnalysis) {
      String[] sentencesFromLine = line.split("[.!?;]\\s|\"|<|>|\\n|\\r");
      for (String s : sentencesFromLine)
        lookPatternsIn(s);
    }
  }

  /**
   * Ищет в предложении паттерны целевых слов.
   * Полная сверка не нужна, поэтому используется перебор паттернов в цикле,
   * с выходом из цикла после первого же совпадения;
   */
  private void lookPatternsIn(String sentence) {

    for (Pattern p : patterns) {
      Matcher matcher = p.matcher(sentence);

      if (matcher.find()) {
        addOccurencies(sentence);
        break;
      }
    }
  }

  /**
   * Добавляет найденное предложение в коллекцию.
   * перед добавлением невидимые символы по краям обрезаются;
   */
  private void addOccurencies(String sentence) {
    lockerForAnalyzers.lock();
    parserStorage.sentencesItogAdd(sentence.trim());
    lockerForAnalyzers.unlock();
  }

  private void addCount() {
    lockerForAnalyzers.lock();
    counterAnalyzers.count++;
    lockerForAnalyzers.unlock();
  }

  private void minusCount() {
    lockerForAnalyzers.lock();
    counterAnalyzers.count--;
    lockerForAnalyzers.unlock();
  }
}
