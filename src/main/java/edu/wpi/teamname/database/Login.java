package edu.wpi.teamname.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

public class Login {
  @Getter @Setter private static String username;
  @Getter @Setter private static String password;

  private static boolean admin;
  @Getter @Setter private static Connection connection;

  public Login(String username, String password) {
    if (username == "Admin" && password == "Admin") {
      admin = true;
    }
    this.username = username;
    this.password = password;
    DatabaseConnection dbc = new DatabaseConnection();
    this.connection = dbc.DbConnection();
  }

  public boolean checkLegalLogin(String u, String p){
    if(u.contains("\"") || u.contains(";")){
      return false;
    }
    else if(u.length()>=8 && capital(u) && number(u) && special(u)){
      return true;
    }
    else{
      return false;
    }
  }
  private boolean capital(String u){
    for(int i=0;i<u.length()-1;i++){
      char c = u.charAt(i);
      if(Character.isUpperCase(c)){
        return true;
      }
    }
    return false;
  }
  private boolean number(String u){
    for(int i=0;i<u.length()-1;i++){
      char c = u.charAt(i);
      if(Character.isDigit(c)){
        return true;
      }
    }
    return false;
  }
  private boolean special(String u){
    for(int i=0;i<u.length()-1;i++){
      char c = u.charAt(i);
      if(!Character.isDigit(c) && !Character.isLetter(c)){
        return true;
      }
    }
    return false;
  }

  public boolean LogInto() throws SQLException {
    boolean done = false;
    String query =
        "Select count(*) from \"Login\" l Where l.username = '"
            + username
            + "' AND l.password = '"
            + password
            + "'";

    try (Statement statement = connection.createStatement()) {
      ResultSet rs = statement.executeQuery(query);
      rs.next();
      int count = rs.getInt(1);
      if (count == 1) {
        if (admin) {
          System.out.println("Welcome Admin " + username + "!");
          // Connect to UI here to tell admin is true
        } else System.out.println("Welcome " + username + "!");
        done = true;
      } else if (count == 0) {
        System.out.println("Username or Password are incorrect.");
        // should clear the line and have to make new login object to update constructor
      }
    } catch (SQLException e) {
      System.out.println("Login Error.");
      throw e;
    }
    return done;
  }

  public static String resetPass() throws SQLException {
    boolean done = false;
    // if (admin){
    System.out.println("Username: " + username);
    //      System.out.println("Old Password: ");
    //      String oldPass = scan.nextLine();
    StringBuilder sb = new StringBuilder();
    Random rand = new Random();
    String newPass = "newPassword";
    //    for (int i = 0; i < 10; i++) {
    //      sb.append(newPass.charAt(rand.nextInt(newPass.length())));
    //    }
    password = "NewPassword"; // newPass.toString();
    String query =
        "Update \"Login\" Set password = '" + password + "' where username = '" + username + "'";

    try (Statement statement = connection.createStatement()) {
      statement.executeUpdate(query);
      done = true;
    } catch (SQLException e3) {
      System.out.println("Set New Password Error.");
      throw e3;
    }
    System.out.println("New password is now: " + password);
    return newPass;
  }

  //    else{
  //      //send a message to admin
  //      System.out.println("Ticket submitted to an Admin to reset your password");
  //    }
  // }

  public void setLogin() throws SQLException {
    Scanner scan = new Scanner(System.in);
    System.out.println("Set Username: ");
    String newUser = scan.nextLine();
    System.out.println("Set Password: ");
    String newPass = scan.nextLine();

    String query =
        "INSERT INTO \"Login\" (username, password) Values('" + newUser + "', '" + newPass + "')";

    try (Statement statement = connection.createStatement()) {
      statement.executeUpdate(query);
    } catch (SQLException e2) {
      System.out.println("Set Login Error.");
      throw e2;
    }
  }
}
