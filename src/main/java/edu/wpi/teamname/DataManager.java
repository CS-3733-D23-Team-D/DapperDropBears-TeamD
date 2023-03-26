package edu.wpi.teamname;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DataManager {

  private static final String DB_URL = "jdbc:postgresql://database.cs.wpi.edu:5432/teamddb";
  private static final String DB_USER = "teamd";
  private static final String DB_PASSWORD = "teamd40";

  /** Prototype 1: method should read file from CSV and import data to teamd database */
  public static List<String[]> importCSV(String fileName) {
    System.out.println("Parsing CSV file: " + fileName);
    List<String[]> rows = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] row = line.split(",", -1);
        rows.add(row);
      }
      System.out.println("CSV file parsed successfully");
      return rows;
    } catch (IOException e) {
      System.err.println("Error parsing CSV file: " + e.getMessage());
      return null;
    }
  }

  //  public static void uploadToPostgreSQL(List<String[]> csvData) throws SQLException {
  //    String url =
  //        "jdbc:postgresql://database.cs.wpi.edu:5432/teamddb"; // replace with your database URL
  //    String user = "teamd"; // replace with your database username
  //    String password = "teamd40"; // replace with your database password
  //
  //    try (Connection conn = DriverManager.getConnection(url, user, password)) {
  //      String query =
  //          "INSERT INTO mytable (nodeID, xcoord, ycoord, floor, building, nodeType, longName,
  // shortName) "
  //              + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
  //      PreparedStatement statement = conn.prepareStatement(query);
  //
  //      for (String[] row : csvData) {
  //        statement.setInt(1, Integer.parseInt(row[0])); // assuming nodeID is an integer column
  //        statement.setDouble(2, Double.parseDouble(row[1])); // assuming xcoord is a double
  // column
  //        statement.setDouble(3, Double.parseDouble(row[2])); // assuming ycoord is a double
  // column
  //        statement.setString(4, row[3]); // assuming floor is a string column
  //        statement.setString(5, row[4]); // assuming building is a string column
  //        statement.setString(6, row[5]); // assuming nodeType is a string column
  //        statement.setString(7, row[6]); // assuming longName is a string column
  //        statement.setString(8, row[7]); // assuming shortName is a string column
  //
  //        statement.executeUpdate();
  //      }
  //      System.out.println("CSV data uploaded to PostgreSQL database");
  //    } catch (SQLException e) {
  //      System.err.println("Error uploading CSV data to PostgreSQL database: " + e.getMessage());
  //    }
  //  }

  /* public static void main(String[] args) {
      System.out.println("CSV data imported to PostgreSQL table successfully!");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }*/

  public static void displayNodeInfo() {
    System.out.println("node info");
  }

  public static void displayEdgeInfo() {
    System.out.println("edge info");
  }

  public static void importData() throws SQLException {
    Scanner scanner = new Scanner(System.in);
    // No quotes when importing doc
    System.out.print("Enter the file path of the CSV file to import: ");
    String csvFileName = scanner.nextLine();
    List<String[]> rows = importCSV(csvFileName);
    if (rows != null) {
      System.out.println("Number of rows: " + rows.size());
      for (String[] row : rows) {
        System.out.println(Arrays.toString(row));
      }
      //      try {
      //        uploadToPostgreSQL(rows);
      //      } catch (SQLException e) {
      //        System.err.println(e.getMessage());
      //      }
    }
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

  public static void main(String[] args) throws SQLException {
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
