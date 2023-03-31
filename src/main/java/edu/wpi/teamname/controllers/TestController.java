package edu.wpi.teamname.controllers;

import edu.wpi.teamname.servicerequests.JFXItems.MenuItem;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class TestController {
  @FXML VBox vBox;

  ArrayList<String> testList = new ArrayList<>();
  @FXML ObservableList<MenuItem> itemlist = FXCollections.observableArrayList();

  public void initialize() {
    //    FXMLLoader will now automatically call any suitably annotated no-arg initialize() (With
    // .load())
    System.out.println("TestININT!");
    testList.add("1");
    testList.add("2");

    for (String item : testList) {
      vBox.getChildren().add(new SubObjects(item));
      // vBox.getChildren().
    }
  }
}
