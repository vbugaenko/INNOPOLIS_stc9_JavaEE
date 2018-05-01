package ru.innopolis.stc9.day16.junit.util;

import java.util.regex.Pattern;

public class PatternsArray {

  private Pattern[] patterns;

  public PatternsArray(String[] words) {

    words = toLowerCase(words);
    this.patterns = getPatternsArray(words);

  }

  private Pattern[] getPatternsArray(String[] words) {

    Pattern[] patterns = new Pattern[words.length];

    int i= 0;
    for (String w : words) {
      Pattern pattern = Pattern.compile(
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
      patterns[i]=pattern;
      i++;
    }
    return patterns;
  }

  private String[] toLowerCase(String[] words) {
    int i = 0;
    String[] wordsToLowerCase = new String[words.length];
    for (String w : words) {
      w = w.toLowerCase();
      wordsToLowerCase[i] = w;
      i++;
    }
    return wordsToLowerCase;
  }

  public Pattern[] getArray() {
    return patterns;
  }
}
