package edu.wpi.teamname;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
  private static final String DB_URL = "jdbc:postgresql://database.cs.wpi.edu:5432/teamddb";
  private static final String DB_USER = "teamd";
  private static final String DB_PASSWORD = "teamd40";

  public Connection DbConnection() {
    Connection connection = null;

    System.out.println("-------- Step 2: Building a Connection ------");
    try {
      connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
      return connection;
    } catch (SQLException e) {
      System.out.println("Connection Failed! Check output console");
      e.printStackTrace();
      return null;
    }
  }

  // comments
}
