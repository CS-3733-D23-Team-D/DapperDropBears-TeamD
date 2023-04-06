package edu.wpi.teamname.controllers;

import edu.wpi.teamname.database.ItemsOrdered;
import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import edu.wpi.teamname.servicerequests.ServiceRequest;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ServiceRequestViewController {

  @FXML MFXButton homeButton;
  @FXML MFXButton helpButton;
  @FXML MFXButton mapButton;
  @FXML MFXButton directionsButton;
  @FXML MFXButton makeRequestsButton;
  @FXML MFXButton showRequestsButton;
  @FXML MFXButton editMapButton;
  @FXML MFXButton exitButton;

  @FXML TableView table;
  @FXML TableView secondTable;

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
    TableColumn requestStatus = new TableColumn("Request Status");
    requestStatus.setCellValueFactory(new PropertyValueFactory<ServiceRequest, String>("status"));
    ObservableList<ServiceRequest> serviceRequests =
        FXCollections.observableArrayList(ServiceRequest.getAllServiceRequests());
    table.setItems(serviceRequests);
    table
        .getColumns()
        .addAll(
            requestID, roomNumber, staffName, patientName, requestedAt, deliverBy, requestStatus);

    secondTable.setEditable(true);

    TableColumn requestID2 = new TableColumn("Request ID");
    requestID2.setCellValueFactory(new PropertyValueFactory<ItemsOrdered, String>("requestID"));
    TableColumn itemID = new TableColumn("Item ID");
    itemID.setCellValueFactory(new PropertyValueFactory<ItemsOrdered, String>("itemID"));
    TableColumn quantity = new TableColumn("Quantity");
    quantity.setCellValueFactory(new PropertyValueFactory<ItemsOrdered, String>("quantity"));
    ObservableList<ItemsOrdered> ItemsOrderedList =
        FXCollections.observableArrayList(ItemsOrdered.getAllItemsOrdered());
    secondTable.setItems(ItemsOrderedList);
    secondTable.getColumns().addAll(requestID2, itemID, quantity);
  }

  @FXML
  public void initialize() {

    homeButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    //    helpButton.setOnMouseClicked(event -> Navigation.navigate(Screen.));
    mapButton.setOnMouseClicked(event -> Navigation.navigate(Screen.MAP));
    directionsButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SIGNAGE));
    makeRequestsButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SERVICE_REQUEST));
    showRequestsButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SERVICE_REQUEST_VIEW));
    editMapButton.setOnMouseClicked(event -> Navigation.navigate(Screen.MAP_EDIT));
    exitButton.setOnMouseClicked(event -> System.exit(0));

    buildData();
  }
}
