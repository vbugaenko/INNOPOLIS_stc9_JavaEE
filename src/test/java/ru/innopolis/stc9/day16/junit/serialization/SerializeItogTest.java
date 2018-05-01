package ru.innopolis.stc9.day16.junit.serialization;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class SerializeItogTest {

  Set<String> storage = new HashSet();

  @Before
  public void createPatternsArray(){
    storage.add("Sentence one.");
    storage.add("Sentence two.");
    storage.add("Sentence three.");
  }

  @Test
  public void serializeTest(){
    new SerializeItog("fileForSerializationTest", storage);

    HashSet<String> resultSentences = new HashSet();

    try (FileInputStream fis = new FileInputStream("fileForSerializationTest")) {
      ObjectInputStream ois = new ObjectInputStream(fis);

      resultSentences = (HashSet<String>) ois.readObject();

    } catch (Exception e) {
      System.out.println(e);
    }
    //resultSentences.add("Sentence four.");  // If we wont failure this test
    TestCase.assertTrue((storage.size()==resultSentences.size())&(storage.containsAll(resultSentences)));
  }

  @After
  public void clear(){
    try {
      Files.delete(Paths.get("fileForSerializationTest"));
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
