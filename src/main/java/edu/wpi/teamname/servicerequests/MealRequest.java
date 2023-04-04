package edu.wpi.teamname.servicerequests;

import edu.wpi.teamname.database.DatabaseConnection;
import edu.wpi.teamname.database.Meal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MealRequest extends ServiceRequest {

  ArrayList<Meal> meals;

  public MealRequest(
      int requestID,
      String staffName,
      String patientName,
      String roomNumber,
      LocalDateTime deliverBy) {
    super(requestID, staffName, patientName, roomNumber, deliverBy);
    meals = new ArrayList<Meal>();
  }

  public void addMeal(Meal meal) {
    meals.add(meal);
  }

  public void removeMeal(int id) {
    for (int i = 0; i < meals.size(); i++) {
      if (meals.get(i).getMealID() == id) {
        meals.remove(i);
        return;
      }
    }
  }

  public ArrayList<Integer> getAllMealIDs() throws SQLException {
    DatabaseConnection dbc = new DatabaseConnection();
    Connection connection = dbc.DbConnection();
    ArrayList<Integer> output = new ArrayList<Integer>();
    try (connection) {
      String query = "SELECT \"mealID\" FROM \"Meal\" ORDER BY \"mealID\"";
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        output.add(rs.getInt("mealID"));
      }

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return output;
  }

  public ArrayList<String> getAllMealNames() throws SQLException {
    DatabaseConnection dbc = new DatabaseConnection();
    Connection connection = dbc.DbConnection();
    ArrayList<String> output = new ArrayList<String>();
    try (connection) {
      String query = "SELECT \"Name\" FROM \"Meal\" ORDER BY \"mealID\"";
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
    for (int i = 0; i < meals.size(); i++) {
      connection = dbc.DbConnection();
      try {
        query =
            "INSERT INTO \"ItemsOrdered\" (\"requestID\", \"itemID\") "
                + "VALUES ('"
                + this.getRequestID()
                + "', "
                + meals.get(i).getMealID()
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
