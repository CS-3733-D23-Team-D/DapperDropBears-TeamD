package edu.wpi.teamname.servicerequests;

import edu.wpi.teamname.database.DatabaseConnection;
import edu.wpi.teamname.database.Flower;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class FlowerRequest extends ServiceRequest implements IItem {
  ArrayList<Flower> flowers;

  public FlowerRequest(
      int requestID,
      String staffName,
      String patientName,
      String roomNumber,
      LocalDateTime deliverBy) {
    super(requestID, staffName, patientName, roomNumber, deliverBy);
    flowers = new ArrayList<Flower>();
  }

  /***
   * Adds the given flower into the request's list
   *
   * @param flower the flower to be added
   */
  public void addFlower(Flower flower) {
    flowers.add(flower);
  }

  public void addItem(int id) throws SQLException {
    addFlower(new Flower(id));
  }

  public void clearItems() {
    flowers.clear();
  }

  /***
   * Removes an instance of a flower from the requests
   *
   * @param id the id of the flower to remove
   */
  public void removeFlower(int id) {
    for (int i = 0; i < flowers.size(); i++) {
      if (flowers.get(i).getFlowerID() == id) {
        flowers.remove(i);
        return;
      }
    }
  }

  /***
   * Queries and gets an array list of all the flower ids
   *
   * @return an array list of all the flower ids
   * @throws SQLException
   */
  public ArrayList<Integer> getAllFlowerIDs() throws SQLException {
    DatabaseConnection dbc = new DatabaseConnection();
    Connection connection = dbc.DbConnection();
    ArrayList<Integer> output = new ArrayList<Integer>();
    try (connection) {
      String query = "SELECT \"flowerID\" FROM \"Flowers\" ORDER BY \"flowerID\"";
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        output.add(rs.getInt("flowerID"));
      }

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return output;
  }

  /***
   * Queries and gets an array list of all the flower name
   *
   * @return an array list of all the flower name
   * @throws SQLException
   */
  public ArrayList<String> getAllFlowerNames() throws SQLException {
    DatabaseConnection dbc = new DatabaseConnection();
    Connection connection = dbc.DbConnection();
    ArrayList<String> output = new ArrayList<String>();
    try (connection) {
      String query = "SELECT \"Name\" FROM \"Flowers\" ORDER BY \"flowerID\"";
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        output.add(rs.getString("Name"));
      }

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return output;
  }

  /***
   * Uploads this instance of a flower request and uploads its info
   * into the ServiceRequest and ItemsOrdered relatiosn
   * 
   * @throws SQLException
   */
  public void uploadRequestToDatabase() throws SQLException {
    DatabaseConnection dbc = new DatabaseConnection();
    Connection connection = dbc.DbConnection();

    String query = null;
    try {
      query =
          "INSERT INTO \"ServiceRequest\" "
              + "(\"requestID\", \"roomNum\", \"staffName\", \"patientName\", \"requestedAt\", \"deliverBy\") "
              + "VALUES ('"
              + this.getRequestID()
              + "', '"
              + this.getRoomNumber()
              + "', '"
              + this.getStaffName()
              + "', '"
              + this.getPatientName()
              + "', "
              + toDate(this.getRequestedAt())
              + ", "
              + toDate(this.getDeliverBy())
              + ")";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    PreparedStatement statement;
    for (int i = 0; i < flowers.size(); i++) {
      connection = dbc.DbConnection();
      try {
        query =
            "INSERT INTO \"ItemsOrdered\" (\"requestID\", \"itemID\") "
                + "VALUES ('"
                + this.getRequestID()
                + "', "
                + flowers.get(i).getFlowerID()
                + ")";
        statement = connection.prepareStatement(query);
        statement.executeUpdate();
      } catch (SQLException e) {
        System.out.println(query);
        System.out.println(e.getMessage());
      }
    }
  }




}
