package edu.wpi.teamname.controllers;

import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Node;
import edu.wpi.teamname.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;

import javax.swing.table.TableColumn;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MapEditController {

  // Side Bar
  @FXML
  MFXButton homeButton;
  @FXML
  MFXButton helpButton;
  @FXML
  MFXButton mapButton;
  @FXML
  MFXButton directionButton;
  @FXML
  MFXButton serviceRequestsButton;
  @FXML
  MFXButton exitButton;
  @FXML
  MFXButton backButton;
  @FXML
  MFXButton importCSVButton;
  @FXML
  MFXButton exportCSVButton;

  @FXML
  public void initialize() {
    System.out.println("Test");
    backButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    //importCSVButton.setOnMouseClicked(event ->)    //implement arturos "getAllNodes" for node column
    //exportCSVButton
  }
  table.setEditable(true);

  TableColumn nodeID = new TableColumn("Node ID");
    nodeID.setCellValueFactory(new PropertyValueFactory<Node, String>("nodeID"));
  TableColumn edgeID = new TableColumn("Edge ID");
    edgeID.setCellValueFactory(new PropertyValueFactory<Node, String>("edgeID"));
  TableColumn locationName = new TableColumn("Location Name");
    locationName.setCellValueFactory(new PropertyValueFactory<Node, String>("locationName"));
  TableColumn move = new TableColumn("Move");
    move.setCellValueFactory(new PropertyValueFactory<Node, String>("move"));
  ObservableList<Node> serviceRequests =
          FXCollections.observableArrayList(Node.getAllNodes());
    table.setItems(serviceRequests);
    table
            .getColumns()
            .addAll(nodeID, edgeID, staffName, patientName, requestedAt, deliverBy);
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
  }/*

