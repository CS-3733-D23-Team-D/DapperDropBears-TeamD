package edu.wpi.teamname.controllers;

import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import edu.wpi.teamname.servicerequests.ServiceRequest;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import lombok.Getter;

public class ServiceRequestController {
  // requestInfo Error if not added anything to both meal and side

  @FXML MFXButton backButton;
  @FXML MFXButton setDateButton;
  @FXML MFXButton printDateButton;
  @FXML MFXButton printMealButton;
  @FXML MFXButton addFriesButton;
  @FXML MFXButton addSandwitchButton;
  @FXML MFXButton addFlowersButton;

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
    backButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    setDateButton.setOnMouseClicked(event -> setDate());
    printDateButton.setOnMouseClicked(event -> printDate());
    printMealButton.setOnMouseClicked(event -> printMeal());
    addFriesButton.setOnMouseClicked(event -> addFries());
    addSandwitchButton.setOnMouseClicked(event -> addSandwitch());
    addFlowersButton.setOnMouseClicked(event -> addFlowers());

    request = new ServiceRequest();
  }
}
