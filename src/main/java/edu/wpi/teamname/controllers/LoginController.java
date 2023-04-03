package edu.wpi.teamname.controllers;

import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.awt.*;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
  @FXML MFXButton exit;

  @FXML MFXButton loginButton;
  @FXML MFXButton forgotPassword;
  @FXML TextField loginText;
  @FXML PasswordField passwordText;

  private static void loginPressed(String username, String password) {
    //    Login user = new Login(username, password);
    HomeController.setLoggedIn(true);
    Navigation.navigate(Screen.HOME);
  }

  @FXML
  public void initialize() {
    exit.setOnMouseClicked(event -> System.exit(0));
    loginButton.disableProperty().bind(Bindings.isEmpty(loginText.textProperty()));
    loginButton.disableProperty().bind((Bindings.isEmpty(passwordText.textProperty())));
    loginButton.setOnMouseClicked(
        event -> loginPressed(loginText.getText(), passwordText.getText()));
  }
}
