package edu.wpi.teamname.controllers;

import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class HomeController {
  // test push

  @FXML ImageView imageView;
  @FXML MFXButton serviceRequests;
  @FXML MFXButton helpButton;
  @FXML MFXButton directionButton;
  @FXML MFXButton exitButton;
  @FXML private AnchorPane rootPane;

  @FXML
  public void initialize() {

    // set the width and height to be bound to the panes width and height
    imageView.fitWidthProperty().bind(rootPane.widthProperty());
    imageView.fitHeightProperty().bind(rootPane.heightProperty());
    // this allows for the image to stay at the same size of the rootPane, which is the parent pane
    // of the Home.fxml

    // Param is EventHandeler
    // Lambda Expression. parameter -> expression
    // Basically just runs the Navigation.navigate Function
    // "event" is a parameter, but there is no
    serviceRequests.setOnMouseClicked(event -> Navigation.navigate(Screen.SERVICE_REQUEST));
    directionButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SIGNAGE));
    exitButton.setOnMouseClicked(event -> System.exit(0));
  }
}
