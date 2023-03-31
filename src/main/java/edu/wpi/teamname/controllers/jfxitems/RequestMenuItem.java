package edu.wpi.teamname.controllers.jfxitems;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class RequestMenuItem extends HBox {
  private String name;
  @FXML Button button;
  @FXML ImageView imageView;
  @FXML MFXTextField quantity;

  public RequestMenuItem(String name) {
    this.name = name;
    initialize();
    try {
      imageView = new ImageView("/FoodIcons/pizza.png");
    } catch (IllegalArgumentException i) {
      System.out.println("illegal image urls");
    }
    button = new RequestMenuItemButton(name, this);
    quantity = new MFXTextField("", "quantity");
    // getChildren().add(imageView);
    getChildren().add(quantity);
    getChildren().add(button);
  }

  private void initialize() {
    setLayoutX(100);
    setStyle("-fx-background-color: black");
  }
}
