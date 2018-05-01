package ru.innopolis.stc9.day16.junit.serialization;

import junit.framework.TestCase;
import org.junit.Test;
import ru.innopolis.stc9.day16.junit.parser.Parser;

public class PrepareForSerializationTest {

  @Test
  public void prepareForSerializationTest(){
    String s = "Sentence one";
    Parser p = new Parser(null, null, null, null);
    p.getParserStorage().sentencesItogAdd(s);

    Parser[] pAr = {p};
    //s = "Sentence two";   // for Failure
    TestCase.assertTrue(
    (new PrepareForSerialization(pAr).convertToSentencesSet().size()==1)&
            (new PrepareForSerialization(pAr).convertToSentencesSet().iterator().next().equals(s))
    );
  }
}
