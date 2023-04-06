package edu.wpi.teamname.controllers;

import edu.wpi.teamname.controllers.jfxitems.RequestMenuItem;
import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import edu.wpi.teamname.servicerequests.FlowerRequest;
import edu.wpi.teamname.servicerequests.MealRequest;
import edu.wpi.teamname.servicerequests.ServiceRequest;
import io.github.palexdev.materialfx.controls.*;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import lombok.Getter;
import lombok.Setter;

public class ServiceRequestController {

  // Side Bar
  @FXML MFXButton homeButton;
  @FXML MFXButton helpButton;
  @FXML MFXButton mapButton;
  @FXML MFXButton directionsButton;
  @FXML MFXButton makeRequestsButton;
  @FXML MFXButton showRequestsButton;
  @FXML MFXButton editMapButton;
  @FXML MFXButton exitButton;

  // requestInfo Error if not added anything to both meal and side

  /**
   * @FXML MFXButton backButton; @FXML MFXButton setDateButton; @FXML MFXButton
   * printDateButton; @FXML MFXButton printMealButton; @FXML MFXButton addFriesButton; @FXML
   * MFXButton addSandwitchButton; @FXML MFXButton addFlowersButton;
   */

  // bot2
  // Sam's Form GUI
  @FXML HBox rootPane;

  @FXML ImageView background;
  private int requestPage = 0; // used for keeping track of which page is active
  // Bottom Bar
  @FXML MFXButton nextButton;
  @FXML StackPane requestPane;
  @FXML MFXButton clearButton;
  @FXML MFXButton cancelButton;

  // Form pane
  @FXML AnchorPane formAnchor;
  @FXML AnchorPane formPane;
  // Form fields
  @FXML TextField staffName;
  @FXML TextField patientName;
  @FXML TextField roomNum;
  @FXML DatePicker dateBox;
  @FXML ComboBox timeBox;
  ObservableList<String> timeValues = FXCollections.observableArrayList();
  ObservableList<String> serviceType =
      FXCollections.observableArrayList("Meal Delivery", "Flower Delivery");
  @FXML ComboBox requestType;

  // menu item page
  @FXML AnchorPane menuPane;
  @FXML TextField searchBar;
  @FXML VBox itemBox;
  ObservableList<String> mealItems =
      FXCollections.observableArrayList(
          "Burger", "Pizza", "Cookies", "Spaghet", "Ice Cream Cone", "Banana", "Banana Split");
  ObservableList<String> flowerItems =
      FXCollections.observableArrayList(
          "Black Cosmos",
          "Gold Roses",
          "Orange Tulips",
          "Green Mums",
          "Orange Cosmos",
          "Purple Hyacinths",
          "Pink Hyacinths");

  @FXML AnchorPane summaryPane;
  @FXML Label summaryLabel;

  @Setter @Getter private ServiceRequest request;

  ArrayList<Integer> itemIDs;
  ArrayList<String> itemNames;

