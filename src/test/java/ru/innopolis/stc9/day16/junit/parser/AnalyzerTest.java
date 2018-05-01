package ru.innopolis.stc9.day16.junit.parser;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

public class AnalyzerTest {

  @Test
  public void analyzerTest() throws InterruptedException {
    String[] linesForAnalyzis = {
            "Sentence1. First long sentence2. Second sentence3. Third long sentence4.",
            "Sentences5. First long sentence6. Second sentence7. Third long sentence8."};
    Pattern[] patterns = {Pattern.compile("\\s(long)\\s")};
    ParserStorage parserStorage = new ParserStorage();
    ReentrantLock locker = new ReentrantLock();
    CounterAnalyzers count = new CounterAnalyzers();
    Analyzer an;
    new Thread(
            an = new Analyzer(linesForAnalyzis, patterns, parserStorage,  count,  locker)
    ).start();
    Thread.sleep(2000);
    //for(String s : parserStorage.getSentencesItog())
    //  System.out.println(s);

    TestCase.assertTrue(parserStorage.getSentencesItog().size()==4);
  }
}
