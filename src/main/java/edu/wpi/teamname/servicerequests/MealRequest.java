package edu.wpi.teamname.servicerequests;

import edu.wpi.teamname.database.DatabaseConnection;
import edu.wpi.teamname.database.Meal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

public class MealRequest extends ServiceRequest {

  @Getter @Setter private ArrayList<Meal> meals;

  public MealRequest(
      int requestID,
      String staffName,
      String patientName,
      String roomNumber,
      LocalDateTime deliverBy,
      Status status) {
    super(requestID, staffName, patientName, roomNumber, deliverBy, status);
    meals = new ArrayList<Meal>();
  }

  public MealRequest(
      int requestID,
      String staffName,
      String patientName,
      String roomNumber,
      LocalDateTime deliverBy) {
    super(requestID, staffName, patientName, roomNumber, deliverBy, Status.BLANK);
    meals = new ArrayList<Meal>();
  }

  /**
   * * Adds the given meal into the request's list
   *
   * @param meal the meal to be added
   */
  public void addMeal(Meal meal) {
    meals.add(meal);
  }

  @Override
  public void addItem(int id) throws SQLException {
    addMeal(new Meal(id));
  }

  /**
   * * Removes an instance of a meal from the requests
   *
   * @param id the id of the meal to remove
   */
  public void removeMeal(int id) {
    for (int i = 0; i < meals.size(); i++) {
      if (meals.get(i).getMealID() == id) {
        meals.remove(i);
        return;
      }
    }
  }

  /**
   * Queries and gets an array list of all the flower ids
   *
   * @return an array list of all the flower ids
   * @throws SQLException
   */
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

  /**
   * * Queries and gets an array list of all the meal name
   *
   * @return an array list of all the meal name
   * @throws SQLException
   */
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

  @Override
  public ArrayList<String> getAllNames() throws SQLException {
    return getAllMealNames();
  }

  @Override
  public ArrayList<Integer> getAllIDs() throws SQLException {
    return getAllMealIDs();
  }

  @Override
  public void clearItems() {
    meals.clear();
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Order Details:");
    stringBuilder.append("\nRequest ID: ");
    stringBuilder.append(getRequestID());
    stringBuilder.append("\nPatient Name: ");
    stringBuilder.append(getPatientName());
    stringBuilder.append("\nRoom Number: ");
    stringBuilder.append(getRoomNumber());
    stringBuilder.append("\nRequest Created: ");
    stringBuilder.append(getRequestedAt().toString());
    stringBuilder.append("\nRequested Delivery: ");
    stringBuilder.append(getDeliverBy().toString());
    stringBuilder.append("\nMeals Requsted: ");
    for (Meal meal : meals) {
      stringBuilder.append("\n");
      stringBuilder.append(meal.getName().replace("_", " "));
    }
    return stringBuilder.toString();
  }

  /**
   * * Uploads this instance of a meal request and uploads its info into the ServiceRequest and
   * ItemsOrdered relatiosn
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
              + "(\"requestID\", \"roomNum\", \"staffName\", \"patientName\", \"requestedAt\", \"deliverBy\", \"status\") "
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
              + ", '"
              + this.getStatus().getStatusString().toUpperCase()
              + "')";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.executeUpdate();
      connection.close();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    PreparedStatement statement;
    int quantity;
    for (int i = 0; i < meals.size(); i++) {
      connection = dbc.DbConnection();
      quantity = getQuantity(this.getRequestID(), meals.get(i).getMealID());
      try {
        if (quantity == 1) {
          query =
              "INSERT INTO \"ItemsOrdered\" (\"requestID\", \"itemID\", \"quantity\") "
                  + "VALUES ('"
                  + this.getRequestID()
                  + "', "
                  + meals.get(i).getMealID()
                  + ", 1)";
        } else {
          query =
              "UPDATE \"ItemsOrdered\" "
                  + "SET quantity = "
                  + quantity
                  + " WHERE \"itemID\" = "
                  + meals.get(i).getMealID();
        }

        statement = connection.prepareStatement(query);
        statement.executeUpdate();

      } catch (SQLException e) {
        System.out.println(query);
        System.out.println(e.getMessage());
      }
    }
    connection.close();
  }
}
