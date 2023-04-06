package edu.wpi.teamname.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
  private static final String DB_URL =
      "jdbc:postgresql://database.cs.wpi.edu:5432/teamddb?currentSchema=\"teamD\"";
  private static final String DB_USER = "teamd";
  private static final String DB_PASSWORD = "teamd40";

  /** Main function that connects to the database, or it will display an error if it does not. */
  public Connection DbConnection() {
    Connection connection = null;

    System.out.print("--- Connecting To Database... ---");
    try {
      Class.forName("org.postgresql.Driver");
      connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
      System.out.println(" Successfully connected to database");
      return connection;
    } catch (SQLException e) {
      System.out.println(" Connection Failed! Check output console");
      e.printStackTrace();
      return null;
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  // comments
}
