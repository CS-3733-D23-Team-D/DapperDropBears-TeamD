package edu.wpi.teamname.controllers;

import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import edu.wpi.teamname.servicerequests.ServiceRequest;
import io.github.palexdev.materialfx.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import lombok.Getter;

public class ServiceRequestController {
  // requestInfo Error if not added anything to both meal and side

  /**
   * @FXML MFXButton backButton; @FXML MFXButton setDateButton; @FXML MFXButton
   * printDateButton; @FXML MFXButton printMealButton; @FXML MFXButton addFriesButton; @FXML
   * MFXButton addSandwitchButton; @FXML MFXButton addFlowersButton;
   */

  // Sam's Form GUI
  private int requestPage = 0; // used for keeping track of which page is active
  // Bottom Bar
  @FXML MFXButton nextButton;
  @FXML StackPane requestPane;
  @FXML MFXButton clearButton;
  @FXML MFXButton cancelButton;

  // Side Bar
  @FXML MFXButton homeButton;
  @FXML MFXButton helpButton;
  @FXML MFXButton mapButton;
  @FXML MFXButton directionButton;
  @FXML MFXButton serviceRequestsButton;
  @FXML MFXButton exitButton;
  @FXML ImageView imageView;
  // Form pane
  @FXML AnchorPane formPane;
  // Form fields
  @FXML MFXTextField staffName;
  @FXML MFXTextField patientName;
  @FXML MFXTextField roomNum;
  @FXML MFXDatePicker dateBox;
  ObservableList<String> serviceType =
      FXCollections.observableArrayList("Meal Delivery", "Flower Delivery");
  @FXML ComboBox requestType;

  // menu item page
  @FXML BorderPane menuPane;
  @FXML MFXTextField searchBar;
  @FXML MFXCheckListView itemChecklist;
  ObservableList<String> mealItems =
      FXCollections.observableArrayList("Burger", "Pizza", "Cookie", "Pasta", "Cake", "Banana");
  ObservableList<String> flowerItems =
      FXCollections.observableArrayList("Daisy", "Rose", "Tulip", "Mum", "Cosmos", "Hyacinth");

  @FXML MFXTextField notesBox;

  @Getter private ServiceRequest request;

  private void printMeal() {
    System.out.println("printMeal");
    System.out.println(this.request);
  }

  private void setDate() {
    System.out.println("setDate");
    this.request.setTime();
  }

  private void printDate() {
    System.out.println("printDate");
    System.out.println(this.request.getDeliverBy());
  }

  private void addFries() {
    System.out.println("addFries");
    this.request.addItem("Fries");
  }

  private void addSandwitch() {
    System.out.println("addSandwitch");
    this.request.addItem("Sandwitch");
  }

  private void addFlowers() {
    System.out.println("addFlowers");
    this.request.addItem("Flowers");
  }

  private void nextPane() {
    if (requestPage == 0) {
      if (requestType.getValue() == "Meal Delivery") {
        itemChecklist.setItems(mealItems);
      } else {
        itemChecklist.setItems(flowerItems);
      }
      formPane.setDisable(true);
      formPane.setVisible(false);
      menuPane.setDisable(false);
      menuPane.setVisible(true);
      nextButton.setText("Submit");
      requestPage = 1;
    } else {
      formPane.setVisible(true);
      formPane.setDisable(false);
      menuPane.setDisable(true);
      menuPane.setVisible(false);
      requestPage = 0;
      Navigation.navigate(Screen.HOME);
    }
  }

  public void initialize() {
    imageView.fitWidthProperty().bind(requestPane.widthProperty());
    imageView.fitHeightProperty().bind(requestPane.heightProperty());
    // Making sure the first page(formBox is visible and enabled)
    formPane.setVisible(true);
    formPane.setDisable(false);
    menuPane.setDisable(true);
    menuPane.setVisible(false);

    // Same As Home Controller
    /**
     * backButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
     * setDateButton.setOnMouseClicked(event -> setDate()); printDateButton.setOnMouseClicked(event
     * -> printDate()); printMealButton.setOnMouseClicked(event -> printMeal());
     * addFriesButton.setOnMouseClicked(event -> addFries());
     * addSandwitchButton.setOnMouseClicked(event -> addSandwitch());
     * addFlowersButton.setOnMouseClicked(event -> addFlowers());
     */
    homeButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    nextButton.setOnMouseClicked(event -> nextPane());

    requestType.setItems(serviceType);

    request = new ServiceRequest();
    requestPage = 0;
  }
}
