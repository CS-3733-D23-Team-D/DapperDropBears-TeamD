package edu.wpi.teamname;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DataManager {

  private static final String DB_URL = "jdbc:postgresql://database.cs.wpi.edu:5432/teamddb";
  private static final String DB_USER = "teamddb";
  private static final String DB_PASSWORD = "teamd40";
  private static int numColumns;

  public DataManager(String filePath, String tableName) {}

  /** Prototype 1: method should read file from CSV and import data to teamd database */
  public static void importCSV(String csvFilePath, String tableName) {

    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
      String insertQuery = generateInsertQuery(tableName);

      try (PreparedStatement ps = conn.prepareStatement(insertQuery);
          BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {

        // Read the CSV file line by line
        String line;
        while ((line = br.readLine()) != null) {
          // Split each line by comma separator
          String[] values = line.split(",");

          // Set the values of the PreparedStatement
          for (int i = 1; i <= values.length; i++) {
            ps.setString(i, values[i - 1]);
          }

          // Execute the PreparedStatement
          ps.executeUpdate();
        }

        System.out.println("CSV import completed successfully.");

      } catch (SQLException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static String generateInsertQuery(String tableName) {
    String query = "INSERT INTO " + tableName + " VALUES(";
    // Assume the CSV file has the same number of columns as the database table
    for (int i = 1; i <= numColumns; i++) {
      query += "?";
      if (i != numColumns) {
        query += ",";
      }
    }
    query += ")";
    return query;
  }
  // Import CSV data to PostgreSQL table
  public static void importToPostgreSQL(
      String csvFilePath, String dbURL, String dbUsername, String dbPassword, String tableName) {

    try {
      // Load PostgreSQL JDBC driver
      Class.forName("org.postgresql.Driver");

      // Open database connection
      Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

      // Prepare SQL statement to insert data into table
      String insertSQL = "INSERT INTO " + tableName + " VALUES (?, ?, ?, ?, ?)";
      PreparedStatement ps = conn.prepareStatement(insertSQL);

      // Read CSV file and insert data into table
      BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        ps.setInt(1, Integer.parseInt(values[0]));
        ps.setString(2, values[1]);
        ps.setString(3, values[2]);
        ps.setString(4, values[3]);
        ps.setDouble(5, Double.parseDouble(values[4]));
        ps.executeUpdate();
      }

      // Close resources
      ps.close();
      br.close();
      conn.close();

      System.out.println("CSV data imported to PostgreSQL table successfully!");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void displayNodeInfo() {
    System.out.println("node info");
  }

  public static void displayEdgeInfo() {
    System.out.println("edge info");
  }

  public static void importData() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the file path of the CSV file to import: ");
    String filePath = scanner.nextLine();

    System.out.print("Enter the name of the table in Postgres to export to: ");
    String tableName = scanner.nextLine();

    DataManager readCSV = new DataManager(filePath, tableName);
    readCSV.importToPostgreSQL(filePath, tableName, DB_URL, DB_USER, DB_PASSWORD);
  }

  public static void exportData() {
    System.out.println("export data");
  }

  public static void updateNodeCoords() {
    System.out.println("update coords");
  }

  public static void updateNodeName() {
    System.out.println("update name");
  }

  public static void displayHelp() {
    System.out.println(
        "---Help---\n"
            + "Choose from the following commands:\n"
            + "(1) Display node information\n"
            + "(2) Display edge information\n"
            + "(3) Import data from CSV file\n"
            + "(4) Export data into CSV file\n"
            + "(5) Update node coordinates\n"
            + "(6) Update node name\n"
            + "(7) Display Help\n"
            + "(8) Exit\n"
            + "Input the number of the command you want to execute\n"
            + "(1) Display node information\n"
            + "\t-Gives all of the information regarding a specific node\n"
            + "(2) Display edge information\n"
            + "\t-Gives all of the information regarding a specific node\n"
            + "(3) Import data from CSV file\n"
            + "\t-Takes node data from a given CSV file and uploads it into the database\n"
            + "(4) Export data into CSV file\n"
            + "\tTakes node data from the database and exports it into a given CSV file\n"
            + "(5) Update node coordinates\n"
            + "\t-Changes the coordinate of a given node to a new value\n"
            + "(6) Update node name\n"
            + "\t-Changes the name of a given node to a new value\n"
            + "(7) Display Help\n"
            + "\t-Displays this text\n"
            + "(8) Exit\n"
            + "\t-Terminates the program");
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean running = true;
    System.out.println(
        "Choose from the following commands:\n"
            + "(1) Display node information\n"
            + "(2) Display edge information\n"
            + "(3) Import data from CSV file\n"
            + "(4) Export data into CSV file\n"
            + "(5) Update node coordinates\n"
            + "(6) Update node name\n"
            + "(7) Display Help\n"
            + "(8) Exit");

    String optionChosen = "help";
    while (running) {
      optionChosen = scanner.nextLine();
      optionChosen = optionChosen.toLowerCase();
      optionChosen = optionChosen.replaceAll("\\s", ""); // Removes whitespace
      switch (optionChosen) {
        case "1":
          displayNodeInfo();
          break;
        case "2":
          displayEdgeInfo();
          break;
        case "3":
          importData();
          break;
        case "4":
          exportData();
          break;
        case "5":
          updateNodeCoords();
          break;
        case "6":
          updateNodeName();
          break;
        case "7":
        case "help":
        case "displayhelp":
          displayHelp();
          break;
        case "8":
        case "exit":
          System.out.println("Terminating program");
          running = false;
          break;
        default:
          System.out.println("Invalid command. Please try again");
          break;
      }
    }
  }
}
