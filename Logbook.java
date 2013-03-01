/*
  Andrew Darwin
  www.adarwin.com
  January 2013
*/

package com.adarwin.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Logbook
{
  private String logFilePath;
  public static String ERROR = "ERROR  ";
  public static String WARNING = "WARNING";
  public static String INFO = "INFO   ";

  public Logbook(String logFilePath)
  {
    try
    {
      File file = new File(logFilePath);
      if (!file.exists())
      {
        file.createNewFile();
      }
    }
    catch (IOException ex)
    {
      System.err.println(ex.getMessage());
      System.err.println(ex.getStackTrace());
    }
    this.logFilePath = logFilePath;
  }

  public boolean log(String message)
  {
    return log(INFO, message);
  }
  public boolean log(String type, String message)
  {
    boolean successful = true;
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true)))
    {
      writer.write(type + " - " + getCurrentTime() + ": " + message);
      writer.newLine();
    }
    catch (IOException ex)
    {
      System.err.println(ex.getMessage());
      System.err.println(ex.getStackTrace());
      successful = false;
    }
    return successful;
  }
  public boolean log(Exception exception)
  {
    return log(ERROR, exception.getMessage());
  }

  public List<String> extractEntries(String type)
  {
    return null;
  }


  private String getCurrentTime()
  {
    DateFormat dateFormat = new SimpleDateFormat("yyy/MM/dd HH:mm:ss");
    Date date = new Date();
    String time = dateFormat.format(date);
    return time;
  }
}
