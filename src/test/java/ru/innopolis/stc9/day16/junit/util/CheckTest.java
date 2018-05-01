package ru.innopolis.stc9.day16.junit.util;

import org.junit.Before;
import org.junit.Test;
import ru.innopolis.stc9.day16.junit.exceptions.NotWorkingWithNullException;

public class CheckTest {

  private Check check;

  @Before
  public void before(){
    this.check = new Check();
  }

  @Test(expected = NotWorkingWithNullException.class)
  public void forNullWithEptyString() throws NotWorkingWithNullException {
    //NPE в тесте опережает собственное исключение
    //java.lang.Exception: Unexpected exception, expected<ru.innopolis.stc9.exceptions.NotWorkingWithNullException> but was<java.lang.NullPointerException>
    //String str = null;
    String str = "";
    check.forNull(str);
  }

  @Test(expected = NotWorkingWithNullException.class)
  public void forNull_Null_InsteadStringArray() throws NotWorkingWithNullException {
    String[] str = null;
    check.forNull(str);
  }

  @Test(expected = NotWorkingWithNullException.class)
  public void forNull_WithNull_InStringArray() throws NotWorkingWithNullException {
    String[] str = {null};
    check.forNull(str);
  }

  @Test(expected = NotWorkingWithNullException.class)
  public void forNull_WithEmpty_InStringArray() throws NotWorkingWithNullException {
    String[] str = {""};
    check.forNull(str);
  }
}
