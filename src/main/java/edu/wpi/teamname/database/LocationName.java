package edu.wpi.teamname.database;

import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LocationName {
  @Getter @Setter private String longName;
  @Getter @Setter private String shortName;
  @Getter @Setter private String nodeType;

  public LocationName(String longName, String shortName, String nodeType) {
    this.longName = longName;
    this.shortName = shortName;
    this.nodeType = nodeType;
  }

  /**
   * Retrieves all LocationName objects from the "LocationName" database table and returns them as an ArrayList.
   * Each LocationName object is created using the data retrieved from the "longName," "shortName," and "nodeType" columns of the table.
   *
   * @return an ArrayList of LocationName objects containing all the data from the "LocationName" table
   * @throws SQLException if an error occurs while attempting to retrieve data from the database
   */
  public ArrayList<LocationName> getAllLocationNames() {
    ArrayList<LocationName> list = new ArrayList<LocationName>();
    DatabaseConnection dbc = new DatabaseConnection();
    Connection connection = dbc.DbConnection();
    try {
      String query = "SELECT * FROM \"LocationName\"";
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        LocationName ln =
                new LocationName(
                        rs.getString("longName"),
                        rs.getString("shortName"),
                        rs.getString("nodeType"));
        list.add(ln);
      }
      connection.close();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return list;
  }
}
