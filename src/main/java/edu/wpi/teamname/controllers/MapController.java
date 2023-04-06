package edu.wpi.teamname.controllers;

import edu.wpi.teamname.entities.MapEntity;
import edu.wpi.teamname.navigation.*;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.*;
import net.kurobako.gesturefx.GesturePane;

public class MapController {

  @FXML MFXButton homeButton;
  @FXML MFXButton helpButton;
  @FXML MFXButton mapButton;
  @FXML MFXButton directionsButton;
  @FXML MFXButton makeRequestsButton;
  @FXML MFXButton showRequestsButton;
  @FXML MFXButton editMapButton;
  @FXML MFXButton exitButton;

  @FXML AnchorPane anchor;
  @FXML GesturePane gp;
  @FXML StackPane sp;
  @FXML Label destText;

  private Point2D centerPoint;
  private Point2D centerTL;

  //  DatabaseConnection dbc = new DatabaseConnection();
  //  Connection connection = dbc.DbConnection();
  //  DataManager dm = new DataManager();

  private double getMapWitdh() {
    //    return sp.getLayoutBounds().getWidth();
    return gp.getCurrentScaleX();
  }

  private double getMapHeight() {
    //    return sp.getLayoutBounds().getHeight();
    return gp.getLayoutBounds().getHeight();
  }

  MapEntity map;

  EventHandler<MouseEvent> e =
      new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
          map.drawAStarPath(anchor, new Point2D(event.getX(), event.getY()));
        }
      };

  public void centerAndZoom() {
    double parentW = getMapWitdh();
    double parentH = getMapHeight();
    parentW = 760;
    parentH = 512;
    System.out.println("PW,PH: " + getMapWitdh() + ", " + getMapHeight());
    //    Point2D CMin = new Point2D(parentW / 2, parentH / 2);

    Point2D scaleOneDim = new Point2D(760 * 2, 512 * 2); // hard Coded

    double scaleX = parentW / scaleOneDim.getX();
    double scaleY = parentH / scaleOneDim.getY();

    System.out.println(scaleX + ", " + scaleY);

    double scaleFactor = Double.min(scaleX, scaleY);

    gp.zoomTo(scaleFactor, Point2D.ZERO);
    double scale = gp.getCurrentScale();
    Point2D CMin = new Point2D((parentW / 2) * (1 / scale), (parentH / 2) * (1 / scale));

    Point2D topDisplay = new Point2D(2250, 685); // Hard Coded

    centerPoint = new Point2D(2250, 1000); // Hard Coded
    centerTL = centerPoint.subtract(CMin);

    //    destText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));

    System.out.println("X: " + destText.getLayoutX());

    destText.setLayoutX(
        topDisplay
            .getX()); // Center Point is in the wrong spot because we are adding half of the outer
    // coords to the Inner 3400 cords.
    destText.setLayoutY(topDisplay.getY());

    //    Circle c = new Circle(centerPoint.getX(), centerPoint.getY(), 20, Color.BLACK);
    //    anchor.getChildren().add(c);

    // Shift To Center

    System.out.println(CMin);
    //    centerPoint = centerPoint.subtract(CMin);
    //     760, 512 = 0.5 Scale
    //     1520, 1024 = 1 Scale

    //    gp.zoomTo(1, Point2D.ZERO);
    //    System.out.println("Z: " + gp.getCurrentScale());
    gp.centreOn(centerTL); // Actually Moves the Top left corner
  }

  @FXML
  public void initialize() {

    map = new MapEntity();

    gp.setMinScale(0.11);
    //    gp.setOnMouseClicked(testMethod());

    anchor.setOnMouseClicked(e);

    homeButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    //    helpButton.setOnMouseClicked(event -> Navigation.navigate(Screen.));
    mapButton.setOnMouseClicked(event -> Navigation.navigate(Screen.MAP));
    directionsButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SIGNAGE));
    makeRequestsButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SERVICE_REQUEST));
    showRequestsButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SERVICE_REQUEST_VIEW));
    editMapButton.setOnMouseClicked(event -> Navigation.navigate(Screen.MAP_EDIT));
    exitButton.setOnMouseClicked(event -> System.exit(0));

    centerAndZoom();
  }
}