  /**
   * Controls the switching and progression through creating the service request
   *
   * @throws SQLException
   */
  private void nextPane() throws SQLException {

    System.out.println("NEXT");
    if (requestPage == 0) {
      String folder;
      String timeString = timeBox.getValue().toString();
      System.out.println(timeString);
      int hour = Integer.valueOf(timeString.split(":")[0]);
      int min = Integer.valueOf(timeString.split(":")[1]);
      System.out.println(hour);
      System.out.println(min);
      LocalDate date = dateBox.getValue();
      System.out.println(date);
      LocalTime time = LocalTime.of(hour, min);
      System.out.println(time);
      LocalDateTime reqDateTime = date.atTime(time);
      System.out.println(reqDateTime.toString());
      if (requestType.getValue() == "Meal Delivery") {
        setRequest(
            new MealRequest(
                Instant.now().get(ChronoField.MICRO_OF_SECOND),
                staffName.toString(),
                patientName.toString(),
                roomNum.toString(),
                reqDateTime));
        folder = "MealIcons";
      } else {
        setRequest(
            new FlowerRequest(
                Instant.now().get(ChronoField.MICRO_OF_SECOND),
                staffName.toString(),
                patientName.toString(),
                roomNum.toString(),
                reqDateTime));
        folder = "FlowerIcons";
      }
      System.out.println(request.getDeliverBy().toString());

      itemNames = request.getAllNames();
      itemIDs = request.getAllIDs();
      for (int a = 0; a < itemIDs.size(); a++) {
        if (a < 4) {
          itemBox
              .getChildren()
              .add(new RequestMenuItem(itemNames.get(a), itemIDs.get(a), folder, getRequest()));
        }
      }

      itemBox.setFillWidth(true);
      setVisibleScreen(1);
      nextButton.setText("Next");

      requestPage = 1;

      request.setStaffName(staffName.getCharacters().toString());
      request.setPatientName(patientName.getCharacters().toString());
      request.setRoomNumber(roomNum.getCharacters().toString());
      // request.setDeliverBy(dateBox.getValue().atStartOfDay());

    } else if (requestPage == 1) {
      setVisibleScreen(2);
      nextButton.setText("Submit");
      requestPage = 2;
      summaryLabel.setText(request.toString());

    } else if (requestPage == 2) {
      setVisibleScreen(0);
      requestPage = 0;
      nextButton.setText("Next");
      request.uploadRequestToDatabase();
      Navigation.navigate(Screen.HOME);

      System.out.println(request);
    }
  }

  /** Clears the service request and brings you back to the home page */
  private void cancelAction() {
    clearAction();
    Navigation.navigate(Screen.HOME);
  }

  /** Clears the service request form and currently created service request */
  private void clearAction() {
    patientName.clear();
    staffName.clear();
    roomNum.clear();
    requestType.cancelEdit();
    dateBox.cancelEdit();
    if (requestPage > 0) {
      setVisibleScreen(0);
      requestPage = 0;
      nextButton.setText("Next");
      request.clearItems();
    }
  }

  /**
   * Sets the visible page of the service request form
   *
   * @param n the page number for the page to display 0 is the form 1 is the menu 2 is the summary
   */
  private void setVisibleScreen(int n) {
    if (n == 1) {
      formPane.setVisible(false);
      formPane.setDisable(true);
      menuPane.setDisable(false);
      menuPane.setVisible(true);
      summaryPane.setVisible(false);
      summaryPane.setDisable(true);
    } else if (n == 2) {
      formPane.setVisible(false);
      formPane.setDisable(true);
      menuPane.setDisable(true);
      menuPane.setVisible(false);
      summaryPane.setVisible(true);
      summaryPane.setDisable(false);
    } else {
      formPane.setVisible(true);
      formPane.setDisable(false);
      menuPane.setDisable(true);
      menuPane.setVisible(false);
      summaryPane.setVisible(false);
      summaryPane.setDisable(true);
      timeBox.setDisable(false);
    }
  }

  public void initialize() {
    setVisibleScreen(0);

    homeButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    //    helpButton.setOnMouseClicked(event -> Navigation.navigate(Screen.));
    mapButton.setOnMouseClicked(event -> Navigation.navigate(Screen.MAP));
    directionsButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SIGNAGE));
    makeRequestsButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SERVICE_REQUEST));
    showRequestsButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SERVICE_REQUEST_VIEW));
    editMapButton.setOnMouseClicked(event -> Navigation.navigate(Screen.MAP_EDIT));
    exitButton.setOnMouseClicked(event -> System.exit(0));

    for (int h = 0; h < 24; h++) {

      timeValues.add(Integer.toString(h) + ":00");
      timeValues.add(Integer.toString(h) + ":15");
      timeValues.add(Integer.toString(h) + ":30");
      timeValues.add(Integer.toString(h) + ":45");
    }
    timeBox.setItems(timeValues);

    nextButton.setText("Next");
    nextButton.setOnMouseClicked(
        event -> {
          try {
            nextPane();
          } catch (SQLException e) {
            throw new RuntimeException(e);
          }
        });
    cancelButton.setOnMouseClicked(event -> cancelAction());
    clearButton.setOnMouseClicked(event -> clearAction());

    requestType.setItems(serviceType);

    request = new ServiceRequest();
    requestPage = 0;

    itemBox.setFillWidth(true);
    itemBox.setSpacing(25);
  }
}
