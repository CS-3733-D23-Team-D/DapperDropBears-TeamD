package edu.wpi.teamname.controllers.jfxitems;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class RequestMenuItem extends BorderPane {
  private String name;
  @FXML HBox hBox;
  @FXML VBox vBox;
  @FXML Label label;
  @FXML Button button;
  @FXML ImageView imageView;
  @FXML TextField quantity;

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
    label.setFont(Font.font("Times New Roman", 32));
    button = new RequestMenuItemButton(name.replace("_", " "), this);
    quantity = new TextField("");
    quantity.setPromptText("Quantity");
    quantity.setFont(Font.font("Times New Roman", 32));
    hBox.getChildren().add(label);
    hBox.getChildren().add(quantity);
    setCenter(hBox);
    hBox.setAlignment(Pos.CENTER);
    label.setAlignment(Pos.CENTER);
    vBox = new VBox();
    vBox.setAlignment(Pos.CENTER);
    vBox.setMinWidth(button.getWidth() + 15);
    vBox.getChildren().add(button);
    setRight(vBox);
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
