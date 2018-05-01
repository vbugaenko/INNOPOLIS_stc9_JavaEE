package ru.innopolis.stc9.day16.junit.parser;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.innopolis.stc9.day16.junit.util.CounterThreads;

import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

public class ParserTest {

  @Before
  public void create25kLinesFile() {
    try (FileWriter fw = new FileWriter("25kLinesFile")) {
      int i = 0;
      while (i < 16000) {
        i++;
        fw.append(i + "text \n");
      }
      fw.flush();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Test
  public void startAnalyzersTest() throws InterruptedException {

    Pattern[] pat = {Pattern.compile("\\s(tmp)\\s")};

    Parser par;
    new Thread(
            par = new Parser("25kLinesFile", pat, new CounterThreads(), new ReentrantLock())
    ).start();

    Thread.sleep(1000);
    TestCase.assertTrue(par.analyzerCounter==4);

  }

  @After
  public void clear() {
    new File("25kLinesFile").delete();
  }

}
