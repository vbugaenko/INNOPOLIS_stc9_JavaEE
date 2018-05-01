package ru.innopolis.stc9.day16.junit;

import org.junit.Before;
import org.junit.Test;
import ru.innopolis.stc9.day16.junit.Occurencies;
import ru.innopolis.stc9.day16.junit.exceptions.NotWorkingWithNullException;

public class OccurenciesNullEmptyArgsTest {

  private Occurencies occurencies;

  @Before
  public void before(){
    this.occurencies = new Occurencies();
  }

  @Test(expected = NotWorkingWithNullException.class)
  public void forNull_1() throws NotWorkingWithNullException {

    String[] sources = null;
    String[] words={"One", "Two"};
    String resultFile="someFile";

    occurencies.getOccurencies(sources, words, resultFile);
  }

  @Test(expected = NotWorkingWithNullException.class)
  public void forNull_2() throws NotWorkingWithNullException {

    String[] sources = {"resource1", null};
    String[] words={"One", "Two"};
    String resultFile="someFile";

    occurencies.getOccurencies(sources, words, resultFile);
  }

  @Test(expected = NotWorkingWithNullException.class)
  public void forNull_3() throws NotWorkingWithNullException {

    String[] sources = {"resource1", "resource2"};
    String[] words=null;
    String resultFile="someFile";

    occurencies.getOccurencies(sources, words, resultFile);
  }

  @Test(expected = NotWorkingWithNullException.class)
  public void forNull_4() throws NotWorkingWithNullException {

    String[] sources = {"resource1", "resource2"};
    String[] words={"One", null};
    String resultFile="someFile";

    occurencies.getOccurencies(sources, words, resultFile);
  }

  //в коде прорывается NotWorkingWithNullException но здесь NullPointerException
  @Test(expected = NullPointerException.class)
  public void forNull_5() throws NotWorkingWithNullException {

    String s = null;
    String[] sources = {"resource1", "resource2"};
    String[] words={"One", "Two"};
    String resultFile=s;

    occurencies.getOccurencies(sources, words, resultFile);
  }
}
