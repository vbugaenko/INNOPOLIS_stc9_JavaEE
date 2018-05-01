package ru.innopolis.stc9.day16.junit.exceptions;


/**
 * Day 7: Multithreading parser.
 * Исключение выбрасывается в случае некорректных данных в параметрах метода;
 *
 * @author Victor Bugaenko
 * @since 25.04.2018
 */

public class NotWorkingWithNullException extends Exception {

  @Override
  public String getMessage() {
    return this.getClass().getSimpleName() + ": В параметрах метода некорректные данные";
  }
}