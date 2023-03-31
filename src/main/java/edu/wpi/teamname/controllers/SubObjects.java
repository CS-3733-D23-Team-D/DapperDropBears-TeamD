package edu.wpi.teamname.controllers;

import javafx.scene.AccessibleRole;
import javafx.scene.control.Button;

public class SubObjects extends Button {
  public SubObjects(String name) {
    super(name);
    initialize();
  }

  private void initialize() {
    // getStyleClass().setAll(DEFAULT_STYLE_CLASS);
    setAccessibleRole(AccessibleRole.BUTTON);
    setMnemonicParsing(true);
    this.setOnMouseClicked(event -> System.out.println(this.getText()));
  }
}
