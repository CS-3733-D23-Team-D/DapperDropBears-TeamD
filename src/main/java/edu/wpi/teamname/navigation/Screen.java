package edu.wpi.teamname.navigation;

public enum Screen {
  // Enum Constants Calling the Enum Constructor
  ROOT("views/Root.fxml"),
  HOME("views/Home.fxml"),
  SERVICE_REQUEST("views/ServiceRequest2.fxml"),
  MAP("views/Map.fxml"),

  TEST("views/Test.fxml"),

  SIGNAGE("views/SignageLevels.fxml");

  private final String filename;

  Screen(String filename) {
    this.filename = filename;
  }

  public String getFilename() {
    return filename;
  }
}
