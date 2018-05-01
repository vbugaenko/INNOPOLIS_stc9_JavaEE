package ru.innopolis.stc9.day16.junit;

import ru.innopolis.stc9.day16.junit.exceptions.NotWorkingWithNullException;

/**
 * Day 7: Multithreading parser.
 *
 * @author Victor Bugaenko
 * @since 05.04.2018
 */

public interface Responsibilities {

  void getOccurencies(String[] sources, String[] words, String res)
          throws NotWorkingWithNullException, InterruptedException;

}
