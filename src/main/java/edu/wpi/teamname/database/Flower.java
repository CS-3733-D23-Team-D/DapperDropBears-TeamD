package edu.wpi.teamname.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import lombok.Getter;
import lombok.Setter;

public class Flower {
  @Setter @Getter private String name;
  @Setter @Getter private int flowerID;
  @Getter @Setter private float price;
  @Getter @Setter private String category;
  @Getter @Setter private String color;

  public Flower(int flowerID, String name, float price, String category, String color) {
    this.flowerID = flowerID;
    this.name = name;
    this.price = price;
    this.category = category;
    this.color = color;
  }

  public Flower(int flowerID) throws SQLException {
    this.flowerID = flowerID;
    DatabaseConnection dbc = new DatabaseConnection();
    Connection connection = dbc.DbConnection();
    String query = "SELECT * FROM \"Flowers\" WHERE \"flowerID\" = " + flowerID + ";";

    try (Statement statement = connection.createStatement()) {
      ResultSet rs = statement.executeQuery(query);
      while (rs.next()) {
        this.name = rs.getString("Name");
        this.price = rs.getFloat("price");
        this.category = rs.getString("Category");
        this.color = rs.getString("Color");
      }
    } catch (SQLException e) {
      System.out.println("Error retrieving flower data: " + e.getMessage());
    }
  }
}
