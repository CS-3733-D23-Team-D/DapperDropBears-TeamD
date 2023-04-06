package edu.wpi.teamname.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

public class NodeMoveLocationName {
  @Getter @Setter private int nodeID;
  @Getter @Setter private int xcoord;
  @Getter @Setter private int ycoord;
  @Getter @Setter private String floor;
  @Getter @Setter private String building;
  @Getter @Setter private String longName;
  @Getter @Setter private String shortName;
  @Getter @Setter private String nodeType;
  @Getter @Setter private String date;

  public NodeMoveLocationName(
      int nodeID,
      int xcoord,
      int ycoord,
      String floor,
      String building,
      String longName,
      String shortName,
      String nodeType,
      String date) {
    this.nodeType = nodeType;
    this.nodeID = nodeID;
    this.xcoord = xcoord;
    this.ycoord = ycoord;
    this.floor = floor;
    this.building = building;
    this.longName = longName;
    this.shortName = shortName;
    this.date = date;
  }

  /**
   * Retrieves all NodeMoveLocationName objects from the "Move" database table and returns them as
   * an ArrayList. Each Move object is created using the data retrieved from the "nodeID,"
   * "longName," and "date" columns of the table.
   *
   * @return an ArrayList of Move objects containing all the data from the "Move" table
   * @throws SQLException if an error occurs while attempting to retrieve data from the database
   */
  public static ArrayList<NodeMoveLocationName> getAllObjects() {
    ArrayList<NodeMoveLocationName> list = new ArrayList<NodeMoveLocationName>();
    DatabaseConnection dbc = new DatabaseConnection();
    Connection connection = dbc.DbConnection();
    try {
      String query =
          "SELECT DISTINCT q.\"nodeID\", \"longName\", \"shortName\", \"nodeType\", xcoord, ycoord, building, floor, date\n"
              + "FROM (SELECT n.\"longName\", \"shortName\", n.\"nodeID\", \"nodeType\", xcoord, ycoord, building, floor, date\n"
              + "      FROM \"LocationName\",\n"
              + "           (select \"Move\".\"nodeID\", xcoord, ycoord, floor, building, \"longName\", date\n"
              + "            FROM \"Node\", \"Move\"\n"
              + "            where \"Node\".\"nodeID\" = \"Move\".\"nodeID\") n\n"
              + "      WHERE \"LocationName\".\"longName\" = n.\"longName\") w,\n"
              + "     (SELECT \"nodeID\" FROM (SELECT n.\"nodeID\"\n"
              + "                            FROM \"LocationName\",\n"
              + "                                 (select \"Move\".\"nodeID\", xcoord, ycoord, floor, building, \"longName\", date\n"
              + "                                  FROM \"Node\", \"Move\"\n"
              + "                                  where \"Node\".\"nodeID\" = \"Move\".\"nodeID\") n\n"
              + "                            WHERE \"LocationName\".\"longName\" = n.\"longName\"\n"
              + "                              ) j\n"
              + "      GROUP BY \"nodeID\") q\n"
              + "WHERE q.\"nodeID\" = w.\"nodeID\";";
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        NodeMoveLocationName ln =
            new NodeMoveLocationName(
                rs.getInt("nodeID"),
                rs.getInt("xcoord"),
                rs.getInt("ycoord"),
                rs.getString("floor"),
                rs.getString("building"),
                rs.getString("longName"),
                rs.getString("shortName"),
                rs.getString("nodeType"),
                rs.getString("date"));
        list.add(ln);
      }
      connection.close();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return list;
  }

  public String toString() {
    return nodeID + " " + longName + " " + date;
  }
}
