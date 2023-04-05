package edu.wpi.teamname.controllers;

import edu.wpi.teamname.database.DataManager;
import edu.wpi.teamname.database.DatabaseConnection;
import edu.wpi.teamname.navigation.*;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
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

  //  private Node n1 = new Node("115", 2130, 904, "L1", "45 Francis", "3", "4", "5");
  //  private Node n2 = new Node("120", 2130, 844, "L1", "45 Francis", "3", "4", "5");
  //  private Node n3 = new Node("200", 2185, 904, "L1", "45 Francis", "3", "4", "5");

  //  private ArrayList<Node> floorNodes;

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

      path.getElements().add(new MoveTo(nodes.get(0).getX(), nodes.get(0).getY()));
      for (int i = 1; i < nodes.size(); i++) {
        path.getElements().add(new LineTo(nodes.get(i).getX(), nodes.get(i).getY()));
      }
      path.setStrokeLineJoin(StrokeLineJoin.ROUND);
      shapes.add(path);
    }

    for (int i = 0; i < nodes.size(); i++) {

      if (i == 0 || i == nodes.size() - 1) {
        c = new Circle(nodes.get(i).getX(), nodes.get(i).getY(), circleR + thickness);
        c.setFill(borderColor);
        shapes.add(c);

        c = new Circle(nodes.get(i).getX(), nodes.get(i).getY(), circleR);
        c.setFill(insideColor);
        shapes.add(c);
      }
    }

    return shapes;
  }

  DatabaseConnection dbc = new DatabaseConnection();
  Connection connection = dbc.DbConnection();
  DataManager dm = new DataManager();

  public void printScale() throws Exception {
    //    System.out.println(gp.getCurrentX() + ", " + gp.getCurrentY());
    //    System.out.println(gp.getCurrentScale());
    //    double parentW = getMapWitdh();
    //    double parentH = getMapHeight();
    //    System.out.println(parentW + ", " + parentH);

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

          String floor = "L1";

          //          ArrayList<Point2D> floorPoints;
          //          floorPoints = dm.displayNodesByFloor(connection, floor);

          Pathfinding p = null;
          try {
            p = new Pathfinding();
          } catch (Exception ex) {
            throw new RuntimeException(ex);
          }
          Graph g = p.getGraph();
          AStar a = p.getAStar();

          List<Node> allNodes = g.getNodes();

          System.out.println(clickPoint); // Coordinates in inner, now goes up to 5000

          int leastDistanceNodeIndex = -1;
          double leastDistance = Double.MAX_VALUE;
          double nodeDist;
          int startNodeIndex = 4; // ID: 115

          for (int i = 0; i < allNodes.size(); i++) {
            if (i == startNodeIndex) {
              continue;
            } else {
              //              Node currentNode = floorNodes.get(i);
              Node currentNode = allNodes.get(i);

              //              System.out.println(currentNode.floor + ", " + floor);

              if (currentNode.floor.equals(floor)) {
                System.out.println("F!");

                nodeDist = clickPoint.distance(currentNode.getX(), currentNode.getY());
                if (nodeDist < leastDistance) {
                  System.out.println("HERE!");
                  leastDistance = nodeDist;
                  leastDistanceNodeIndex = i;
                }
              }
            }
          }

          Node startNode = allNodes.get(startNodeIndex);
          Node endNode = allNodes.get(leastDistanceNodeIndex);

          g.setStart(startNode);
          g.setTarget(endNode);

          //          Circle c = new Circle(startNode.getX(), startNode.getY(), 20, Color.BLACK);
          //          anchor.getChildren().add(c);
          //          c = new Circle(endNode.getX(), endNode.getY(), 20, Color.RED);
          //          anchor.getChildren().add(c);

          a.aStar(g);

          ArrayList<Shape> shapes = makePath(a.getPath(p.getGraph().getTarget()));

          a.printPath(p.getGraph().getTarget());

          anchor.getChildren().addAll(shapes);

          dm.displayNodesByFloor(connection, "L1");

          //          ArrayList<Node> path = AStarPath(startNode,endNode);
          //          makePath(path);
        }
      };

  @FXML
  public void initialize() {
    gp.setMinScale(0.11);
    //    gp.setOnMouseClicked(event -> printScale());
    //    gp.setOnMouseClicked(e);

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
