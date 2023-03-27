package edu.wpi.teamname;

import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App extends Application {

  @Setter @Getter private static Stage primaryStage;
  @Setter @Getter private static BorderPane rootPane;

  @Override
  public void init() {
    // This runs first, right after Launch
    log.info("Starting Up");
  }

  @Override
  public void start(Stage primaryStage) throws IOException {

    System.out.println("Start!");
    // Right After init, This is called

    /* primaryStage is generally only used if one of your components require the stage to display */
    // Stage is like a window (To my knolwedge)
    App.primaryStage = primaryStage;
    // Loads and stores the outermost XML Object. Data Types Must match.
    final FXMLLoader loader = new FXMLLoader(App.class.getResource("views/Root.fxml"));

    // Border Pane Is the type of XML Object that is outermost (or root)
    final BorderPane root = loader.load();

    App.rootPane = root;

    final Scene scene = new Scene(root);

    // Specify the scene to be used on this stage,
    // AKA Specify what to be painted on the window
    primaryStage.setScene(scene);

    // SHOW the windwow
    primaryStage.show();

    // Homemade Navigation Class with Homemade Enum Screen
    Navigation.navigate(Screen.HOME);
  }

  @Override
  public void stop() {
    // Happens when the application is closed
    log.info("Shutting Down");
  }
}
