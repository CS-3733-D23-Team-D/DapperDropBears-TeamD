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

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter the file path of the CSV file to import: ");
    String filePath = scanner.nextLine();

    System.out.print("Enter the name of the table in Postgres to export to: ");
    String tableName = scanner.nextLine();

    DataManager readCSV = new DataManager(filePath, tableName);
    readCSV.importToPostgreSQL(filePath, tableName, DB_URL, DB_USER, DB_PASSWORD);
  }
}
