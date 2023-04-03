package edu.wpi.teamname.controllers.jfxitems;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class RequestMenuItem extends BorderPane {
  private String name;
  @FXML HBox hBox;
  @FXML Label label;
  @FXML Button button;
  @FXML ImageView imageView;
  @FXML MFXTextField quantity;

  public RequestMenuItem(String name, String folder) {
    this.name = name;

    try {
      imageView =
          new ImageView(
              "edu/wpi/teamname/images/" + folder + "/" + name.replace(" ", "_") + ".png");
      setLeft(imageView);
    } catch (IllegalArgumentException i) {
      System.out.println("illegal image urls");
    }
    hBox = new HBox();
    label = new Label(name);
    label.setFont(Font.font("Times New Roman", 20));
    button = new RequestMenuItemButton(name.replace("_", " "), this);
    quantity = new MFXTextField("", "Quantity");
    quantity.setFont(Font.font("Times New Roman", 20));
    hBox.getChildren().add(label);
    hBox.getChildren().add(quantity);
    setCenter(hBox);
    hBox.setAlignment(Pos.CENTER);
    label.setAlignment(Pos.CENTER);

    setRight(button);
    button.setAlignment(Pos.CENTER);
    button.setPadding(new Insets(this.getHeight() / 2 - button.getHeight() / 2, 0, 0, 0));
    initialize();
  }

  private void initialize() {
    // setLayoutX(1000);
    hBox.setPadding(new Insets(0, 30, 0, 30));
    hBox.setSpacing(100);
    setStyle("-fx-background-color: white");
  }
}
