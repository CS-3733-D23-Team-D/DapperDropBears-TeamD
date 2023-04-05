package edu.wpi.teamname.controllers;

import edu.wpi.teamname.database.LocationName;
import edu.wpi.teamname.database.Move;
import edu.wpi.teamname.navigation.Edge;
import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Node;
import edu.wpi.teamname.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

public class MapEditController {

  // Side Bar
  @FXML MFXButton homeButton;
  @FXML MFXButton helpButton;
  @FXML MFXButton mapButton;
  @FXML MFXButton directionButton;
  @FXML MFXButton serviceRequestsButton;
  @FXML MFXButton exitButton;
  @FXML MFXButton backButton;
  @FXML MFXButton importCSVButton;
  @FXML MFXButton exportCSVButton;

    @FXML TableView table;
    @FXML TableColumn table1;
    @FXML TableColumn table2;
    @FXML TableColumn table3;
    @FXML TableColumn table4;


    public void buildData() throws SQLException {
      table.setEditable(true);

      TableColumn nodeID = new TableColumn("Node ID");
      nodeID.setCellValueFactory(new PropertyValueFactory<Node, String>("nodeID"));
      TableColumn edgeID = new TableColumn("Edge ID");
      edgeID.setCellValueFactory(new PropertyValueFactory<Node, String>("edgeID"));
      TableColumn locationName = new TableColumn("Location Name");
      move.setCellValueFactory(new PropertyValueFactory<Node, String>("locationName"));
      TableColumn move = new TableColumn("Move");
      move.setCellValueFactory(new PropertyValueFactory<Node, String>("move"));
      ObservableList<Node> nodes = FXCollections.observableArrayList(Node.getAllNodes());
      table.setItems(nodes);
      table.getColumns().addAll(nodeID, edgeID, locationName, move);
    }

    @FXML
    public void initialize() {
      buildData();
    }
  }

// @FXML
 // public void initialize() {
   // System.out.println("Test");
   // backButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    // importCSVButton.setOnMouseClicked(event ->)    //implement arturos "getAllNodes" for node
    // column
    // exportCSVButton
  }
}

  /*public static ArrayList<Node> getAllNodes() throws SQLException {
      DatabaseConnection dbc = new DatabaseConnection();
      Connection connection = dbc.DbConnection();
      ArrayList<Node> list = new ArrayList<Node>();

      try (connection) {
        String query = "SELECT * FROM \"Node\"";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()) {
          int id = rs.getInt("nodeID");
          int xcoord = rs.getInt("xcoord");
          int ycoord = rs.getInt("ycoord");
          String floor = rs.getString("floor");
          String building = rs.getString("building");
          list.add(new Node(id, xcoord, ycoord, floor, building));
        }
      }
      return list;
    }

  public void buildData() {
      table.setEditable(true);

      TableColumn requestID = new TableColumn("Request ID");
      requestID.setCellValueFactory(new PropertyValueFactory<ServiceRequest, String>("requestID"));
      TableColumn roomNumber = new TableColumn("Room Number");
      roomNumber.setCellValueFactory(new PropertyValueFactory<ServiceRequest, String>("roomNumber"));
      TableColumn staffName = new TableColumn("Staff Name");
      staffName.setCellValueFactory(new PropertyValueFactory<ServiceRequest, String>("staffName"));
      TableColumn patientName = new TableColumn("Patient Name");
      patientName.setCellValueFactory(
          new PropertyValueFactory<ServiceRequest, String>("patientName"));
      TableColumn requestedAt = new TableColumn("Request Date");
      requestedAt.setCellValueFactory(
          new PropertyValueFactory<ServiceRequest, String>("requestedAt"));
      TableColumn deliverBy = new TableColumn("Deliver Date");
      deliverBy.setCellValueFactory(new PropertyValueFactory<ServiceRequest, String>("deliverBy"));
      ObservableList<ServiceRequest> serviceRequests =
          FXCollections.observableArrayList(ServiceRequest.getAllServiceRequests());
      table.setItems(serviceRequests);
      table
          .getColumns()
          .addAll(requestID, roomNumber, staffName, patientName, requestedAt, deliverBy);
    }

     */
