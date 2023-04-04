package edu.wpi.teamname.servicerequests;

import edu.wpi.teamname.database.DatabaseConnection;
import edu.wpi.teamname.database.Flower;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class FlowerRequest extends ServiceRequest {
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

  public void addFlower(Flower flower) {
    flowers.add(flower);
  }

  public void removeFlower(int id) {
    for (int i = 0; i < flowers.size(); i++) {
      if (flowers.get(i).getFlowerID() == id) {
        flowers.remove(i);
        return;
      }
    }
  }

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

  private String toDate(LocalDateTime date) {
    String year = String.valueOf(date.getYear());
    String month = String.valueOf(date.getMonth());
    String day = addLeadingZero(String.valueOf(date.getDayOfMonth()));
    String hour = addLeadingZero(String.valueOf(date.getHour()));
    String minute = addLeadingZero(String.valueOf(date.getMinute()));
    String second = addLeadingZero(String.valueOf(date.getSecond()));

    return "TO_TIMESTAMP('"
        + year
        + "-"
        + month
        + "-"
        + day
        + "-"
        + hour
        + "-"
        + minute
        + "-"
        + second
        + "', 'YYYY-MONTH-DD-HH24-MI-SS')";
  }

  private String addLeadingZero(String value) {
    if (value.length() == 1) {
      return "0" + value;
    } else {
      return value;
    }
  }
}
