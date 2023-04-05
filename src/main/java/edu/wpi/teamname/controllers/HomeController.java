package edu.wpi.teamname.controllers;

import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lombok.Setter;

public class HomeController {
  // test push
  @Setter private static boolean loggedIn = false;
  @FXML ImageView imageView;
  @FXML MFXButton serviceRequests;
  @FXML MFXButton helpButton;
  @FXML MFXButton directionButton;
  @FXML MFXButton exitButton;
  @FXML private AnchorPane rootPane;
  @FXML MFXButton serviceRequestView;
  @FXML MFXButton loginButton;
  @FXML MFXButton logoutButton;

  private void logout() {
    loggedIn = false;
    loginButton.setVisible(true);
    logoutButton.setVisible(false);
  }

  @FXML
  public void initialize() {

    // set the width and height to be bound to the panes width and height
    //    imageView.fitWidthProperty().bind(rootPane.widthProperty());
    //    imageView.fitHeightProperty().bind(rootPane.heightProperty());
    // this allows for the image to stay at the same size of the rootPane, which is the parent pane
    // of the Home.fxml

    // Param is EventHandeler
    // Lambda Expression. parameter -> expression
    // Basically just runs the Navigation.navigate Function
    // "event" is a parameter, but there is no
    if (loggedIn) {
      loginButton.setVisible(false);
      logoutButton.setVisible(true);
    } else {
      loginButton.setVisible(true);
      logoutButton.setVisible(false);
    }
    serviceRequestView.setOnMouseClicked(event -> Navigation.navigate(Screen.SERVICE_REQUEST_VIEW));
    serviceRequests.setOnMouseClicked(event -> Navigation.navigate(Screen.SERVICE_REQUEST));
    directionButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SIGNAGE));
    loginButton.setOnMouseClicked(event -> Navigation.navigate(Screen.LOGIN));
    logoutButton.setOnMouseClicked(event -> logout());
    exitButton.setOnMouseClicked(event -> System.exit(0));
  }
}
