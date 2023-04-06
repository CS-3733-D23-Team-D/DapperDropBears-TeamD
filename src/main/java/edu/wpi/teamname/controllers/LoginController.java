package edu.wpi.teamname.controllers;

import edu.wpi.teamname.database.DataManager;
import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.awt.*;
import java.sql.SQLException;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class LoginController {
  @FXML MFXButton exit;
  @FXML AnchorPane rootPane;
  @FXML StackPane paneOfStuff;
  String tempPassword;
  @FXML Label newPassword;
  @FXML Label success;
  @FXML MFXButton loginButton;
  @FXML MFXButton forgotPassword;
  @FXML TextField loginText;
  @FXML PasswordField passwordText;

  @FXML MFXButton homeButton;
  @FXML MFXButton helpButton;
  @FXML MFXButton mapButton;
  @FXML MFXButton directionButton;
  @FXML MFXButton serviceRequestsButton;
  @FXML MFXButton exitButton;

  /**
   * controls when a user presses the login button, which is only enabled when the username and
   * password have been filled in exits and then navigates to the home page, as a logged in user
   *
   * @param username the user's username
   * @param password the user's password
   * @return a boolean if the login was successful
   * @throws SQLException if there is a SQL error when getting the query
   */
  private static boolean loginPressed(String username, String password) throws SQLException {
    //    Login user = new Login(username, password);
    boolean successLog = DataManager.Login(username, password);
    if (successLog) {
      HomeController.setLoggedIn(true);
      Navigation.navigate(Screen.HOME);
      return true;
    } else {
      return false;
    }
  }

  @FXML
  public void initialize() {
    newPassword.setVisible(false);
    success.setText("Username or password\nis incorrect");
    success.setVisible(false);
    exit.setOnMouseClicked(event -> System.exit(0));
    forgotPassword.disableProperty().bind(Bindings.isEmpty(loginText.textProperty()));
    loginButton.disableProperty().bind(Bindings.isEmpty(loginText.textProperty()));
    loginButton.disableProperty().bind((Bindings.isEmpty(passwordText.textProperty())));
    /**
     * when the forgot password button is pressed, a new password is generated for the user, and it
     * updates the database with the new password for that user. The password is displayed for now,
     * but can be implemented as a email api. The text disappears when the pane behind it is
     * clicked.
     */
    forgotPassword.setOnMouseClicked(
        event -> {
          try {
            tempPassword = forgotPasswordPressed(loginText.getText());
            newPassword.setText("Your new password is: \n" + tempPassword);
            newPassword.setVisible(true);
            paneOfStuff.setDisable(true);
          } catch (SQLException e) {
            throw new RuntimeException(e);
          }
        });
    /** returns the view to the normal view, and doesn't display the extra texts anymore */
    rootPane.setOnMouseClicked(
        event -> {
          paneOfStuff.setDisable(false);
          newPassword.setVisible(false);
          success.setVisible(false);
        });
    /**
     * disabled until both the username and password fields have been filled in. Once pressed, goes
     * to the login method
     */
    loginButton.setOnMouseClicked(
        event -> {
          try {
            boolean temp = loginPressed(loginText.getText(), passwordText.getText());
            if (!temp) {
              paneOfStuff.setDisable(true);
              success.setVisible(true);
              passwordText.clear();
            }
          } catch (SQLException e) {
            throw new RuntimeException(e);
          }
        });
    mapButton.setOnMouseClicked(event -> Navigation.navigate(Screen.MAP));
    directionButton.setOnMouseClicked(event -> Navigation.navigate(Screen.SIGNAGE));
    homeButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    exitButton.setOnMouseClicked(event -> System.exit(0));
  }

  /**
   * Forgot password button, updates the password for the user that the inputted username that it
   * corresponds to
   *
   * @param username
   * @return
   * @throws SQLException
   */
  private String forgotPasswordPressed(String username) throws SQLException {
    return DataManager.forgotPassword(username);
  }
}
