package edu.wpi.teamname.controllers;

import edu.wpi.teamname.controllers.jfxitems.RequestMenuItem;
import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import edu.wpi.teamname.servicerequests.ServiceRequest;
import io.github.palexdev.materialfx.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import lombok.Getter;

public class ServiceRequestController {
  // requestInfo Error if not added anything to both meal and side

  /**
   * @FXML MFXButton backButton; @FXML MFXButton setDateButton; @FXML MFXButton
   * printDateButton; @FXML MFXButton printMealButton; @FXML MFXButton addFriesButton; @FXML
   * MFXButton addSandwitchButton; @FXML MFXButton addFlowersButton;
   */

  // bot2
  // Sam's Form GUI
  @FXML HBox rootPane;

  @FXML ImageView background;
  private int requestPage = 0; // used for keeping track of which page is active
  // Bottom Bar
  @FXML MFXButton nextButton;
  @FXML StackPane requestPane;
  @FXML MFXButton clearButton;
  @FXML MFXButton cancelButton;

  // Side Bar
  @FXML MFXButton homeButton;
  @FXML MFXButton helpButton;
  @FXML MFXButton mapButton;
  @FXML MFXButton directionButton;
  @FXML MFXButton serviceRequestsButton;
  @FXML MFXButton exitButton;

  // Form pane
  @FXML AnchorPane formAnchor;
  @FXML AnchorPane formPane;
  // Form fields
  @FXML MFXTextField staffName;
  @FXML MFXTextField patientName;
  @FXML MFXTextField roomNum;
  @FXML DatePicker dateBox;
  ObservableList<String> serviceType =
      FXCollections.observableArrayList("Meal Delivery", "Flower Delivery");
  @FXML ComboBox requestType;

  // menu item page
  @FXML AnchorPane menuPane;
  @FXML MFXTextField searchBar;
  @FXML VBox itemBox;
  ObservableList<String> mealItems =
      FXCollections.observableArrayList(
          "Burger", "Pizza", "Cookies", "Spaghet", "Ice Cream Cone", "Banana", "Banana Split");
  ObservableList<String> flowerItems =
      FXCollections.observableArrayList(
          "Black Cosmos",
          "Gold Roses",
          "Orange Tulips",
          "Green Mums",
          "Orange Cosmos",
          "Purple Hyacinths",
          "Pink Hyacinths");

  @FXML MFXTextField notesBox;

  @Getter private ServiceRequest request;

  /*
  private void addSelectedItems() {
    for (int i = 0; i < itemChecklist.getCells().size(); i++) {
      System.out.println();
      MFXCheckListCell cell = itemChecklist.getCell(i);
      if (cell.isSelected()) {
        // request.addItem(itemChecklist.getItems().get(i).toString());
      }
    }
  }*/

  private void nextPane() {
    System.out.println("NEXT");
    if (requestPage == 0) {
      if (requestType.getValue() == "Meal Delivery") {
        for (String item : mealItems) {
          itemBox.getChildren().add(new RequestMenuItem(item, "FoodIcons"));
        }
      } else {
        for (String item : flowerItems) {
          itemBox.getChildren().add(new RequestMenuItem(item, "FlowerIcons"));
        }
      }
      itemBox.setFillWidth(true);
      formPane.setDisable(true);
      formPane.setVisible(false);
      menuPane.setDisable(false);
      menuPane.setVisible(true);
      nextButton.setText("Submit");
      requestPage = 1;

      request.setStaffName(staffName.getCharacters().toString());
      request.setPatientName(patientName.getCharacters().toString());
      request.setRoomNumber(roomNum.getCharacters().toString());
      request.setDeliverBy(dateBox.getValue().atStartOfDay());

    } else {
      formPane.setVisible(true);
      formPane.setDisable(false);
      menuPane.setDisable(true);
      menuPane.setVisible(false);
      requestPage = 0;
      Navigation.navigate(Screen.HOME);

      request.setNotes(notesBox.getCharacters().toString());

      // addSelectedItems();

      System.out.println(request);
    }
  }

  private void cancelAction() {
    clearAction();
    Navigation.navigate(Screen.HOME);
  }

  private void clearAction() {
    patientName.clear();
    staffName.clear();
    roomNum.clear();
    requestType.cancelEdit();
    dateBox.cancelEdit();
    if (requestPage == 1) {
      formPane.setVisible(true);
      formPane.setDisable(false);
      menuPane.setDisable(true);
      menuPane.setVisible(false);
      requestPage = 0;
      nextButton.setText("Next");
    }
  }

  public void initialize() {

    // set the width and height to be bound to the panes width and height
    //    background.fitWidthProperty().bind(rootPane.widthProperty());
    //    background.fitHeightProperty().bind(rootPane.heightProperty());
    // make an image and image view, using the path to the image
    //    Image image = new Image("edu/wpi/teamname/images/BaWHospital.jpg");
    //    background.setImage(image);
    // set the width and height to be bound to the panes width and height
    //    background.fitWidthProperty().bind(rootPane.widthProperty());
    //    background.fitHeightProperty().bind(rootPane.heightProperty());

    // Making sure the first page(formBox is visible and enabled)
    //    formPane.setVisible(true);
    //    formPane.setDisable(false);
    //    menuPane.setDisable(true);
    //    menuPane.setVisible(false);

    // Same As Home Controller
    /**
     * backButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
     * setDateButton.setOnMouseClicked(event -> setDate()); printDateButton.setOnMouseClicked(event
     * -> printDate()); printMealButton.setOnMouseClicked(event -> printMeal());
     * addFriesButton.setOnMouseClicked(event -> addFries());
     * addSandwitchButton.setOnMouseClicked(event -> addSandwitch());
     * addFlowersButton.setOnMouseClicked(event -> addFlowers());
     */
    homeButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    exitButton.setOnMouseClicked(event -> System.exit(0));
    nextButton.setOnMouseClicked(event -> nextPane());
    cancelButton.setOnMouseClicked(event -> cancelAction());
    clearButton.setOnMouseClicked(event -> clearAction());

    requestType.setItems(serviceType);

    request = new ServiceRequest();
    requestPage = 0;

    itemBox.setFillWidth(true);
    itemBox.setSpacing(25);
  }
}
