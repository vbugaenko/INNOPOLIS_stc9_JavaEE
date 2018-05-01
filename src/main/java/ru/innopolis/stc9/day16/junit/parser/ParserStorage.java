package ru.innopolis.stc9.day16.junit.parser;

import java.util.HashSet;
import java.util.Set;

public class ParserStorage {

  private Set<String> sentencesItog = new HashSet<>();

  public Set<String> getSentencesItog() {
    return sentencesItog;
  }

  public void sentencesItogAdd(String sentence) {
    sentencesItog.add(sentence);
  }
}
