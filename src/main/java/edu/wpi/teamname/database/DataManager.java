package edu.wpi.teamname.database;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DataManager {

  /**
   * Reads a CSV file and returns its contents as a list of string arrays.
   *
   * @param fileName the name of the CSV file to import
   * @return a list of string arrays representing the contents of the CSV file, or null if an error
   *     occurs
   */
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
  /**
   * Uploads CSV data to a PostgreSQL database table "Edge"
   *
   * @param csvData a List of String arrays representing the rows and columns of CSV data
   * @param connection a Connection object to connect to the PostgreSQL database
   * @throws SQLException if an error occurs while uploading the data to the database
   */
  public static void uploadEdgeToPostgreSQL(List<String[]> csvData, Connection connection)
      throws SQLException {

    try (connection) {
      String query =
          "INSERT INTO \"Edge\" (\"edgeID\", \"startNode\", \"endNode\") " + "VALUES (?, ?, ?)";
      PreparedStatement statement = connection.prepareStatement("TRUNCATE TABLE \"Edge\";");
      statement.executeUpdate();
      statement = connection.prepareStatement(query);

      for (int i = 1; i < csvData.size(); i++) {
        String[] row = csvData.get(i);
        statement.setString(1, row[0]); // edgeID is a string column
        statement.setString(2, row[1]); // startNode is a string column
        statement.setString(3, row[2]); // endNode is a string column

        statement.executeUpdate();
      }
      System.out.println("CSV data uploaded to PostgreSQL database");
    } catch (SQLException e) {
      System.err.println("Error uploading CSV data to PostgreSQL database: " + e.getMessage());
    }
  }
  /**
   * Uploads CSV data to a PostgreSQL database table "Node"
   *
   * @param csvData a List of String arrays representing the rows and columns of CSV data
   * @param connection a Connection object to connect to the PostgreSQL database
   * @throws SQLException if an error occurs while uploading the data to the database
   */
  public static void uploadNodeToPostgreSQL(List<String[]> csvData, Connection connection)
      throws SQLException {

    try (connection) {
      String query =
          "INSERT INTO \"Node\" (\"nodeID\", xcoord, ycoord, floor, building,) "
              + "VALUES (?, ?, ?, ?, ?)";
      PreparedStatement statement = connection.prepareStatement("TRUNCATE TABLE \"Node\";");
      statement.executeUpdate();
      statement = connection.prepareStatement(query);

      for (int i = 1; i < csvData.size(); i++) {
        String[] row = csvData.get(i);
        statement.setString(1, row[0]); // nodeID is a string column
        statement.setInt(2, Integer.parseInt(row[1])); // xcoord is an integer column
        statement.setInt(3, Integer.parseInt(row[2])); // ycoord is an integer column
        statement.setString(4, row[3]); // assuming floor is a string column
        statement.setString(5, row[4]); // assuming building is a string column

        statement.executeUpdate();
      }
      System.out.println("CSV data uploaded to PostgreSQL database");
    } catch (SQLException e) {
      System.err.println("Error uploading CSV data to PostgreSQL database: " + e.getMessage());
    }
  }
  /**
   * Displays node information from the "Node" table in the connected PostgreSQL database.
   *
   * @param connection A Connection object representing the connection to the PostgreSQL database.
   * @throws SQLException If an error occurs while executing the SQL statement.
   */
  public static void displayNodeInfo(Connection connection) throws SQLException {
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter 1 for all node info, or 2 for a specific nodes info.");
    int ans = scan.nextInt();
    if (ans == 1) { // if want all node info
      System.out.println("Node Info:");

      String query = "select * from \"Node\"";
      try (Statement statement = connection.createStatement()) {
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
          String nodeID = rs.getString("nodeID");
          String xcoord = rs.getString("xcoord");
          String ycoord = rs.getString("ycoord");
          String floor = rs.getString("floor");
          String building = rs.getString("building");
          System.out.println(
              "[NodeID:"
                  + nodeID
                  + ", X-Cord:"
                  + xcoord
                  + ", Y-Cord:"
                  + ycoord
                  + ", Floor:"
                  + floor
                  + ", Building:"
                  + building
                  + "]");
          System.out.println("");
        }
      } catch (SQLException e) {
        System.out.println("Display Node Info Error.");
        throw e;
      }

    } else if (ans == 2) { // if want specific node info
      System.out.println("Enter Node ID: ");
      int selectNode = scan.nextInt();

      System.out.println("Node " + selectNode + " Info:");
      String query = "select * from \"Node\" where \"nodeID\" = " + selectNode;
      try (Statement statement = connection.createStatement()) {
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
          String nodeID = rs.getString("nodeID");
          String xcoord = rs.getString("xcoord");
          String ycoord = rs.getString("ycoord");
          String floor = rs.getString("floor");
          String building = rs.getString("building");
          System.out.println(
              "[NodeID:"
                  + nodeID
                  + ", X-Cord:"
                  + xcoord
                  + ", Y-Cord:"
                  + ycoord
                  + ", Floor:"
                  + floor
                  + ", Building:"
                  + building
                  + ", Node Type:"
                  + "]");
          System.out.println("");
        }
      } catch (SQLException e) {
        System.out.println("Display Node Error.");
        throw e;
      }
    } else {
      System.out.println("Please enter 1 or 2.");
      displayNodeInfo(connection);
    }
  }
  /**
   * Displays the edge information from the "Edge" table in the PostgreSQL database.
   *
   * @param connection a Connection object representing a connection to the database
   * @throws SQLException if there is an error while executing the SQL query
   */
  public static void displayEdgeInfo(Connection connection) throws SQLException {
    System.out.println("Edge Info:");

    String query = "select * from \"Edge\"";
    try (Statement statement = connection.createStatement()) {
      ResultSet rs = statement.executeQuery(query);
      while (rs.next()) {
        String startNode = rs.getString("startNode");
        String endNode = rs.getString("endNode");
        System.out.println("[Start Node:" + startNode + ", End Node:" + endNode + "]");
      }
    } catch (SQLException e) {
      System.out.println("Display Edge Info Error.");
      throw e;
    }
  }
  /**
   * Prompts the user to choose between importing a node or edge. Then asks for a file path for a
   * CSV file to import, parses the data, and uploads it to a PostgreSQL database using the
   * importCSV and uploadNodeToPostgreSQL/uploadEdgeToPostgreSQL functions.
   *
   * <p>Notes: Enter a LOCAL path on computer and REMOVE quotations
   *
   * @param connection the connection object to the PostgreSQL database
   * @throws SQLException if there is an error with the database connection or query execution
   */
  public static void importData(Connection connection) throws SQLException {
    Scanner scanner = new Scanner(System.in);
    // No quotes when importing doc
    System.out.println("Enter the file path of the CSV file to import: ");
    String csvFileName = scanner.nextLine();
    System.out.println("Press 0 for Node import" + "\npress 1 for Edge import: ");
    int edXorNo = scanner.nextInt();
    if (edXorNo == 1) {

      System.out.println("You chose to import edge data ");
      List<String[]> edgeRows = importCSV(csvFileName);
      if (edgeRows != null) {
        System.out.println("Number of rows: " + edgeRows.size());
        for (String[] row : edgeRows) {
          System.out.println(Arrays.toString(row));
        }
        try {
          uploadEdgeToPostgreSQL(edgeRows, connection);
        } catch (SQLException e) {
          System.err.println(e.getMessage());
        }
      }
    } else if (edXorNo == 0) {
      System.out.println("You chose to import node data ");
      List<String[]> rows = importCSV(csvFileName);
      if (rows != null) {
        System.out.println("Number of rows: " + rows.size());
        for (String[] row : rows) {
          System.out.println(Arrays.toString(row));
        }
        try {
          uploadNodeToPostgreSQL(rows, connection);
        } catch (SQLException e) {
          System.err.println(e.getMessage());
        }
      }
    } else {
      System.out.println("Unknown input. Please press 1 or 2 ");
      importData(connection);
    }
  }
  /**
   * Exports data from the "Node" and "Edge" tables in the PostgreSQL database to a CSV file
   * specified by the user. The function prompts the user to enter the file path for the CSV export
   * and then executes a SQL query to retrieve all data from the prompted table. The results are
   * written to the CSV file in comma-separated format.
   *
   * <p>Note: LOCAL file path NO quotations!
   *
   * @param connection a Connection object representing the connection to the PostgreSQL database
   */
  public static void exportData(Connection connection) throws SQLException {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the file path for the CSV export: ");
    String cvsFilePath = scanner.nextLine();
    System.out.println("Press 0 for Node import" + "\npress 1 for Edge import: ");
    int userChoice = scanner.nextInt();

    if (userChoice == 1) {
      try (connection) {
        System.out.println("You chose edge export");
        String q = String.format("SELECT * FROM \"Edge\"");
        PreparedStatement state = connection.prepareStatement(q);
        ResultSet rs = state.executeQuery();
        try (PrintWriter writer = new PrintWriter(new File(cvsFilePath))) {
          StringBuilder sb = new StringBuilder();
          ResultSetMetaData metaData = rs.getMetaData();
          int columnCount = metaData.getColumnCount();
          for (int i = 1; i <= columnCount; i++) {
            sb.append(metaData.getColumnName(i));
            if (i < columnCount) {
              sb.append(",");
            }
          }
          sb.append(System.lineSeparator());
          while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
              sb.append(rs.getString(i));
              if (i < columnCount) {
                sb.append(",");
              }
            }
            sb.append(System.lineSeparator());
          }
          writer.write(sb.toString());
          System.out.printf("Data exported to CSV file: %s%n", cvsFilePath);
        } catch (SQLException e) {
          throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
          throw new RuntimeException(e);
        }
      }
    } else if (userChoice == 0) {
      try (connection) {
        System.out.println("You chose node export");
        String query = String.format("SELECT * FROM \"Node\"");
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
    } else {
      System.out.printf("Input not recognized. Please only input 1 or 0");
      exportData(connection);
    }
  }

  /**
   * Prompts the user to enter a node ID, as well as new x and y coordinates for the node, and
   * updates the corresponding row in the Node table with the new coordinates.
   *
   * @param connection the connection to the PostgreSQL database
   * @throws SQLException if an error occurs while executing the SQL query
   */
  public static void updateNodeCoords(Connection connection) throws SQLException {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the node ID of the node you want to update the coordinates of: ");
    String nodeID = scanner.nextLine();
    System.out.print("Enter the new x-coordinate of node " + nodeID + ": ");
    String newX = scanner.nextLine();
    System.out.print("Enter the new y-coordinate of node " + nodeID + ": ");
    String newY = scanner.nextLine();

    String query =
        "UPDATE Node SET xcoord = " + newX + ", ycoord = " + newY + " WHERE nodeID = " + nodeID;
    try (Statement statement = connection.createStatement()) {
      ResultSet rs = statement.executeQuery(query);
      System.out.println("Node successfully updated");
    } catch (SQLException e) {
      System.out.println("Update Node Coordinates Error.");
      throw e;
    }
  }

  /**
   * Updates the name of a node in the database. Prompts the user for the node ID, new long name,
   * and new short name. Executes an SQL UPDATE statement to modify the longName and shortName
   * fields of the Node table in the database for the specified node ID with the new values entered
   * by the user.
   *
   * @param connection the connection to the database
   * @throws SQLException if there is an error executing the SQL statement
   */
  public static void updateNodeName(Connection connection) throws SQLException {

    // Need to link to location name table

    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the node ID of the node you want to update the name of: ");
    int nodeID = scanner.nextInt();
    System.out.print("Enter the new long name of node " + nodeID + ": ");
    String newLongName = scanner.nextLine();
    System.out.print("Enter the new short name of node " + nodeID + ": ");
    String newShortName = scanner.nextLine();
    System.out.println("Enter the type of node " + nodeID + ": ");
    String newType = scanner.nextLine();
    // update node

    String queryFix =
        "Select new.\"longName\", new.\"shortName\", new.\"nodeType\" "
            + "From (Select l.\"longName\", l.\"shortName\", l.\"nodeType\", m.\"nodeID\" "
            + "From \"LocationName\" as l, \"Move\" as m "
            + "Where l.\"longName\" = m.\"longName\") new "
            + "Where new.\"nodeID\" = "
            + nodeID;

    String query =
        "UPDATE \"Node\" SET \"longName\" = "
            + newLongName
            + ", \"shortName\" = "
            + newShortName
            + " WHERE \"nodeID\" = "
            + nodeID;
    try (Statement statement = connection.createStatement()) {
      ResultSet rs = statement.executeQuery(query);
      System.out.println("Node successfully updated");
    } catch (SQLException e) {
      System.out.println("Update Node Names Error.");
      throw e;
    }
  }

  /**
   * Prompts the user to enter a node ID and confirms if they want to delete the node along with the
   * connected edges. If confirmed, the function deletes the node and connected edges from the
   * database.
   *
   * @param connection a Connection object representing a connection to a PostgreSQL database
   */
  public static void deleteNode(Connection connection) throws SQLException {

    // Need to edit to work with tables

    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the node ID of the node you want to delete: ");
    String nodeid = scanner.nextLine();

    // Show edge nodes being deleted also

    String queryFix2 =
        "Select *\n"
            + "From\n"
            + "    (Select\n"
            + "         new2.\"nodeType\", new2.\"longName\", new2.\"shortName\", new2.\"nodeID\", new2.xcoord, new2.ycoord, new2.floor, new2.building, e.\"startNode\", e.\"endNode\"\n"
            + "    From\n"
            + "        (Select\n"
            + "             new1.\"nodeType\", new1.\"longName\", new1.\"shortName\", n.\"nodeID\", n.xcoord, n.ycoord, n.floor, n.building\n"
            + "        From\n"
            + "            (Select l.\"nodeType\", l.\"longName\", l.\"shortName\", m.\"nodeID\"\n"
            + "            From\n"
            + "                \"LocationName\" l, \"Move\" m\n"
            + "            Where\n"
            + "                l.\"longName\" = m.\"longName\") as new1, \"Node\" as n\n"
            + "        Where\n"
            + "            new1.\"nodeID\" = n.\"nodeID\") as new2, \"Edge\" as e\n"
            + "    Where\n"
            + "        new2.\"nodeID\" = e.\"endNode\" OR new2.\"nodeID\" = e.\"startNode\") as new3\n"
            + "Where new3.\"nodeID\" = "
            + nodeid;

    String query2 =
        "Select e.\"edgeID\" from \"Edge\" e, \"Node\" n where \'"
            + nodeid.toUpperCase()
            + "\' = e.\"startNode\" or \'"
            + nodeid.toUpperCase()
            + "\' = e.\"endNode\" group by e.\"edgeID\"";
    try (Statement statement2 = connection.createStatement()) {
      ResultSet rs2 = statement2.executeQuery(query2);

      ArrayList<String> edges = new ArrayList<String>();
      while (rs2.next()) {
        String edgeID = rs2.getString("edgeID");
        edges.add(edgeID);
      }

      System.out.println("Deleting " + nodeid + " will delete edges: ");
      System.out.println(edges);
      System.out.println("(Y/N)? ");
      String input = scanner.nextLine();
    } catch (SQLException e2) {
      System.out.println("Delete Node Connection Error. ");
      throw e2;
    }

    System.out.print("Are you sure you want to delete node " + nodeid + "(Y/N)? ");
    String sureDelete = scanner.nextLine();
    if (sureDelete.equalsIgnoreCase("y") || sureDelete.equalsIgnoreCase("Y")) {

      try (PreparedStatement statement =
          connection.prepareStatement("DELETE FROM \"Node\" WHERE \"nodeID\" = ?")) {
        statement.setString(1, nodeid);
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {

          System.out.println("Node " + nodeid + " deleted successfully.");
        } else {
          System.out.println("Node " + nodeid + " not found.");
        }
      } catch (SQLException e) {
        System.out.println("Delete Node Error.");
        throw e;
      }
    } else {
      System.out.println("Deletion terminated");
    }
  }

  /**
   * Allows the user to delete an edge from the graph by entering the edge ID. Prompts the user to
   * confirm the deletion before proceeding.
   *
   * @param connection a Connection object representing the database connection
   */
  public static void deleteEdge(Connection connection) throws SQLException {

    // Need to edit to work with tables

    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the start node of the edge you want to delete: ");
    String startNodeID = scanner.nextLine();
    System.out.print("Enter the end node of the edge you want to delete: ");
    String endNodeID = scanner.nextLine();
    System.out.print(
        "Are you sure you want to delete edge " + startNodeID + " to " + endNodeID + " (Y/N)? ");
    String sureDelete = scanner.nextLine();
    if (sureDelete.equalsIgnoreCase("y") || sureDelete.equalsIgnoreCase("Y")) {
      // delete edge

      String queryFix3 =
          " Select *\n"
              + "From\n"
              + "    (Select\n"
              + "         new2.\"nodeType\", new2.\"longName\", new2.\"shortName\", new2.\"nodeID\", new2.xcoord, new2.ycoord, new2.floor, new2.building, e.\"startNode\", e.\"endNode\"\n"
              + "    From\n"
              + "        (Select\n"
              + "             new1.\"nodeType\", new1.\"longName\", new1.\"shortName\", n.\"nodeID\", n.xcoord, n.ycoord, n.floor, n.building\n"
              + "        From\n"
              + "            (Select l.\"nodeType\", l.\"longName\", l.\"shortName\", m.\"nodeID\"\n"
              + "            From\n"
              + "                \"LocationName\" l, \"Move\" m\n"
              + "            Where\n"
              + "                l.\"longName\" = m.\"longName\") as new1, \"Node\" as n\n"
              + "        Where\n"
              + "            new1.\"nodeID\" = n.\"nodeID\") as new2, \"Edge\" as e\n"
              + "    Where\n"
              + "        new2.\"nodeID\" = e.\"endNode\" OR new2.\"nodeID\" = e.\"startNode\") as new3\n"
              + "Where new3.\"startNode\" = "
              + startNodeID
              + " AND new3.\"endNode\" = "
              + endNodeID;

      String query3 = "DELETE FROM \"Edge\" WHERE \"edgeID\" = ?";
      try (PreparedStatement statement = connection.prepareStatement(query3)) {
        statement.setString(1, startNodeID);
        statement.setString(1, endNodeID);
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
          System.out.println("Edge " + startNodeID + " to " + endNodeID + " successfully deleted.");
        } else {
          System.out.println(
              "Edge " + startNodeID + " to " + endNodeID + " not found in the database.");
        }
      } catch (SQLException e) {
        System.out.println(
            "Error deleting edge " + startNodeID + " to " + endNodeID + ": " + e.getMessage());
        throw e;
      }
    } else {
      System.out.println("Deletion terminated");
    }
  }

  /**
   * Executes an SQL command on the provided database connection.
   *
   * @param connection the database connection to use for executing the command
   * @throws SQLException if there is an error executing the SQL command
   */
  public static void runQuery(Connection connection) throws SQLException {
    Scanner scanner = new Scanner(System.in);
    System.out.print(
        "Enter the SQL you want to run (Put quotes around table names, single quotes around data points): ");
    String query = scanner.nextLine();
    try (Statement statement = connection.createStatement()) {
      ResultSet rs = statement.executeQuery(query);
      ResultSetMetaData rsmd = rs.getMetaData();
      int colNum = rsmd.getColumnCount();
      while (rs.next()) {
        for (int i = 1; i <= colNum; i++) {
          if (i > 1) {
            System.out.print(",\t");
          }
          String colVal = rs.getString(i);
          System.out.print(colVal + " " + rsmd.getColumnName(i));
        }
        System.out.println("");
      }
      System.out.println("Query successfully run");
    } catch (SQLException e) {
      System.out.println("Query failed: " + e.getMessage());
      throw e;
    }
  }

  /**
   * Displays the available commands and their descriptions to the user. Provides information on how
   * to execute each command and what they do. Prompts the user to input the number of the command
   * they want to execute. Commands: Display node information - Gives all of the information
   * regarding a specific node. Display edge information - Gives all of the information regarding a
   * specific edge. Import data from CSV file - Takes node data from a given CSV file and uploads it
   * into the database. Export data into CSV file - Takes node data from the database and exports it
   * into a given CSV file. Update node coordinates - Changes the coordinate of a given node to a
   * new value. Update node name - Changes the name of a given node to a new value. Delete node -
   * Deletes a node given its ID. Delete edge - Deletes an edge given its ID. Run SQL query - Will
   * run the inputted SQL query on the database. Only use if you know how to use SQL. Display help -
   * Displays information on the available commands and their descriptions. Exit - Terminates the
   * program.
   *
   * @return void
   */
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
            + "\tMake sure to put quotes around table names\n"
            + "\tand put single quotes around data points\n"
            + "(10) Display Help\n"
            + "\t-Displays this text\n"
            + "(11) Exit\n"
            + "\t-Terminates the program");
  }

  public static void main(String[] args) throws SQLException {
    Scanner scanner = new Scanner(System.in);
    String cvsFilePath = " ";
    boolean running = true;
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
      DatabaseConnection dbc = new DatabaseConnection();
      Connection connection = dbc.DbConnection();

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
