package edu.wpi.teamname.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import lombok.Getter;
import lombok.Setter;

public class Meal {
  @Setter @Getter private String name;
  @Setter @Getter private int mealID;
  @Setter @Getter private float price;
  @Setter @Getter private String meal;
  @Setter @Getter private String cuisine;

  public Meal(int mealID, String name, float price, String meal, String cuisine) {
    this.mealID = mealID;
    this.name = name;
    this.price = price;
    this.meal = meal;
    this.cuisine = cuisine;
  }

  public Meal(int mealID) throws SQLException {
    this.mealID = mealID;
    DatabaseConnection dbc = new DatabaseConnection();
    Connection connection = dbc.DbConnection();
    String query = "SELECT * FROM \"Meal\" WHERE \"mealID\" = " + mealID + ";";

    try (Statement statement = connection.createStatement()) {
      ResultSet rs = statement.executeQuery(query);
      while (rs.next()) {
        this.name = rs.getString("Name");
        this.price = rs.getFloat("price");
        this.meal = rs.getString("Meal");
        this.cuisine = rs.getString("Cuisine");
      }
    } catch (SQLException e) {
      System.out.println("Error retrieving meal data: " + e.getMessage());
    }
  }

  public boolean equals(Meal other) {
    if (this.mealID == other.mealID) {
      return true;
    }
    return false;
  }

  public String toString() {
    return "mealID: "
        + mealID
        + ", name: "
        + name
        + ", price: "
        + price
        + ", meal: "
        + meal
        + ", cuisine: "
        + cuisine;
  }
}
