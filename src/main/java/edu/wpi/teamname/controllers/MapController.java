package edu.wpi.teamname.controllers;

import edu.wpi.teamname.database.DataManager;
import edu.wpi.teamname.database.DatabaseConnection;
import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Node;
import edu.wpi.teamname.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.sql.Connection;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
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
  //  @FXML Text destText;
  @FXML Label destText;

  private Node n1 = new Node("115", 2130, 904, "L1", "45 Francis", "3", "4", "5");
  private Node n2 = new Node("120", 2130, 844, "L1", "45 Francis", "3", "4", "5");
  private Node n3 = new Node("200", 2185, 904, "L1", "45 Francis", "3", "4", "5");

  private ArrayList<Node> floorNodes;

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

  DatabaseConnection dbc = new DatabaseConnection();
  Connection connection = dbc.DbConnection();
  DataManager dm = new DataManager();

  public void printScale() {
    //    System.out.println(gp.getCurrentX() + ", " + gp.getCurrentY());
    //    System.out.println(gp.getCurrentScale());
    //    double parentW = getMapWitdh();
    //    double parentH = getMapHeight();
    //    System.out.println(parentW + ", " + parentH);

    dm.displayNodesByFloor(connection, "L1");
  }

  private double getMapWitdh() {
    //    return sp.getLayoutBounds().getWidth();
    return gp.getCurrentScaleX();
  }

  private double getMapHeight() {
    //    return sp.getLayoutBounds().getHeight();
    return gp.getLayoutBounds().getHeight();
  }

  EventHandler<MouseEvent> e =
      new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

          Point2D clickPoint = new Point2D(event.getX(), event.getY());

          ArrayList<Point2D> floorPoints;

          floorPoints = dm.displayNodesByFloor(connection, "L1");

          System.out.println(clickPoint); // Coordinates in inner, now goes up to 5000

          int leastDistanceNodeIndex = -1;
          double leastDistance = Double.MAX_VALUE;
          double nodeDist;
          int startNodeIndex = 0;
          for (int i = 0; i < floorPoints.size(); i++) {
            if (i == startNodeIndex) {
              continue;
            } else {
              //              Node currentNode = floorNodes.get(i);
              Point2D currentNode = floorPoints.get(i);
              nodeDist = clickPoint.distance(currentNode);
              if (nodeDist < leastDistance) {
                leastDistance = nodeDist;
                leastDistanceNodeIndex = i;
              }
            }
          }

          Point2D startNode = floorPoints.get(startNodeIndex);
          Point2D endNode = floorPoints.get(leastDistanceNodeIndex);

          Circle c = new Circle(startNode.getX(), startNode.getY(), 20, Color.BLACK);
          anchor.getChildren().add(c);
          c = new Circle(endNode.getX(), endNode.getY(), 20, Color.RED);
          anchor.getChildren().add(c);

          //          ArrayList<Node> path = AStarPath(startNode,endNode);
          //          makePath(path);
        }
      };

  @FXML
  public void initialize() {
    gp.setMinScale(0.11);
    gp.setOnMouseClicked(event -> printScale());
    //    gp.setOnMouseClicked(e);

    floorNodes = new ArrayList<Node>();

    floorNodes.add(n3);
    floorNodes.add(n1);
    floorNodes.add(n2);

    ArrayList<Shape> shapes = makePath(floorNodes);

    anchor.getChildren().addAll(shapes);

    anchor.setOnMouseClicked(e);

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
}
