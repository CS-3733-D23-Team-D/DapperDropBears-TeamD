package edu.wpi.teamname.controllers.jfxitems;

import javafx.scene.AccessibleRole;
import javafx.scene.control.Button;

public class RequestMenuItemButton extends Button {
  RequestMenuItem parent;

  RequestMenuItemButton(String name, RequestMenuItem parent) {
    super(name);
    this.parent = parent;
    initialize();
  }

  private void initialize() {
    getStyleClass().setAll("button");
    setAccessibleRole(AccessibleRole.BUTTON);
    setMnemonicParsing(true);
    setOnMouseClicked(event -> System.out.println(getText() + " " + parent.quantity.getText()));
  }
}
