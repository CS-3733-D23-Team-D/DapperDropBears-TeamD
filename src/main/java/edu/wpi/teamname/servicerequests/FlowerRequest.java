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
  private ArrayList<Flower> flowers;

  public FlowerRequest(
      int requestID,
      String staffName,
      String patientName,
      String roomNumber,
      LocalDateTime deliverBy) {
    super(requestID, staffName, patientName, roomNumber, deliverBy);
    flowers = new ArrayList<Flower>();
  }

  /**
   * Adds the given flower into the request's list
   *
   * @param flower the flower to be added
   */
  public void addFlower(Flower flower) {
    flowers.add(flower);
  }

  @Override
  public void addItem(int id) throws SQLException {
    addFlower(new Flower(id));
  }

  @Override
  public void clearItems() {
    flowers.clear();
  }

  /**
   * * Removes an instance of a flower from the requests
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

  /**
   * * Queries and gets an array list of all the flower ids
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

  /**
   * * Queries and gets an array list of all the flower name
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

  @Override
  public ArrayList<String> getAllNames() throws SQLException {
    return getAllFlowerNames();
  }

  @Override
  public ArrayList<Integer> getAllIDs() throws SQLException {
    return getAllFlowerIDs();
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Order Details:");
    stringBuilder.append("\nRequest ID: ");
    stringBuilder.append(getRequestID());
    stringBuilder.append("\nStaff Name: ");
    stringBuilder.append(getStaffName());
    stringBuilder.append("\nPatient Name: ");
    stringBuilder.append(getPatientName());
    stringBuilder.append("\nRoom Number: ");
    stringBuilder.append(getRoomNumber());
    stringBuilder.append("\nRequest Created: ");
    stringBuilder.append(getRequestedAt().toString());
    stringBuilder.append("\nRequested Delivery: ");
    stringBuilder.append(getDeliverBy().toString());
    stringBuilder.append("\nFlowers Requsted: ");
    for (Flower flower : flowers) {
      stringBuilder.append("\n");
      stringBuilder.append(flower.getName().replace("_", " "));
    }
    return stringBuilder.toString();
  }

  /**
   * * Uploads this instance of a flower request and uploads its info into the ServiceRequest and
   * ItemsOrdered relatiosn
   *
   * @throws SQLException
   */
  @Override
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
      connection.close();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    PreparedStatement statement;
    for (int i = 0; i < flowers.size(); i++) {
      connection = dbc.DbConnection();
      int quantity = getQuantity(this.getRequestID(), flowers.get(i).getFlowerID());
      try {

        if (quantity == 1) {
          query =
              "INSERT INTO \"ItemsOrdered\" (\"requestID\", \"itemID\", \"quantity\") "
                  + "VALUES ('"
                  + this.getRequestID()
                  + "', "
                  + flowers.get(i).getFlowerID()
                  + ", 1)";
        } else {
          query =
              "UPDATE \"ItemsOrdered\" "
                  + "SET quantity = "
                  + quantity
                  + " WHERE \"itemID\" = "
                  + flowers.get(i).getFlowerID();
        }
        statement = connection.prepareStatement(query);
        statement.executeUpdate();
        // System.out.println("ID: " + flowers.get(i).getFlowerID() + " - " + quantity);
      } catch (SQLException e) {
        System.out.println(query);
        System.out.println(e.getMessage());
      }
      connection.close();
    }
  }
}
