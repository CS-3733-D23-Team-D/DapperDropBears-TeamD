package edu.wpi.teamname.controllers;

import edu.wpi.teamname.controllers.jfxitems.RequestMenuItem;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class TestController {
  @FXML VBox vBox;

  ArrayList<String> testList = new ArrayList<>();
  @FXML ObservableList<RequestMenuItem> itemlist = FXCollections.observableArrayList();

  public void initialize() {
    //    FXMLLoader will now automatically call any suitably annotated no-arg initialize() (With.load())
    // vBox.setPrefWidth(3000);
    System.out.println("TestININT!");
    testList.add("pizza");
    testList.add("pie");

    for (String item : testList) {
      // vBox.getChildren().add(new RequestMenuItem(item, "FoodIcon"));
      // vBox.getChildren().add(new Button("E"));
      // vBox.getChildren().
    }
  }
}
