package edu.wpi.teamname.navigation;

import edu.wpi.teamname.App;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

public class Navigation {

  public static void navigate(final Screen screen) {
    final String filename = screen.getFilename();

    try {
      // Idk why using var instead of URL like before, but beats me.
      final var resource = App.class.getResource(filename);
      final FXMLLoader loader = new FXMLLoader(resource);

      // getter
      // Put the corresponding screen XML file in the center of the border ui element.
      App.getRootPane().setCenter(loader.load());
    } catch (IOException | NullPointerException e) {
      e.printStackTrace();
    }
  }
}
