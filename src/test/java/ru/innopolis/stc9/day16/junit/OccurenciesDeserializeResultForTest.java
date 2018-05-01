package ru.innopolis.stc9.day16.junit;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.innopolis.stc9.day16.junit.exceptions.NotWorkingWithNullException;

import java.io.*;
import java.util.HashSet;

public class OccurenciesDeserializeResultForTest {

  private Occurencies occurencies;

  @Before
  public void before() {
    this.occurencies = new Occurencies();
  }

  @Test
  public void resultFileDeserializeTest() throws NotWorkingWithNullException, IOException {

    try (FileWriter fw = new FileWriter("sourceForTest.txt")) {
      fw.append("some long. Very long text. text.\n text again long text \n");
      fw.append("other text without l-o-n-g. Very. text. text.\n text again long text \n");
      fw.flush();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    String[] words = {"long"};
    String[] sources = {"sourceForTest.txt"};
    String resultFile = "resultFileForDeserializeTest";

    new Occurencies().getOccurencies(sources, words, resultFile);

    HashSet<String> resultSentences = new HashSet();

    try (FileInputStream fis = new FileInputStream(resultFile)) {
      ObjectInputStream ois = new ObjectInputStream(fis);

      resultSentences = (HashSet<String>) ois.readObject();

    } catch (Exception e) {
      System.out.println(e);
    }

    TestCase.assertTrue(!resultSentences.isEmpty());
  }

  @After
  public void clear() {
    new File("sourceForTest.txt").delete();
    new File("resultFileForDeserializeTest").delete();
  }
}
