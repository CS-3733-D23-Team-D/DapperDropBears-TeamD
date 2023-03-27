package edu.wpi.teamname.controllers;

import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;

public class HomeController {
  // test push
  @FXML MFXButton serviceRequests;
  @FXML MFXButton helpButton;
  @FXML MFXButton directionButton;
  @FXML MFXButton exitButton;

  @FXML
  public void initialize() {
    serviceRequests.setOnMouseClicked(event -> Navigation.navigate(Screen.SERVICE_REQUEST));
    exitButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SIGNAGE));
  }
}
