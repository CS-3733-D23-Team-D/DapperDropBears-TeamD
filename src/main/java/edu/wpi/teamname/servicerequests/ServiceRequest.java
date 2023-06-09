package edu.wpi.teamname.servicerequests;

import edu.wpi.teamname.database.DatabaseConnection;
import edu.wpi.teamname.navigation.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

public class ServiceRequest {

  @Getter @Setter private int requestID;
  @Setter @Getter private String staffName;
  @Setter @Getter private String patientName;

  @Setter @Getter private String roomNumber;
  private Node deliveryLocation;

  @Setter private String notes;

  @Setter @Getter private LocalDateTime deliverBy;
  @Setter @Getter private LocalDateTime requestedAt;

  @Setter @Getter private Status status;

  public ServiceRequest(
      int requestID,
      String staffName,
      String patientName,
      String roomNumber,
      LocalDateTime deliverBy,
      Status status) {
    this.requestID = requestID;
    this.staffName = staffName;
    this.patientName = patientName;
    this.roomNumber = roomNumber;
    this.deliverBy = deliverBy;
    requestedAt = LocalDateTime.now();
    this.status = status;
  }

  public ServiceRequest(
      int requestID,
      String staffName,
      String patientName,
      String roomNumber,
      LocalDateTime deliverBy) {
    this.requestID = requestID;
    this.staffName = staffName;
    this.patientName = patientName;
    this.roomNumber = roomNumber;
    this.deliverBy = deliverBy;
    requestedAt = LocalDateTime.now();
    this.status = Status.BLANK;
  }

  public ServiceRequest(
      int requestID,
      String staffName,
      String patientName,
      String roomNumber,
      LocalDateTime deliverBy,
      LocalDateTime requestedAt,
      Status status) {
    this.requestID = requestID;
    this.staffName = staffName;
    this.patientName = patientName;
    this.roomNumber = roomNumber;
    this.deliverBy = deliverBy;
    this.requestedAt = requestedAt;
    this.status = status;
  }

  public ServiceRequest() {
    requestedAt = LocalDateTime.now();
  }

  /**
   * * Given an id and a staffname, updates that request's staff name into the new staff name
   *
   * @param requestID the id of the request to update
   * @param staffName the new staff name
   */
  public static void uploadStaffName(int requestID, String staffName) {
    DatabaseConnection dbc = new DatabaseConnection();
    Connection connection = dbc.DbConnection();
    try {
      String query =
          "UPDATE \"ServiceRequest\" SET "
              + "\"staffName\" = '"
              + staffName
              + "' WHERE \"requestID\" = "
              + requestID;
      PreparedStatement statement = connection.prepareStatement(query);
      statement.executeUpdate();
      connection.close();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public String toString() {
    return "ID: " + requestID;
    // return this.getRequestInfo();
  }

  /**
   * * Given a numerical string, add a leading zero if the string is only one digit long used for
   * parsing dates
   *
   * @param value the numerical String
   * @return the String with the leading zero or not
   */
  protected String addLeadingZero(String value) {
    if (value.length() == 1) {
      return "0" + value;
    } else {
      return value;
    }
  }
  /*public void addItem(String item) {
    RequestItem reqestItem = new RequestItem(item, 0.0);
    this.requestItems.add(reqestItem);
  }*/

  /*public String getRequestInfo() {
    String returnStr = "This Request has %s deliver (%s) to %s on %s. Notes %s";

    String requestStr = "";

    for (int i = 0; i < requestItems.size(); i++) {
      requestStr += requestItems.get(i);
      if (i != (requestItems.size() - 1)) {
        requestStr += " and ";
      }
    }
    return returnStr;
  }*/

  /**
   * * Converts a LocalDateTime object to a String with the TO_TIMESTAMP SQL command Essentially
   * converts LocalDateTime into SQL dates
   *
   * @param date the LocalDateTime object to be converted into a string
   * @return
   */
  protected String toDate(LocalDateTime date) {
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

  public int getQuantity(int requestID, int itemID) {
    DatabaseConnection dbc = new DatabaseConnection();
    Connection connection = dbc.DbConnection();

    int quantity = 0;
    try (connection) {
      String query =
          "SELECT \"quantity\" FROM \"ItemsOrdered\" WHERE \"itemID\" = "
              + itemID
              + " AND \"requestID\" = "
              + requestID;
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet rs = statement.executeQuery();

      while (rs.next()) {
        quantity = rs.getInt("quantity");
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    if (quantity > 0) {
      return quantity;
    } else {
      return 0;
    }
  }

  /**
   * Retrieves all service requests from the "ServiceRequest" table in the database and returns them
   * as an ArrayList of ServiceRequest objects.
   *
   * @return An ArrayList of ServiceRequest objects that represent all service requests in the
   *     "ServiceRequest" table.
   * @throws SQLException if a database access error occurs
   */
  public static ArrayList<ServiceRequest> getAllServiceRequests() {
    ArrayList<ServiceRequest> list = new ArrayList<ServiceRequest>();
    DatabaseConnection dbc = new DatabaseConnection();
    Connection connection = dbc.DbConnection();
    try {
      String query = "SELECT * FROM \"ServiceRequest\"";
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        String staffName = rs.getString("staffName");
        if (staffName == null) {
          staffName = "No staff inputted";
        }
        ServiceRequest sr =
            new ServiceRequest(
                rs.getInt("requestID"),
                staffName,
                rs.getString("patientName"),
                rs.getString("roomNum"),
                rs.getTimestamp("deliverBy").toLocalDateTime(),
                rs.getTimestamp("requestedAt").toLocalDateTime(),
                Status.valueOf(rs.getString("status")));
        list.add(sr);
      }
      connection.close();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return list;
  }

  public void addItem(int id) throws SQLException {};

  public void clearItems() {};

  public void uploadRequestToDatabase() throws SQLException {};

  public ArrayList<Integer> getAllIDs() throws SQLException {
    return new ArrayList<Integer>();
  };

  public ArrayList<String> getAllNames() throws SQLException {
    return new ArrayList<String>();
  };

  /*public void addItem(String item) {
    RequestItem reqestItem = new RequestItem(item, 0.0);
    this.requestItems.add(reqestItem);
  }*/
  /*public String getRequestInfo() {
      String returnStr = "This Request has %s deliver (%s) to %s on %s. Notes %s";

      String requestStr = "";

      for (int i = 0; i < requestItems.size(); i++) {
        requestStr += requestItems.get(i);
        if (i != (requestItems.size() - 1)) {
          requestStr += " and ";
        }
      }

      String dateStr = deliverBy.toString();

      returnStr = String.format(returnStr, staffName, requestStr, patientName, dateStr, notes);
      return returnStr;
    }
  */
  public void roomNumToLocationNode() {
    // Todo!!!
    // Convert the string property of this object into the corresponding node
  }
}
