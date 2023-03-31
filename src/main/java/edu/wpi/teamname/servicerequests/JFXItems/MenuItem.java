package edu.wpi.teamname.servicerequests.JFXItems;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.*;
import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class MenuItem extends BorderPane {

  @FXML MFXButton cartButton;
  @FXML MFXTextField quantityField;
  @FXML Label itemLabel;
  @FXML ImageView itemImage;

  private StringProperty itemName = new SimpleStringProperty();
  private StringProperty imageURL = new SimpleStringProperty();
  private StringProperty quantity = new SimpleStringProperty();

  // borrowed from
  // https://stackoverflow.com/questions/51064426/add-custom-component-to-fxml-scene-builder-in-intellij
  public void MenuItem(String name) {
    try {
      FXMLLoader l = new FXMLLoader(getClass().getResource("MenuItem.fxml"));
      l.setController(this);
      l.setRoot(this);
      l.load();
      setItemLabel(name);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void initialize() {
    itemLabel.textProperty().bindBidirectional(itemName);
    quantityField.textProperty().bindBidirectional(quantity);
  }

  @FXML
  public void setItemImage(String url) {
    this.itemImage.setImage(new Image(url));
  }

  @FXML
  public void setItemLabel(String name) {
    this.itemName.set(name);
  }
}
