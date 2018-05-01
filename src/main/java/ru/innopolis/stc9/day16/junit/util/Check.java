package ru.innopolis.stc9.day16.junit.util;

import ru.innopolis.stc9.day16.junit.exceptions.NotWorkingWithNullException;

import java.util.Arrays;

/**
 * Day 7: Multithreading parser.
 * Проверяем данные на корректность;
 *
 * @author Victor Bugaenko
 * @since 25.04.2018
 */

public class Check {

  /**
   * Проверка на пустое значение.
   * (приходящей в параметры какого-то метода строки).
   * @param   str
   *          проверяемая строка;
   *
   * @throws NotWorkingWithNullException
   *          мы точно знаем, что проверять нужно именно параметры метода;
   */
  public void forNull(final String str) throws NotWorkingWithNullException {

    if ((str == null) | (str.equals("")))
      throw new NotWorkingWithNullException();

  }

  /**
   * Проверка не только на подмену null'ом самого массива
   * но и на содержание внутри этим массивом null значений и пустых строк.
   * @param   strAr
   *          проверяемый массив строк;
   *
   * @throws  NotWorkingWithNullException
   *          мы точно знаем, что проверять нужно именно параметры метода;
   */
  public void forNull(final String[] strAr) throws NotWorkingWithNullException {

    if (strAr == null)
      throw new NotWorkingWithNullException();

    if (Arrays.asList(strAr).contains(null))
      throw new NotWorkingWithNullException();

    if (Arrays.asList(strAr).contains(""))
      throw new NotWorkingWithNullException();
  }
}
