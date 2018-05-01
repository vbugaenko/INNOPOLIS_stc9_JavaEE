package ru.innopolis.stc9.day16.junit;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.day16.junit.exceptions.NotWorkingWithNullException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Day 16: JUnit.
 * Покрыть код тестами;
 *
 * @since 28.04.2018
 *
 * Day 15: Logging.
 * заменить sout на логгирование;
 * запуск и остановку парсера каждого ресурса логгировать Info в фаил;
 * исключения логгировать Error в отдельный фаил;
 *
 * @since 25.04.2018
 *
 * Day 7: Multithreading parser.
 * 1.Интерфейс Occurencies, метод getOccurencies;
 * 2.Добавить необходимые исключения;
 * 3.На вход массив адресов ресурсов(файлы, FTP, web ссылки);
 * - ресурсов может быть много (>2000, каждый <2kb);
 * - одиночные ресурсы могут быть <10Gb;
 * 4.На вход массив целевых слов;
 * 5.Найти в многопоточном режиме все предложения среди ресурсов в которых есть целевые слова;
 * 6.Все найденные предложения сериализуются в resultFile;
 * 7.Замерить производительность;
 *
 * @author Victor Bugaenko
 * @since 04.04.2018
 */

public class Main {
    final static private Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

        //LogManager.resetConfiguration();
        //PropertyConfigurator.configure("/media/polart/MyStorage/INNOPOLIS/Logging_day15/src/main/resources/log4j.properties");


        ArrayList<String> resourcesList = new ArrayList<>();

        try (Scanner sc = new Scanner(new File( "resLOC.txt"))){

            while (sc.hasNext()) {
                String line = sc.nextLine();
                resourcesList.add(line);
            }

            String[] words = {"измерения", "болезнь", "совет", "здоровье", "психика",
                    "психология", "восприятие", "личность", "терапия", "мотив",
                    "наука", "население", "ребенок", "психиатрия", "рецепт", "подросток",
                    "педиатрия", "шизофрения", "медицина", "человек"};

            String resultFile = "sentence.txt";
            String[] sources = resourcesList.toArray(new String[resourcesList.size()]);
            new Occurencies().getOccurencies(sources, words, resultFile);

            String[] words2 = null;
            String[] sources2 = resourcesList.toArray(new String[resourcesList.size()]);
            new Occurencies().getOccurencies(sources2, words2, resultFile);

        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (NotWorkingWithNullException e) {
            logger.error(e.getMessage());
        }

      //Files.delete(Paths.get("sentenceDB"));
      //Files.delete(Paths.get("error.log"));
      //Files.delete(Paths.get("info.log"));
    }
}
