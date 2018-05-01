package ru.innopolis.stc9.day16.junit.exceptions;

/**
 * Day 7: Multithreading parser.
 * Исключение выбрасывается в случае некорректных данных в параметрах метода;
 *
 * @author Victor Bugaenko
 * @since 30.04.2018
 */

public class NotUnderstandResourceException extends Exception {

  @Override
  public String getMessage() {
    return this.getClass().getSimpleName() + ": Непоняно, что за источник";
  }
}
