package edu.wpi.teamname.controllers.jfxitems;

import javafx.scene.AccessibleRole;
import javafx.scene.control.Button;

public class RequestMenuItemButton extends Button {
  RequestMenuItem parent;
  String name;

  RequestMenuItemButton(String name, RequestMenuItem parent) {
    super(name);
    this.parent = parent;
    this.name = name;
    initialize();
    this.setText("Add to Cart");
  }

  private void initialize() {
    getStyleClass().setAll("button");
    setAccessibleRole(AccessibleRole.BUTTON);
    setMnemonicParsing(true);
    setStyle("-fx-background-color: #d9d9d9;-fx-font-size: 20pt");
    setOnMouseClicked(event -> System.out.println(name + " " + parent.quantity.getText()));
  }
}
