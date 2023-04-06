package edu.wpi.teamname.database;

import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemsOrdered {
  @Setter @Getter private int requestID;
  @Setter @Getter private int itemID;
  @Setter @Getter private int quantity;

  public ItemsOrdered(int requestID, int itemID, int quantity) {
    this.requestID = requestID;
    this.itemID = itemID;
    this.quantity = quantity;
  }

  public String toString() {
    return "[" + requestID + " " + itemID + " " + quantity + "]";
  }

  /**
   * Retrieves all service requests from the "ItemsOrdered" table in the database and returns them
   * as an ArrayList of ItemsOrdered objects.
   *
   * @return An ArrayList of ItemsOrdered objects that represent all objects ordered in the
   *     "ItemsOrdered" table.
   * @throws SQLException if a database access error occurs
   */
  public static ArrayList<ItemsOrdered> getAllItemsOrdered() {
    ArrayList<ItemsOrdered> list = new ArrayList<ItemsOrdered>();
    DatabaseConnection dbc = new DatabaseConnection();
    Connection connection = dbc.DbConnection();
    try {
      String query = "SELECT * FROM \"ItemsOrdered\"";
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        ItemsOrdered ir =
                new ItemsOrdered(rs.getInt("requestID"), rs.getInt("itemID"), rs.getInt("quantity"));
        list.add(ir);
      }
      connection.close();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return list;
  }
}
