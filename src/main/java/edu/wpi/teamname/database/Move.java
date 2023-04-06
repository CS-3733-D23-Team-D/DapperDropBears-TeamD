package edu.wpi.teamname.database;

import edu.wpi.teamname.navigation.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

public class Move {
  @Getter @Setter private int nodeID;
  @Getter @Setter private String longName;
  @Getter @Setter private String date;
  @Getter @Setter private Node node;
  @Getter @Setter private LocationName locationName;

  public Move(int nodeID, String longName, String date) {
    this.nodeID = nodeID;
    this.longName = longName;
    this.date = date;
  }

  public boolean swapNodes(String swapNodeID) {
    boolean done = false;
    // Swap nodeID in node
    // Swap startNodes with eachother, and end nodes with eachother in edge
    // using

    return done;
  }

  /**
   * Retrieves all Move objects from the "Move" database table and returns them as an ArrayList.
   * Each Move object is created using the data retrieved from the "nodeID," "longName," and "date"
   * columns of the table.
   *
   * @return an ArrayList of Move objects containing all the data from the "Move" table
   * @throws SQLException if an error occurs while attempting to retrieve data from the database
   */
  public static ArrayList<Move> getAllMoveObjects() {
    ArrayList<Move> list = new ArrayList<Move>();
    DatabaseConnection dbc = new DatabaseConnection();
    Connection connection = dbc.DbConnection();
    try {
      String query = "SELECT * FROM \"Move\"";
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        Move ln = new Move(rs.getInt("nodeID"), rs.getString("longName"), rs.getString("date"));
        list.add(ln);
      }
      connection.close();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return list;
  }
}
