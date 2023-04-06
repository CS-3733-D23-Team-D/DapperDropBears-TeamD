package edu.wpi.teamname.controllers;

import edu.wpi.teamname.navigation.DatabaseConnection;
import edu.wpi.teamname.navigation.Node;
import edu.wpi.teamname.navigation.Pathfinding;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

  // table FXML features
  @FXML TableView table;
  private Connection connection;

  public void buildNodeData() throws SQLException {
    connection = new DatabaseConnection().DbConnection();
    table.setEditable(true);

    TableColumn nodeID = new TableColumn("Node ID");
    nodeID.setCellValueFactory(new PropertyValueFactory<Node, String>("id"));
    TableColumn edgeID = new TableColumn("Edge ID");
    edgeID.setCellValueFactory(new PropertyValueFactory<Pathfinding, String>("Edges"));
    TableColumn locationName = new TableColumn("Location Name");
    locationName.setCellValueFactory(new PropertyValueFactory<Node, String>("locationName"));
    TableColumn move = new TableColumn("Move");
    move.setCellValueFactory(new PropertyValueFactory<Node, String>("Move"));
    ObservableList<Node> nodes = FXCollections.observableArrayList(Node.getAllNodes());
    table.setItems(nodes);
    table.getColumns().addAll(nodeID, edgeID, locationName, move);
  }

  @FXML
  public void initialize() throws SQLException {
    buildNodeData();
  }
}

// @FXML
 // public void initialize() {
   // System.out.println("Test");
   // backButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    // importCSVButton.setOnMouseClicked(event ->)    //implement arturos "getAllNodes" for node
    // column
    // exportCSVButton
 // }
// }

  /*
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
