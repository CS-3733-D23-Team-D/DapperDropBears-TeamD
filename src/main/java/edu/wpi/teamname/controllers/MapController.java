package edu.wpi.teamname.controllers;

import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.awt.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import net.kurobako.gesturefx.GesturePane;

public class MapController {

  @FXML MFXButton homeButton;
  @FXML MFXButton helpButton;
  @FXML MFXButton mapButton;
  @FXML MFXButton directionButton;
  @FXML MFXButton serviceRequestsButton;
  @FXML MFXButton exitButton;

  GraphicsContext gc;

  @FXML private Canvas canvas;

  @FXML private Line line;

  @FXML private GesturePane gp;

  private Line tempLine;

  private Node n;

  int xCord1, xCord2, yCord1, yCord2;

  float canvasX = -20;
  float canvasY = -20;

  public void testMethod() {

    System.out.println("Test");

    gc.setStroke(Color.BLUE);
    gc.setLineWidth(2);
    //    gc.fillRect(75, 75, 100, 100);

    gc.beginPath();
    gc.lineTo(xCord1, yCord1);
    gc.lineTo(xCord2, yCord2);
    gc.lineTo(xCord2 + 10, yCord2 + 10);
    gc.closePath();
    gc.stroke();

    //    Path path = new Path();
    //    path.getElements().add(new MoveTo(0.0f, 50.0f));
    //    path.getElements().add(new LineTo(100.0f, 100.0f));
  }

  EventHandler<MouseEvent> e =
      new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
          System.out.println("test2");

          double parentW = canvas.getParent().getParent().getLayoutBounds().getWidth();
          double parentH = canvas.getParent().getParent().getLayoutBounds().getHeight();
          //
          // System.out.println(); //
          // Size of bound box
          //
          // System.out.println(canvas.getParent().getParent().getLayoutBounds().getHeight());

          //          gp.centreOn(new Point2D(1002, 1003));

          double diffX = gp.getCurrentX() - canvasX;
          double diffY = gp.getCurrentY() - canvasY;

          int offset = 2;
          int testS = 17;

          // Scroll Bars are 16 px Thick
          int barT = 16;

          double hCenter = ((parentW - barT) / 2) - (canvas.getWidth() / 2);
          double vCenter = ((parentH - barT) / 2) - (canvas.getHeight() / 2);

//          canvasX += diffX;
//          canvas.setTranslateX(-canvasX + hCenter); // parentW);
//          canvasY += diffY;
//          canvas.setTranslateY(-canvasY + vCenter); // parentH);
//          gc.fillRect(0, 0, testS + offset, testS + offset);
//          gc.setFill(Color.RED);
//          gc.fillRect(0, 0, testS, testS);

          //          gp.setHbarPolicy(GesturePane.ScrollBarPolicy.NEVER);
          //          gp.setVbarPolicy(GesturePane.ScrollBarPolicy.NEVER);

          //          System.out.println("X: " +gp.getCurrentX()); // Coords off of top Left.
          // negative only
          //          System.out.println("Y: " + gp.getCurrentY());
          //          System.out.println(event.getX());
          //          System.out.println(event.getY());
          //          System.out.println(event.getSceneX());
          //          System.out.println(event.getSceneY());
          //          System.out.println(canvas.getWidth());
          //          System.out.println(canvas.getHeight());
          System.out.println("ETest2");
        }
      };

  @FXML
  public void initialize() {
    xCord1 = 75;
    yCord1 = 75;
    xCord2 = 100;
    yCord2 = 100;

    tempLine = new Line();
    tempLine.setStartX(xCord1);
    tempLine.setEndX(xCord2);
    tempLine.setStartY(yCord1);
    tempLine.setEndY(yCord2);

    //    canvas.setOnMouseClicked(event -> testMethod());

    canvas.setOnMouseClicked(e);

    gc = canvas.getGraphicsContext2D();

    Color c = Color.WHITE;

    gc.setFill(c);

    gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

    c = Color.BLUE;

    gc.setFill(c);

    canvas.setTranslateX(-canvasX);
    canvas.setTranslateY(-canvasY);

    homeButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    //    helpButton
    mapButton.setOnMouseClicked(event -> Navigation.navigate(Screen.MAP));
    directionButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SIGNAGE));
    serviceRequestsButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SERVICE_REQUEST));
    exitButton.setOnMouseClicked(event -> System.exit(0));
  }
}
