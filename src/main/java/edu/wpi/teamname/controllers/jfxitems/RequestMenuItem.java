package edu.wpi.teamname.controllers.jfxitems;

import edu.wpi.teamname.servicerequests.ServiceRequest;
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
  private int id;
  ServiceRequest request;
  @FXML HBox hBox;
  @FXML VBox vBox;
  @FXML Label label;
  @FXML Button button;
  @FXML ImageView imageView;
  @FXML TextField quantity;

  public RequestMenuItem(String name, int id, String folder, ServiceRequest request) {
    this.name = name;
    this.id = id;
    this.request = request;
    try {
      imageView =
          new ImageView(
              "edu/wpi/teamname/images/" + folder + "/" + name.replace(" ", "_") + ".png");
      setLeft(imageView);
      System.out.println(
          "edu/wpi/teamname/images/" + folder + "/" + name.replace(" ", "_") + ".png");
    } catch (IllegalArgumentException i) {
      System.out.println("illegal image urls");
      System.out.println(
          "edu/wpi/teamname/images/" + folder + "/" + name.replace(" ", "_") + ".png");
    }
    hBox = new HBox();
    label = new Label(name.replace("_", " "));
    label.setFont(Font.font("Times New Roman", 32));
    button = new RequestMenuItemButton(name.replace("_", " "), this.id, this, this.request);
    quantity = new TextField("");
    quantity.setPromptText("Quantity");
    quantity.setFont(Font.font("Times New Roman", 32));
    hBox.getChildren().add(label);
    hBox.getChildren().add(quantity);
    setCenter(hBox);
    hBox.setAlignment(Pos.CENTER);
    label.setAlignment(Pos.CENTER);
    quantity.setAlignment(Pos.CENTER);
    vBox = new VBox();
    vBox.setAlignment(Pos.CENTER);
    vBox.setMinWidth(button.getWidth() + 15);
    vBox.getChildren().add(button);
    setRight(vBox);
    button.setAlignment(Pos.CENTER);
    button.setLayoutY(this.getHeight());
    button.setPadding(new Insets(this.getHeight() / 2 - button.getHeight() / 2, 25, 0, 25));
    initialize();
  }

  private void initialize() {
    // setLayoutX(1000);
    hBox.setPadding(new Insets(0, 30, 0, 30));
    hBox.setSpacing(100);
    setStyle("-fx-background-color: white");
  }

  public int getQuantity() {
    int val;
    try {
      val = Integer.parseInt(quantity.getText());
    } catch (NumberFormatException e) {
      val = 1;
    }
    return val;
  }
}
