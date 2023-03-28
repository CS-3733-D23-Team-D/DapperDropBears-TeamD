package edu.wpi.teamname.controllers;

import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;

public class SignageController {

  @FXML MFXButton backButton;

  @FXML
  public void initialize() {
    System.out.println("Test");
    backButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
  }
}
