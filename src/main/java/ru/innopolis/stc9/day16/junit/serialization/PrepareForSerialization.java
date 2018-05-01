package ru.innopolis.stc9.day16.junit.serialization;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.day16.junit.parser.Parser;

import java.util.HashSet;
import java.util.Set;

public class PrepareForSerialization {

  final Logger logger = Logger.getLogger(PrepareForSerialization.class);
  Parser[] parsers;

  public PrepareForSerialization(Parser[] parsers) {
    this.parsers = parsers;
  }

  /**
   * Перед сериализацией данные конвертируются в более простой вид.
   */
  public Set<String> convertToSentencesSet() {

    Set<String> itogRaboti = new HashSet<>();
    for (Parser p : parsers)
      itogRaboti.addAll(p.getParserStorage().getSentencesItog());

    logResult(itogRaboti.size());

    return itogRaboti;
  }

  private void logResult(int i) {
    logger.info("got sentences ["+ i +"]");
  }
}
