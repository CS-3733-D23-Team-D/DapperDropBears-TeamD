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

  public static void main(String[] args) throws SQLException {
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
}
