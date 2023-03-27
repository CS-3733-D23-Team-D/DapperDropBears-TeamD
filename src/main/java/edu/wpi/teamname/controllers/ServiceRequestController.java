package edu.wpi.teamname.controllers;

import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import edu.wpi.teamname.requestItems.MealItem;
import edu.wpi.teamname.servicerequests.FlowerRequest;
import edu.wpi.teamname.servicerequests.MealRequest;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import lombok.Getter;

public class ServiceRequestController {

  // Made Temp Node class
  // Do we want print methods to return or Print Service Request /Added Getter/ToString?
  // Request info only prints the first item

  // Added Name setting constructor to Request item, and extended in subclasses

  // If we are going to store this, how will we add to the Meal and flower requests?
  // I just made separate variables for each class, do we want to fix this?

  // Added addSide and addmeal Method
  // Added Request Constructors
  // Renamed Food to meal item
  // All Private or protected

  // Do we want abstract Class or Interface
  // Folder Structure?
  // requestInfo Error if not added anything to both meal and side

  @FXML MFXButton backButton;
  @FXML MFXButton setDateButton;
  @FXML MFXButton printDateButton;
  @FXML MFXButton printMealButton;
  @FXML MFXButton addFriesButton;
  @FXML MFXButton addSandwitchButton;
  @FXML MFXButton addFlowersButton;

  @Getter private MealRequest mealRequest;
  @Getter private FlowerRequest flowerRequest;

  private void printMeal() {
    System.out.println("printMeal");
    System.out.println(this.getMealRequest());
  }

  private void setDate() {
    System.out.println("setDate");
    this.mealRequest.setTime();
  }

  private void printDate() {
    System.out.println("printDate");
    System.out.println(this.mealRequest.getDeliverBy());
  }

  private void addFries() {
    System.out.println("addFries");
    MealItem fries = new MealItem("Fries");
    this.mealRequest.addSide(fries);
  }

  private void addSandwitch() {
    System.out.println("addSandwitch");
    MealItem sandwitch = new MealItem("Sandwitch");
    this.mealRequest.addMain(sandwitch);
  }

  private void addFlowers() {
    System.out.println("addFlowers");
  }

  public void initialize() {
    // Same As Home Controlelr
    backButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    setDateButton.setOnMouseClicked(event -> setDate());
    printDateButton.setOnMouseClicked(event -> printDate());
    printMealButton.setOnMouseClicked(event -> printMeal());
    addFriesButton.setOnMouseClicked(event -> addFries());
    addSandwitchButton.setOnMouseClicked(event -> addSandwitch());
    addFlowersButton.setOnMouseClicked(event -> addFlowers());

    mealRequest = new MealRequest();
  }
}
