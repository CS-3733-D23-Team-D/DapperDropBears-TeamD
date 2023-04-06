package edu.wpi.teamname.navigation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

public class Edge {
  @Getter @Setter int startNodeID;
  @Getter @Setter int endNodeID;

  // Constructor
  public Edge(int startNodeID, int endNodeID) {
    this.startNodeID = startNodeID;
    this.endNodeID = endNodeID;
  }

  /**
   * * Gets all of the edges in the database and puts them into an array list
   *
   * @return An array list of all the edges in the database
   * @throws SQLException
   */
  public static ArrayList<Edge> getAllEdges() throws SQLException {
    DatabaseConnection dbc = new DatabaseConnection();
    Connection connection = dbc.DbConnection();
    ArrayList<Edge> list = new ArrayList<Edge>();

    try (connection) {
      String query = "SELECT * FROM \"Edge\"";
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(query);

      while (rs.next()) {
        int startNode = rs.getInt("startNode");
        int endNode = rs.getInt("endNode");
        list.add(new Edge(startNode, endNode));
      }
    }
    return list;
  }

  // Returns all the attributes of a Node as a String
  public String toString() {
    return "StartNodeID: " + startNodeID + " EndNodeID: " + endNodeID;
  }
}
