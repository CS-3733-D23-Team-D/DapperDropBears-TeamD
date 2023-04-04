package edu.wpi.teamname.controllers;

import edu.wpi.teamname.controllers.jfxitems.RequestMenuItem;
import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import edu.wpi.teamname.servicerequests.FlowerRequest;
import edu.wpi.teamname.servicerequests.MealRequest;
import edu.wpi.teamname.servicerequests.ServiceRequest;
import io.github.palexdev.materialfx.controls.*;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import lombok.Getter;
import lombok.Setter;

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
  @FXML TextField staffName;
  @FXML TextField patientName;
  @FXML TextField roomNum;
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

  @Setter @Getter private ServiceRequest request;

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

  private void nextPane() throws SQLException {

    System.out.println("NEXT");
    if (requestPage == 0) {
      int counter;
      if (requestType.getValue() == "Meal Delivery") {
        counter = 1100;
        setRequest(
            new MealRequest(
                Instant.now().get(ChronoField.MICRO_OF_SECOND),
                staffName.toString(),
                patientName.toString(),
                roomNum.toString(),
                dateBox.getValue().atTime(LocalTime.now())));
        for (String item : mealItems) {
          itemBox.getChildren().add(new RequestMenuItem(item, counter, "MealIcons", getRequest()));
          counter++;
        }
      } else {
        for (String item : flowerItems) {
          counter = 1000;
          setRequest(
              new FlowerRequest(
                  Instant.now().get(ChronoField.MICRO_OF_SECOND),
                  staffName.toString(),
                  patientName.toString(),
                  roomNum.toString(),
                  dateBox.getValue().atTime(12, 00)));
          itemBox
              .getChildren()
              .add(new RequestMenuItem(item, counter, "FlowerIcons", getRequest()));
          counter++;
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

    } else if (requestPage == 1) {
      formPane.setVisible(true);
      formPane.setDisable(false);
      menuPane.setDisable(true);
      menuPane.setVisible(false);
      requestPage = 2;
      request.uploadRequestToDatabase();
      Navigation.navigate(Screen.HOME);
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
      request.clearItems();
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
    nextButton.setOnMouseClicked(
        event -> {
          try {
            nextPane();
          } catch (SQLException e) {
            throw new RuntimeException(e);
          }
        });
    cancelButton.setOnMouseClicked(event -> cancelAction());
    clearButton.setOnMouseClicked(event -> clearAction());

    requestType.setItems(serviceType);

    request = new ServiceRequest();
    requestPage = 0;

    itemBox.setFillWidth(true);
    itemBox.setSpacing(25);

    dateBox.setStyle("-fx-font: Times New Roman; -fx-font-size: 20");
  }
}
