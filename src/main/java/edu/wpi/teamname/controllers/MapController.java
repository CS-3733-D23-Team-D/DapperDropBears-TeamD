package edu.wpi.teamname.controllers;

import edu.wpi.teamname.database.DataManager;
import edu.wpi.teamname.database.DatabaseConnection;
import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Node;
import edu.wpi.teamname.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.sql.Connection;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import net.kurobako.gesturefx.GesturePane;

public class MapController {

  @FXML MFXButton homeButton;
  @FXML MFXButton helpButton;
  @FXML MFXButton mapButton;
  @FXML MFXButton directionButton;
  @FXML MFXButton serviceRequestsButton;
  @FXML MFXButton exitButton;
  @FXML AnchorPane anchor;
  @FXML GesturePane gp;

  @FXML StackPane sp;
  @FXML Text destText;

  private Node n1 = new Node("115", 2130, 904, "L1", "45 Francis", "3", "4", "5");
  private Node n2 = new Node("120", 2130, 844, "L1", "45 Francis", "3", "4", "5");
  private Node n3 = new Node("200", 2185, 904, "L1", "45 Francis", "3", "4", "5");

  private Point2D centerPoint;
  private Point2D centerTL;

  private ArrayList<Shape> makePath(ArrayList<Node> nodes) {
    ArrayList<Shape> shapes = new ArrayList<Shape>();

    Circle c;
    float circleR = 10.0f;
    float lineT = 10.0f;

    int thickness = 2;

    Path path;

    Color borderColor = new Color(0.1, 0.4, 0.9, 1);
    Color insideColor = new Color(0.05, 0.7, 1, 1);

    for (int j = 0; j < 2; j++) {
      path = new Path();
      if (j == 0) {
        path.setStroke(borderColor);
      } else {
        path.setStroke(insideColor);
      }
      path.setStrokeWidth(lineT - (thickness * 2 * j));

      path.getElements().add(new MoveTo(nodes.get(0).getXCord(), nodes.get(0).getYCord()));
      for (int i = 1; i < nodes.size(); i++) {
        path.getElements().add(new LineTo(nodes.get(i).getXCord(), nodes.get(i).getYCord()));
      }
      path.setStrokeLineJoin(StrokeLineJoin.ROUND);
      shapes.add(path);
    }

    for (int i = 0; i < nodes.size(); i++) {

      if (i == 0 || i == nodes.size() - 1) {
        c = new Circle(nodes.get(i).getXCord(), nodes.get(i).getYCord(), circleR + thickness);
        c.setFill(borderColor);
        shapes.add(c);

        c = new Circle(nodes.get(i).getXCord(), nodes.get(i).getYCord(), circleR);
        c.setFill(insideColor);
        shapes.add(c);
      }
    }

    return shapes;
  }

  public void printScale() {
    //    System.out.println(gp.getCurrentX() + ", " + gp.getCurrentY());
    //    System.out.println(gp.getCurrentScale());
    //    double parentW = getMapWitdh();
    //    double parentH = getMapHeight();
    //    System.out.println(parentW + ", " + parentH);
    DatabaseConnection dbc = new DatabaseConnection();
    Connection connection = dbc.DbConnection();

    DataManager dm = new DataManager();

    dm.displayNodesByFloor(connection, "L1");
  }

  private double getMapWitdh() {
    //    return sp.getLayoutBounds().getWidth();
    return sp.getWidth();
  }

  private double getMapHeight() {
    //    return sp.getLayoutBounds().getHeight();
    return sp.getHeight();
  }

  @FXML
  public void initialize() {
    gp.setMinScale(0.11);
    gp.setOnMouseClicked(event -> printScale());

    ArrayList<Node> nodes = new ArrayList<Node>();

    nodes.add(n3);
    nodes.add(n1);
    nodes.add(n2);

    ArrayList<Shape> shapes = makePath(nodes);

    anchor.getChildren().addAll(shapes);

    homeButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    //    helpButton
    mapButton.setOnMouseClicked(event -> Navigation.navigate(Screen.MAP));
    directionButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SIGNAGE));
    serviceRequestsButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SERVICE_REQUEST));
    exitButton.setOnMouseClicked(event -> System.exit(0));

    double parentW = getMapWitdh();
    double parentH = getMapHeight();
    parentW = 760;
    parentH = 512;
    System.out.println("PW,PH: " + parentW + ", " + getMapHeight());
    //    Point2D CMin = new Point2D(parentW / 2, parentH / 2);
    Point2D CMin = new Point2D(500, 100);

    centerTL = new Point2D(1530, 530);
    centerPoint = centerTL.add(CMin);

    destText.setLayoutX(
        centerPoint
            .getX()); // Center Point is in the wrong spot because we are adding half of the outer
    // coords to the Inner 3400 cords.
    destText.setLayoutY(centerPoint.getY());

    Circle c = new Circle(centerPoint.getX(), centerPoint.getY(), 20, Color.BLACK);
    anchor.getChildren().add(c);

    // Shift To Center

    System.out.println(CMin);
    //    centerPoint = centerPoint.subtract(CMin);
    //     760, 512 = 0.5 Scale

    gp.zoomTo(0.5, Point2D.ZERO);
    gp.centreOn(centerTL); // Actually Moves the Top left corner
  }
}
