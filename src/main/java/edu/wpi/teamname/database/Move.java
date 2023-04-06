package edu.wpi.teamname.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

public class Move {
  @Getter @Setter private int nodeID;
  @Getter @Setter private String longName;
  @Getter @Setter private Date date;
  @Getter @Setter private Node node;
  @Getter @Setter private LocationName locationName;

  public Move(int nodeID, String longName, Date date) {
    this.nodeID = nodeID;
    this.longName = longName;
    this.date = date;
  }

  /**
   * Checks if this.nodeID has switched with given nodeID to swap positions, floor and building in the table
   * with it in the edge table
   * also swaps correlating information as well as switching the longNames with eachother in LocationName table
   * @param swapNodeID
   * @return Boolean
   */
  public boolean swapNodes(String swapNodeID, String swapLongName) {
    boolean done = false;
    // Swap nodeID in node
    // Swap this.nodeID with swapNodeID in edge table

    return done;
  }

  /**
   * Retrieves all Move objects from the "Move" database table and returns them as an ArrayList.
   * Each Move object is created using the data retrieved from the "nodeID," "longName," and "date" columns of the table.
   *
   * @return an ArrayList of Move objects containing all the data from the "Move" table
   * @throws SQLException if an error occurs while attempting to retrieve data from the database
   */
  public ArrayList<Move> getAllMoveObjects() {
    ArrayList<Move> list = new ArrayList<Move>();
    DatabaseConnection dbc = new DatabaseConnection();
    Connection connection = dbc.DbConnection();
    try {
      String query = "SELECT * FROM \"Move\"";
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        Move ln =
                new Move(
                        rs.getInt("nodeID"),
                        rs.getString("longName"),
                        rs.getDate("date"));
        list.add(ln);
      }
      connection.close();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return list;
  }
}
