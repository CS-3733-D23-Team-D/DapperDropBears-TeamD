package edu.wpi.teamname.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;

public class LoginController {
  @FXML MFXButton exit;

  @FXML
  private void username() {}

  @FXML
  private void password() {}

  @FXML
  void updatePassword() {}

  @FXML
  public void initialize() {
    exit.setOnMouseClicked(event -> System.exit(0));
  }
}
