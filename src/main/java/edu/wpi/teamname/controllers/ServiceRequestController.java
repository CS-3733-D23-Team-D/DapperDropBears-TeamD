package edu.wpi.teamname.controllers;

import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import edu.wpi.teamname.servicerequests.ServiceRequest;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import lombok.Getter;

public class ServiceRequestController {
  // requestInfo Error if not added anything to both meal and side

  /**
   * @FXML MFXButton backButton; @FXML MFXButton setDateButton; @FXML MFXButton
   * printDateButton; @FXML MFXButton printMealButton; @FXML MFXButton addFriesButton; @FXML
   * MFXButton addSandwitchButton; @FXML MFXButton addFlowersButton;
   */

  // Sam's Form GUI
  // Bottom Bar
  @FXML MFXButton nextButton;

  @FXML MFXButton clearButton;
  @FXML MFXButton cancelButton;

  // Side Bar
  @FXML MFXButton homeButton;
  @FXML MFXButton helpButton;
  @FXML MFXButton mapButton;
  @FXML MFXButton directionButton;
  @FXML MFXButton serviceRequestsButton;
  @FXML MFXButton exitButton;

  // Form fields
  @FXML MFXTextField staffName;
  @FXML MFXTextField patientName;
  @FXML MFXTextField roomNum;
  @FXML DatePicker dateBox;
  ObservableList<String> serviceType =
      FXCollections.observableArrayList("Meal Delivery", "Flower Delivery");
  @FXML ComboBox requestType;

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

  public void initialize() {
    // Same As Home Controller
    /**
     * backButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
     * setDateButton.setOnMouseClicked(event -> setDate()); printDateButton.setOnMouseClicked(event
     * -> printDate()); printMealButton.setOnMouseClicked(event -> printMeal());
     * addFriesButton.setOnMouseClicked(event -> addFries());
     * addSandwitchButton.setOnMouseClicked(event -> addSandwitch());
     * addFlowersButton.setOnMouseClicked(event -> addFlowers());
     */
    nextButton.setOnMouseClicked(event -> Navigation.navigate(Screen.REQ_MENU));

    requestType.setItems(serviceType);

    request = new ServiceRequest();
  }
}
