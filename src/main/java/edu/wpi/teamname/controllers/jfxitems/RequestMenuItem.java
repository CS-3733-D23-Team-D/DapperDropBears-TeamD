package edu.wpi.teamname.controllers.jfxitems;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class RequestMenuItem extends HBox {
  private String name;
  @FXML Label label;
  @FXML Button button;
  @FXML ImageView imageView;
  @FXML MFXTextField quantity;

  public RequestMenuItem(String name, String folder) {
    this.name = name;
    initialize();
    try {
      imageView =
          new ImageView(
              "edu/wpi/teamname/images/" + folder + "/" + name.replace(" ", "_") + ".png");
      getChildren().add(imageView);
    } catch (IllegalArgumentException i) {
      System.out.println("illegal image urls");
    }
    label = new Label(name);
    label.setStyle("-fx-text-fill: #000000; -fx-font-size: 20pt");
    button = new RequestMenuItemButton(name.replace("_", " "), this);
    quantity = new MFXTextField("", "quantity");
    getChildren().add(label);
    getChildren().add(quantity);
    getChildren().add(button);
  }

  private void initialize() {
    // setLayoutX(1000);
    setPadding(new Insets(0, 30, 0, 30));
    setSpacing(100);
    setStyle("-fx-background-color: white");
  }
}
