package ru.innopolis.stc9.day16.junit.connect;

import junit.framework.TestCase;
import org.junit.Test;
import ru.innopolis.stc9.day16.junit.connect.Connect;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConnectTest {

  @Test
  public void getInWEB() {
    String str="http";
    TestCase.assertTrue (new Connect(str).connectionType == Connect.ConnectionType.WEB);
  }
  @Test
  public void getInWEBwww() {
    String str="www.";
    TestCase.assertTrue (new Connect(str).connectionType == Connect.ConnectionType.WEB);
  }
  @Test
  public void getInFTP() {
    String str="ftp://ftp.intel.com/readme.txt";
    TestCase.assertTrue (new Connect(str).connectionType == Connect.ConnectionType.FTP);
  }

  @Test
  public void getInLOCAL() throws IOException {
    String str="file";
    Files.createFile(Paths.get(str));
    TestCase.assertTrue (new Connect(str).connectionType == Connect.ConnectionType.LOCAL);
    Files.delete(Paths.get(str));
  }

}
