package ru.innopolis.stc9.day16.junit;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.innopolis.stc9.day16.junit.Occurencies;
import ru.innopolis.stc9.day16.junit.exceptions.NotWorkingWithNullException;

import java.io.*;
import java.util.HashSet;
//import org.junit.Test;
//import static org.mockito.Mockito.*;

public class OccurenciesResourcesNotAvailableTest {

  private Occurencies occurencies;

  @Before
  public void before(){
    this.occurencies = new Occurencies();
  }


  @Test
  public void resultFileDeserializeTest() throws NotWorkingWithNullException, IOException {

    try (
            FileWriter fw =new FileWriter("source")
    ){
      fw.append("some long. Very long text. text.\n");
      fw.append("next line with long text. Very long text in new line. Good text.\n");
      fw.append("some long. Very long. else.\n");
      fw.append("third line.\n");
      fw.flush();
      fw.close();
    }
    catch (Exception ex) {}


    String[] sourches2 = {"source"};
    String[] words2 = {"line", "long", "text"};

    String resultFile2 = "resultFileForDeserialize";

    new Occurencies().getOccurencies(sourches2, words2, resultFile2);

    HashSet<String> resultSentences2 = new HashSet();

    try (FileInputStream fis = new FileInputStream("resultFileForDeserialize")) {
      ObjectInputStream ois = new ObjectInputStream(fis);

      resultSentences2 = (HashSet<String>) ois.readObject();

      for (String s : resultSentences2)
        System.out.println(s);

    } catch (Exception e) {
      System.out.println(e);
    }

    TestCase.assertTrue(!resultSentences2.isEmpty());
  }

  @After
  public void clear(){
    new File("source").delete();
    new File("resultFileForDeserialize").delete();
  }

}
