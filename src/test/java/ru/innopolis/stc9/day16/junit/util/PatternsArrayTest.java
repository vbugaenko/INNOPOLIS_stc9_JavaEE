package ru.innopolis.stc9.day16.junit.util;

import org.junit.Before;
import org.junit.Test;

import java.util.regex.Pattern;

import static junit.framework.TestCase.assertEquals;

public class PatternsArrayTest {

  private PatternsArray ptAr;

  @Before
  public void before() {
    String[] strAr = {"One"};
    this.ptAr = new PatternsArray(strAr);
  }

  @Test
  public void getPatternsArray() {

    String w = "one";

    Pattern etalon = Pattern.compile(
            "(\\s(" + w + ")\\s)"
                    + "|" +
                    "(.(" + w + ")\\s)"
                    + "|" +
                    "(\\s(" + w + ").)"
                    + "|" +
                    "(\\((" + w + ")\\))"
                    + "|" +
                    "(\"(" + w + ")\")"
    );

    assertEquals(etalon.toString(), ptAr.getArray()[0].toString());
  }


}
