package edu.wpi.teamname.controllers;

import edu.wpi.teamname.database.DatabaseConnection;
import edu.wpi.teamname.database.NodeMoveLocationName;
import edu.wpi.teamname.navigation.Edge;
import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
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
  @FXML TableView edgeTable;
  private Connection connection;

  public void buildNodeData() throws SQLException {
    connection = new DatabaseConnection().DbConnection();
    table.setEditable(true);

    TableColumn nodeID = new TableColumn("Node ID");
    nodeID.setCellValueFactory(new PropertyValueFactory<NodeMoveLocationName, String>("nodeID"));
    TableColumn xCoord = new TableColumn("x-coordinate");
    xCoord.setCellValueFactory(new PropertyValueFactory<NodeMoveLocationName, String>("xcoord"));
    TableColumn yCoord = new TableColumn("y-coordinate");
    yCoord.setCellValueFactory(new PropertyValueFactory<NodeMoveLocationName, String>("ycoord"));
    TableColumn floor = new TableColumn("Floor");
    floor.setCellValueFactory(new PropertyValueFactory<NodeMoveLocationName, String>("floor"));
    TableColumn building = new TableColumn("Building");
    building.setCellValueFactory(
        new PropertyValueFactory<NodeMoveLocationName, String>("building"));
    TableColumn longName = new TableColumn("Long Name");
    longName.setCellValueFactory(
        new PropertyValueFactory<NodeMoveLocationName, String>("longName"));
    TableColumn shortName = new TableColumn("Short Name");
    shortName.setCellValueFactory(
        new PropertyValueFactory<NodeMoveLocationName, String>("shortName"));
    TableColumn nodeType = new TableColumn("Node Type");
    nodeType.setCellValueFactory(
        new PropertyValueFactory<NodeMoveLocationName, String>("nodeType"));
    ObservableList<NodeMoveLocationName> nodes =
        FXCollections.observableArrayList(NodeMoveLocationName.getAllObjects());
    table.setItems(nodes);
    table
        .getColumns()
        .addAll(nodeID, xCoord, yCoord, floor, building, longName, shortName, nodeType);
  }

  public void buildEdgeData() throws SQLException {
    connection = new DatabaseConnection().DbConnection();
    edgeTable.setEditable(true);
    TableColumn startNodeID = new TableColumn("Start Edge");
    startNodeID.setCellValueFactory(new PropertyValueFactory<Edge, String>("startNodeID"));
    TableColumn endNodeID = new TableColumn("End Edge");
    endNodeID.setCellValueFactory(new PropertyValueFactory<Edge, String>("endNodeID"));
    ObservableList<Edge> edges = FXCollections.observableArrayList(Edge.getAllEdges());
    edgeTable.setItems(edges);
    edgeTable.getColumns().addAll(startNodeID, endNodeID);
  }

  @FXML
  public void initialize() throws SQLException {
    buildNodeData();
    buildEdgeData();
    backButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    homeButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    exitButton.setOnMouseClicked(event -> System.exit(0));
    connection.close();
  }
}

// @FXML
 // public void initialize() {
   // System.out.println("Test");
   //
    // importCSVButton.setOnMouseClicked(event ->)    //implement arturos "getAllNodes" for node
    // column
    // exportCSVButton
 // }
// }
