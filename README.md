Logbook
=======

Compilation Instructions
------------------------
Once you have obtained a copy of this repository, simply run
  javac -d <TargetDirectory> src/Logging.java
to compile the logbook.

Integration
-----------
To use the logbook, you will need to import the compiled .class file into your project:
  import com.adarwin.logging.Logbook;

API
---
The Logbook constructor takes a filepath to your desired log file, as represented by a string.
  Logbook myLogbook = new Logbook("path/to/my/application.log");

To issue a log command, use one of the following methods from the Logbook class:
  boolean log(String message)
  boolean log(String type, String message)
  boolean log(Exception exception)

Notice the 'type' parameter listed in the method section. The available types are:
  static String ERROR
  statis String WARNING
  static String INFO
