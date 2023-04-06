package edu.wpi.teamname.entities;

import edu.wpi.teamname.navigation.AStar;
import edu.wpi.teamname.navigation.Graph;
import edu.wpi.teamname.navigation.Node;
import edu.wpi.teamname.navigation.Pathfinding;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class MapEntity {

  private ArrayList<Shape> makeShapePath(ArrayList<Node> nodes) {
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

  // anchor, event.getX()
  public void drawAStarPath(Pane parent, Point2D clickPos) {

    Point2D clickPoint = clickPos;

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

    ArrayList<Shape> shapes = makeShapePath(a.getPath(p.getGraph().getTarget()));

    a.printPath(p.getGraph().getTarget());

    parent.getChildren().addAll(shapes);

    //        dm.displayNodesByFloor(connection, "L1");

    //          ArrayList<Node> path = AStarPath(startNode,endNode);
    //          makePath(path);
  }
}
