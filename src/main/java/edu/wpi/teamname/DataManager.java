package edu.wpi.teamname;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DataManager {

  /** Prototype 1: method should read file from CSV and import data to teamd database */
  public static List<String[]> importCSV(String fileName) {
    System.out.println("Parsing CSV file: " + fileName);
    List<String[]> rows = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] row = line.split(",", -1);
        rows.add(row);
      }
      System.out.println("CSV file parsed successfully");
      return rows;
    } catch (IOException e) {
      System.err.println("Error parsing CSV file: " + e.getMessage());
      return null;
    }
  }

  public static void uploadToPostgreSQL(List<String[]> csvData, Connection connection)
      throws SQLException {

    try (connection) {
      String query =
          "INSERT INTO \"L1Nodes\" (\"nodeID\", xcoord, ycoord, floor, building, \"nodeType\", \"longName\",\"shortName\") "
              + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
      PreparedStatement statement = connection.prepareStatement(query);

      for (int i = 1; i < csvData.size(); i++) {
        String[] row = csvData.get(i);
        statement.setString(1, row[0]); // nodeID is a string column
        statement.setInt(2, Integer.parseInt(row[1])); // xcoord is an integer column
        statement.setInt(3, Integer.parseInt(row[2])); // ycoord is an integer column
        statement.setString(4, row[3]); // assuming floor is a string column
        statement.setString(5, row[4]); // assuming building is a string column
        statement.setString(6, row[5]); // assuming nodeType is a string column
        statement.setString(7, row[6]); // assuming longName is a string column
        statement.setString(8, row[7]); // assuming shortName is a string column

        statement.executeUpdate();
      }
      System.out.println("CSV data uploaded to PostgreSQL database");
    } catch (SQLException e) {
      System.err.println("Error uploading CSV data to PostgreSQL database: " + e.getMessage());
    }
  }

  public static void displayNodeInfo(Connection connection) throws SQLException {
    System.out.println("Node Info:");

    String query = "select \"nodeID\", xcoord, ycoord, building, \"longName\" from \"L1Nodes\"";
    try (Statement statement = connection.createStatement()) {
      ResultSet rs = statement.executeQuery(query);
      while (rs.next()) {
        String nodeID = rs.getString("nodeID");
        String xcoord = rs.getString("xcoord");
        String ycoord = rs.getString("ycoord");
        String building = rs.getString("building");
        String longname = rs.getString("longname");
        System.out.println(
            "[NodeID: "
                + nodeID
                + "X-Cord: "
                + xcoord
                + "Y-Cord"
                + ycoord
                + "Building: "
                + building
                + "Long Name: "
                + longname
                + "]");
        System.out.println("");
      }
    } catch (SQLException e) {
      System.out.println("Display Node Info Error.");
      throw e;
    }
  }

  public static void displayEdgeInfo(Connection connection) throws SQLException {
    System.out.println("Edge Info:");

    String query = "select \"edgeID\", \"startNode\",\"endNode\" from \"L1Edges\"";
    try (Statement statement = connection.createStatement()) {
      ResultSet rs = statement.executeQuery(query);
      while (rs.next()) {
        String edgeID = rs.getString("edgeID");
        String startNode = rs.getString("startNode");
        String endNode = rs.getString("endNode");
        System.out.println(
            "[EdgeID: " + edgeID + "Start Node: " + startNode + "End Node: " + endNode + "]");
        System.out.println("");
      }
    } catch (SQLException e) {
      System.out.println("Display Edge Info Error.");
      throw e;
    }
  }

  public static void importData(Connection connection) throws SQLException {
    Scanner scanner = new Scanner(System.in);
    // No quotes when importing doc
    System.out.print("Enter the file path of the CSV file to import: ");
    String csvFileName = scanner.nextLine();
    List<String[]> rows = importCSV(csvFileName);
    if (rows != null) {
      System.out.println("Number of rows: " + rows.size());
      for (String[] row : rows) {
        System.out.println(Arrays.toString(row));
      }
      try {
        uploadToPostgreSQL(rows, connection);
      } catch (SQLException e) {
        System.err.println(e.getMessage());
      }
    }
  }

  public static void exportData(Connection connection) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the file path for the CSV export: ");
    String cvsFilePath = scanner.nextLine();

    try (connection) {
      String query = String.format("SELECT * FROM \"L1Nodes\"");
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet resultSet = statement.executeQuery();

      try (PrintWriter writer = new PrintWriter(new File(cvsFilePath))) {
        StringBuilder sb = new StringBuilder();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
          sb.append(metaData.getColumnName(i));
          if (i < columnCount) {
            sb.append(",");
          }
        }
        sb.append(System.lineSeparator());
        while (resultSet.next()) {
          for (int i = 1; i <= columnCount; i++) {
            sb.append(resultSet.getString(i));
            if (i < columnCount) {
              sb.append(",");
            }
          }
          sb.append(System.lineSeparator());
        }
        writer.write(sb.toString());
      }

      System.out.printf("Data exported to CSV file: %s%n", cvsFilePath);
    } catch (SQLException | FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void updateNodeCoords(Connection connection) throws SQLException {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the node ID of the node you want to update the coordinates of: ");
    String nodeID = scanner.nextLine();
    System.out.print("Enter the new x-coordinate of node " + nodeID + ": ");
    String newX = scanner.nextLine();
    System.out.print("Enter the new y-coordinate of node " + nodeID + ": ");
    String newY = scanner.nextLine();

    String query =
        "UPDATE L1Nodes SET xcoord = "
            + newX
            + ", ycoord = "
            + newY
            + " WHERE nodeID = "
            + "nodeID";
    try (Statement statement = connection.createStatement()) {
      ResultSet rs = statement.executeQuery(query);
    } catch (SQLException e) {
      System.out.println("Update Node Coordinates Error.");
      throw e;
    }
  }

  public static void updateNodeName(Connection connection) throws SQLException {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the node ID of the node you want to update the name of: ");
    String nodeID = scanner.nextLine();
    System.out.print("Enter the new long name of node " + nodeID + ": ");
    String newLongName = scanner.nextLine();
    System.out.print("Enter the new short name of node " + nodeID + ": ");
    String newShortName = scanner.nextLine();
    // update node
    String query =
        "UPDATE L1Nodes SET longName = "
            + newLongName
            + ", shortName = "
            + newShortName
            + " WHERE nodeID = "
            + "nodeID";
    try (Statement statement = connection.createStatement()) {
      ResultSet rs = statement.executeQuery(query);
    } catch (SQLException e) {
      System.out.println("Update Node Names Error.");
      throw e;
    }
  }

  public static void deleteNode(Connection connection) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the node ID of the node you want to delete: ");
    String nodeID = scanner.nextLine();
    System.out.print("Are you sure you want to delete node " + nodeID + "(Y/N)? ");
    String sureDelete = scanner.nextLine();
    if (sureDelete.equalsIgnoreCase("y")) {
      // Delete node
    } else {
      System.out.println("Deletion terminated");
    }
  }

  public static void deleteEdge(Connection connection) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the edge ID of the edge you want to delete: ");
    String edgeID = scanner.nextLine();
    System.out.print("Are you sure you want to delete edge " + edgeID + "(Y/N)? ");
    String sureDelete = scanner.nextLine();
    if (sureDelete.equalsIgnoreCase("y")) {
      // Delete edge
    } else {
      System.out.println("Deletion terminated");
    }
  }

  public static void runQuery(Connection connection) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the SQL you want to run: ");
    String command = scanner.nextLine();
    // run command
    System.out.println("Command successful");
  }

  public static void displayHelp() {
    System.out.println(
        "---Help---\n"
            + "Choose from the following commands:\n"
            + "(1) Display node information\n"
            + "(2) Display edge information\n"
            + "(3) Import data from CSV file\n"
            + "(4) Export data into CSV file\n"
            + "(5) Update node coordinates\n"
            + "(6) Update node name\n"
            + "(7) Delete node\n"
            + "(8) Delete edge\n"
            + "(9) Run SQL query\n"
            + "(10) Display help\n"
            + "(11) Exit\n"
            + "---------------------------------------------------\n"
            + "Input the number of the command you want to execute\n"
            + "(1) Display node information\n"
            + "\t-Gives all of the information regarding a specific node\n"
            + "(2) Display edge information\n"
            + "\t-Gives all of the information regarding a specific node\n"
            + "(3) Import data from CSV file\n"
            + "\t-Takes node data from a given CSV file and uploads it into the database\n"
            + "(4) Export data into CSV file\n"
            + "\t-Takes node data from the database and exports it into a given CSV file\n"
            + "(5) Update node coordinates\n"
            + "\t-Changes the coordinate of a given node to a new value\n"
            + "(6) Update node name\n"
            + "\t-Changes the name of a given node to a new value\n"
            + "(7) Delete node\n"
            + "\t-Deletes a node given its id\n"
            + "(8) Delete edge\n"
            + "\t-Deletes an edge given its id\n"
            + "(9) Run SQL query\n"
            + "\t-Will run the inputted SQL query on the database\n"
            + "\t-Only use if you know how to use SQL\n"
            + "(10) Display Help\n"
            + "\t-Displays this text\n"
            + "(11) Exit\n"
            + "\t-Terminates the program");
  }

  public static void main(String[] args) throws SQLException {
    Scanner scanner = new Scanner(System.in);
    String cvsFilePath = " ";
    boolean running = true;
    DatabaseConnection dbc = new DatabaseConnection();
    Connection connection = dbc.DbConnection();
    System.out.println(
        "Choose from the following commands:\n"
            + "(1) Display node information\n"
            + "(2) Display edge information\n"
            + "(3) Import data from CSV file\n"
            + "(4) Export data into CSV file\n"
            + "(5) Update node coordinates\n"
            + "(6) Update node name\n"
            + "(7) Delete node\n"
            + "(8) Delete edge\n"
            + "(9) Run SQL query\n"
            + "(10) Display help\n"
            + "(11) Exit");

    String optionChosen = "help";
    while (running) {
      optionChosen = scanner.nextLine();
      optionChosen = optionChosen.toLowerCase();
      optionChosen = optionChosen.replaceAll("\\s", ""); // Removes whitespace
      switch (optionChosen) {
        case "1":
          displayNodeInfo(connection);
          break;
        case "2":
          displayEdgeInfo(connection);
          break;
        case "3":
          importData(connection);
          break;
        case "4":
          exportData(connection);
          break;
        case "5":
          updateNodeCoords(connection);
          break;
        case "6":
          updateNodeName(connection);
          break;
        case "7":
          deleteNode(connection);
          break;
        case "8":
          deleteEdge(connection);
          break;
        case "9":
        case "sql":
          runQuery(connection);
          break;
        case "10":
        case "help":
        case "displayhelp":
          displayHelp();
          break;
        case "11":
        case "exit":
          System.out.println("Terminating program");
          running = false;
          break;
        default:
          System.out.println("Invalid command. Please try again");
          break;
      }
    }
  }
}
