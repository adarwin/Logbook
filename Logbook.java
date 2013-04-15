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
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Logbook implements Serializable
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
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true)))
    {
      writer.newLine();
      writer.newLine();
      writer.write("----- Logbook Object Initialized -----");
      writer.newLine();
      writer.newLine();
    }
    catch (IOException ex)
    {
      System.err.println(ex.getMessage());
      System.err.println(ex.getStackTrace());
    }
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
      type += " - ";
      char[] charArray = (type).toCharArray();
      for(int i = 0; i < charArray.length; i++)
      {
        charArray[i] = ' ';
      }
      String messageIndent = new String(charArray);
      writer.write(type + getCurrentTime() + ":\n"
                   + messageIndent + message + "\n");
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



  public boolean log(String type, String header, String message)
  {
    return log(type, header + ": " + message);
  }



  public boolean log(Exception exception)
  {
    return log(ERROR, exception.getMessage());
  }


  public boolean log(String header, Exception exception)
  {
    return log(ERROR, header, exception.getMessage());
  }



  private List<String> extractEntries(String type)
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
