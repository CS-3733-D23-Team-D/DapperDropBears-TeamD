package edu.wpi.teamname.database;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DataManager {

  public static void requestService(Connection connection) {}

  public static void moveNode(Connection connection) {
    Scanner scan = new Scanner(System.in);
    boolean moving = true;
    while (moving) {
      System.out.println("What node would you like to move? ");
      int moveNode = scan.nextInt();
      System.out.println("To which node would you like to switch it with?");
      int switchNode = scan.nextInt();
      //
    }
  }

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
   * Uploads CSV data to a PostgreSQL database table "LocationName"
   *
   * @param csvData a List of String arrays representing the rows and columns of CSV data
   * @param connection a Connection object to connect to the PostgreSQL database
   * @throws SQLException if an error occurs while uploading the data to the database
   */
  public static void uploadLocationNameToPostgreSQL(List<String[]> csvData, Connection connection)
      throws SQLException {

    try (connection) {
      String query =
          "INSERT INTO \"LocationName\" (\"longName\", \"shortName\", \"nodeType\") "
              + "VALUES (?, ?, ?)";
      PreparedStatement statement = connection.prepareStatement("TRUNCATE TABLE \"LocationName\";");
      statement.executeUpdate();
      statement = connection.prepareStatement(query);

      for (int i = 1; i < csvData.size(); i++) {
        String[] row = csvData.get(i);
        statement.setString(1, row[0]); // longName is a string column
        statement.setString(2, row[1]); // shortName is a string column
        statement.setString(3, row[2]); // nodeType is a string column

        statement.executeUpdate();
      }
      System.out.println("CSV data uploaded to PostgreSQL database");
    } catch (SQLException e) {
      System.err.println("Error uploading CSV data to PostgreSQL database: " + e.getMessage());
    }
  }
  /**
   * Uploads CSV data to a PostgreSQL database table "Move"
   *
   * @param csvData a List of String arrays representing the rows and columns of CSV data
   * @param connection a Connection object to connect to the PostgreSQL database
   * @throws SQLException if an error occurs while uploading the data to the database
   */
  public static void uploadMoveToPostgreSQL(List<String[]> csvData, Connection connection)
      throws SQLException {

    try (connection) {
      String query =
          "INSERT INTO \"Move\" (\"nodeID\", \"longName\", \"date\") " + "VALUES (?, ?, ?)";
      PreparedStatement statement = connection.prepareStatement("TRUNCATE TABLE \"Move\";");
      statement.executeUpdate();
      statement = connection.prepareStatement(query);

      for (int i = 1; i < csvData.size(); i++) {
        String[] row = csvData.get(i);
        statement.setInt(1, Integer.parseInt(row[0])); // nodeId is an int column
        statement.setString(2, row[1]); // longName is a string column
        statement.setString(3, row[2]); // date is a string column

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
          "INSERT INTO \"Node\" (\"nodeID\", xcoord, ycoord, floor, building) "
              + "VALUES (?, ?, ?, ?, ?)";
      PreparedStatement statement = connection.prepareStatement("TRUNCATE TABLE \"Node\";");
      statement.executeUpdate();
      statement = connection.prepareStatement(query);

      for (int i = 1; i < csvData.size(); i++) {
        String[] row = csvData.get(i);
        statement.setInt(1, Integer.parseInt(row[0])); // nodeID is a string column
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
   * Display nodes located on every floor the parameter String is on within the "Node" table
   *
   * @param floor a String representing the floor the user wants to display nodes on
   * @param connection a Connection object to connect to the PostgreSQL database
   * @throws SQLException if an error occurs while displaying the data
   */
  public static void displayNodesByFloor(Connection connection, String floor) {
    String query = "SELECT * FROM \"Node\" WHERE floor = ?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setString(1, floor);
      ResultSet rs = statement.executeQuery();

      while (rs.next()) {
        System.out.print("[NodeID: " + rs.getInt("nodeID") + "], ");
        System.out.print("[XCord: " + rs.getString("xcoord") + "], ");
        System.out.print("[YCord: " + rs.getString("ycoord") + "], ");
        System.out.print("[Floor: " + rs.getString("floor") + "], ");
        System.out.print("[Building: " + rs.getString("building") + "]");
        System.out.println();
      }
    } catch (SQLException ex) {
      throw new RuntimeException(ex);
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
    if (ans == 1) { // if you want all node info
      System.out.println("Node Info:");

      String query =
          "SELECT DISTINCT q.\"nodeID\", \"longName\", \"shortName\", \"nodeType\", xcoord, ycoord, building, floor, date\n"
              + "FROM (SELECT n.\"longName\", \"shortName\", n.\"nodeID\", \"nodeType\", xcoord, ycoord, building, floor, date\n"
              + "      FROM \"LocationName\",\n"
              + "           (select \"Move\".\"nodeID\", xcoord, ycoord, floor, building, \"longName\", date\n"
              + "            FROM \"Node\", \"Move\"\n"
              + "            where \"Node\".\"nodeID\" = \"Move\".\"nodeID\") n\n"
              + "      WHERE \"LocationName\".\"longName\" = n.\"longName\") w,\n"
              + "             (SELECT \"nodeID\" FROM (SELECT n.\"nodeID\"\n"
              + "               FROM \"LocationName\",\n"
              + "                    (select \"Move\".\"nodeID\", xcoord, ycoord, floor, building, \"longName\", date\n"
              + "                     FROM \"Node\", \"Move\"\n"
              + "                     where \"Node\".\"nodeID\" = \"Move\".\"nodeID\") n\n"
              + "               WHERE \"LocationName\".\"longName\" = n.\"longName\"\n"
              + "               AND date < (SELECT cast(now() AS DATE))) j\n"
              + "    GROUP BY \"nodeID\") q\n"
              + "WHERE q.\"nodeID\" = w.\"nodeID\";";
      try (Statement statement = connection.createStatement()) {
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
          String nodeID = rs.getString("nodeID");
          String xcoord = rs.getString("xcoord");
          String ycoord = rs.getString("ycoord");
          String floor = rs.getString("floor");
          String building = rs.getString("building");
          String longName = rs.getString("longName");
          String shortName = rs.getString("shortName");
          String nodeType = rs.getString("nodeType");
          String date = rs.getString("date");
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
                  + nodeType
                  + ", Long Name:"
                  + longName
                  + ", Short Name:"
                  + shortName
                  + ", Date Last Moved:"
                  + date
                  + "]");
        }
      } catch (SQLException e) {
        System.out.println("Display Node Info Error.");
        throw e;
      }

    } else if (ans == 2) { // if you want specific node info
      System.out.println("Enter Node ID: ");
      int selectNode = scan.nextInt();

      System.out.println("Node " + selectNode + " Info:");
      String query =
          "Select\n"
              + "    \"nodeID\", \"longName\", \"shortName\", xcoord, ycoord, \"nodeType\", building, floor, date\n"
              + "FROM\n"
              + "    (SELECT\n"
              + "         new1.\"longName\", \"shortName\", new1.\"nodeID\", \"nodeType\", xcoord, ycoord, building, floor, date\n"
              + "       FROM\n"
              + "           \"LocationName\" as l,\n"
              + "            (Select\n"
              + "                 m.\"nodeID\", xcoord, ycoord, floor, building, \"longName\", date\n"
              + "             FROM\n"
              + "                 \"Node\" as n, \"Move\" as m\n"
              + "             Where\n"
              + "                 n.\"nodeID\" = m.\"nodeID\") as new1\n"
              + "       Where l.\"longName\" = new1.\"longName\") new2\n"
              + "WHERE\n"
              + "    new2.\"nodeID\"= "
              + selectNode
              + " AND date =\n"
              + "    (Select max(date) From \"Move\" Where \"nodeID\" = "
              + selectNode
              + ");";

      try (Statement statement = connection.createStatement()) {
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
          String nodeID = rs.getString("nodeID");
          String xcoord = rs.getString("xcoord");
          String ycoord = rs.getString("ycoord");
          String floor = rs.getString("floor");
          String building = rs.getString("building");
          String longName = rs.getString("longName");
          String shortName = rs.getString("shortName");
          String nodeType = rs.getString("nodeType");
          String date = rs.getString("date");
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
                  + nodeType
                  + ", Long Name:"
                  + longName
                  + ", Short Name:"
                  + shortName
                  + ", Date Last Moved:"
                  + date
                  + "]");
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

  /*public static void displayServiceRequests() throws SQLException {
    DatabaseConnection dbc = new DatabaseConnection();
    Connection connection = dbc.DbConnection();

    String query = "SELECT "
  }*/

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
    System.out.println(
        "Press 0 for Node import"
            + "\npress 1 for Edge import"
            + "\npress 2 for LocationName import"
            + "\npress 3 for Move import: ");
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
    } else if (edXorNo == 2) {
      System.out.println("You chose to import LocationName data ");
      List<String[]> rows = importCSV(csvFileName);
      if (rows != null) {
        System.out.println("Number of rows: " + rows.size());
        for (String[] row : rows) {
          System.out.println(Arrays.toString(row));
        }
        try {
          uploadLocationNameToPostgreSQL(rows, connection);
        } catch (SQLException e) {
          System.err.println(e.getMessage());
        }
      }
    } else if (edXorNo == 3) {
      System.out.println("You chose to import Move data ");
      List<String[]> rows = importCSV(csvFileName);
      if (rows != null) {
        System.out.println("Number of rows: " + rows.size());
        for (String[] row : rows) {
          System.out.println(Arrays.toString(row));
        }
        try {
          uploadMoveToPostgreSQL(rows, connection);
        } catch (SQLException e) {
          System.err.println(e.getMessage());
        }
      }
    } else {
      System.out.println("Unknown input. Please press 1, 2, 3, or 0 ");
      importData(connection);
    }
  }
  /**
   * Exports data from the "Node", "Edge", "LocationName", and "Move" tables in the PostgreSQL
   * database to a CSV file specified by the user. The function prompts the user to enter the file
   * path for the CSV export and then executes a SQL query to retrieve all data from the prompted
   * table. The results are written to the CSV file in comma-separated format.
   *
   * <p>Note: LOCAL file path NO quotations!
   *
   * @param connection a Connection object representing the connection to the PostgreSQL database
   */
  public static void exportData(Connection connection) throws SQLException {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the file path for the CSV export: ");
    String cvsFilePath = scanner.nextLine();
    System.out.println(
        "Press 0 for Node export"
            + "\npress 1 for Edge export"
            + "\npress 2 for LocationName export"
            + "\npress 3 for Move export");
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
        } catch (SQLException | FileNotFoundException e) {
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
    } else if (userChoice == 2) {
      try (connection) {
        System.out.println("You chose LocationName export");
        String query = String.format("SELECT * FROM \"LocationName\"");
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
    } else if (userChoice == 3) {
      try (connection) {
        System.out.println("You chose Move export");
        String query = String.format("SELECT * FROM \"Move\"");
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
      System.out.printf("Input not recognized. Please only input 1,2,3,or 0");
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
    int nodeID = scanner.nextInt();
    // Check if the node ID exists in the Node table
    String queryCheckNode = "SELECT COUNT(*) FROM \"Node\" WHERE \"nodeID\" = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(queryCheckNode)) {
      pstmt.setInt(1, nodeID);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next() && rs.getInt(1) == 0) {
        System.out.println("Node ID " + nodeID + " does not exist in the Node table.");
        return;
      }
    } catch (SQLException e) {
      System.out.println("Error checking for node ID " + nodeID + " in the Node table.");
      throw e;
    }
    System.out.print("Enter the new x-coordinate of node " + nodeID + ": ");
    int newX = scanner.nextInt();
    System.out.print("Enter the new y-coordinate of node " + nodeID + ": ");
    int newY = scanner.nextInt();

    String query = "UPDATE \"Node\" SET xcoord = ?, ycoord = ? WHERE \"nodeID\" = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      pstmt.setInt(1, newX);
      pstmt.setInt(2, newY);
      pstmt.setInt(3, nodeID);
      int rowsUpdated = pstmt.executeUpdate();
      if (rowsUpdated > 0) {
        System.out.println("Node successfully updated");
      } else {
        System.out.println("Node not updated");
      }
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

    Scanner scanner = new Scanner(System.in);
    System.out.print("Would you like to update the long name? (Y/N)");
    String yesNo = scanner.nextLine();
    if (yesNo.equalsIgnoreCase("y")) {
      updateLongName(connection);
    }
    System.out.print("Would you like to continue editing?(Y/N)");
    yesNo = scanner.nextLine();
    if (yesNo.equalsIgnoreCase("n")) {
      return;
    }
    System.out.print("Enter the node ID of the node you want to update the name of: ");
    int nodeID = scanner.nextInt();
    scanner.nextLine(); // consume the newline character left by nextInt()

    // find the long name for the node from the Move table
    String queryMove = "SELECT \"longName\" FROM \"Move\" WHERE \"nodeID\" = ?";
    String longName = null;
    try (PreparedStatement pstmtMove = connection.prepareStatement(queryMove)) {
      pstmtMove.setInt(1, nodeID);
      ResultSet rsMove = pstmtMove.executeQuery();
      if (rsMove.next()) {
        longName = rsMove.getString(1);
      } else {
        System.out.println("Node ID " + nodeID + " does not exist in the Move table.");
        return;
      }
    } catch (SQLException e) {
      System.out.println("Error checking for node ID " + nodeID + " in the Move table.");
      throw e;
    }

    // find the LocationName record for the node using the long name
    String queryLocation =
        "SELECT \"shortName\", \"nodeType\" FROM \"LocationName\" WHERE \"longName\" = ?";
    try (PreparedStatement pstmtLocation = connection.prepareStatement(queryLocation)) {
      pstmtLocation.setString(1, longName);
      ResultSet rsLocation = pstmtLocation.executeQuery();
      if (rsLocation.next()) {
        String oldShortName = rsLocation.getString("shortName");
        String oldNodeType = rsLocation.getString("nodeType");
        System.out.println("Old short name: " + oldShortName);
        System.out.println("Old node type: " + oldNodeType);
        System.out.print("Enter the new short name: ");
        String newShortName = scanner.nextLine();
        System.out.print("Enter the new node type: ");
        String newNodeType = scanner.nextLine();

        // update the LocationName record with the new values
        String queryUpdate =
            "UPDATE \"LocationName\" SET \"shortName\" = ?, \"nodeType\" = ? WHERE \"longName\" = ?";
        try (PreparedStatement pstmtUpdate = connection.prepareStatement(queryUpdate)) {
          pstmtUpdate.setString(1, newShortName);
          pstmtUpdate.setString(2, newNodeType);
          pstmtUpdate.setString(3, longName);
          int rowsUpdated = pstmtUpdate.executeUpdate();
          if (rowsUpdated > 0) {
            System.out.println("LocationName successfully updated");
          } else {
            System.out.println("LocationName not updated");
          }
        } catch (SQLException e) {
          System.out.println("Error updating LocationName record for node ID " + nodeID);
          throw e;
        }
      } else {
        System.out.println("No LocationName record found for node ID " + nodeID);
      }
    } catch (SQLException e) {
      System.out.println("Error retrieving LocationName record for node ID " + nodeID);
      throw e;
    }
  }

  public static void updateLongName(Connection connection) throws SQLException {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the current long name: ");
    String currentLongName = scanner.nextLine();
    System.out.print("Enter the new long name: ");
    String newLongName = scanner.nextLine();

    // update the Move table with the new long name
    String updateMoveQuery = "UPDATE \"Move\" SET \"longName\" = ? WHERE \"longName\" = ?";
    try (PreparedStatement pstmtMove = connection.prepareStatement(updateMoveQuery)) {
      pstmtMove.setString(1, newLongName);
      pstmtMove.setString(2, currentLongName);
      int rowsUpdated = pstmtMove.executeUpdate();
      if (rowsUpdated > 0) {
        System.out.println("Move table updated successfully");
      } else {
        System.out.println("No records found in the Move table for the provided long name");
      }
    } catch (SQLException e) {
      System.out.println("Error updating Move table: " + e.getMessage());
      throw e;
    }

    // update the LocationName table with the new long name
    String updateLocationNameQuery =
        "UPDATE \"LocationName\" SET \"longName\" = ? WHERE \"longName\" = ?";
    try (PreparedStatement pstmtLocationName =
        connection.prepareStatement(updateLocationNameQuery)) {
      pstmtLocationName.setString(1, newLongName);
      pstmtLocationName.setString(2, currentLongName);
      int rowsUpdated = pstmtLocationName.executeUpdate();
      if (rowsUpdated > 0) {
        System.out.println("LocationName table updated successfully");
      } else {
        System.out.println("No records found in the LocationName table for the provided long name");
      }
    } catch (SQLException e) {
      System.out.println("Error updating LocationName table: " + e.getMessage());
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
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the node ID of the node you want to delete: ");
    int nodeid = scanner.nextInt();
    scanner.nextLine();
    System.out.print("Enter the long name of the node you want to delete: ");
    String longName = scanner.nextLine();
    // Show edge nodes being deleted also
    String query2 =
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

    try (Statement statement2 = connection.createStatement()) {
      ResultSet rs2 = statement2.executeQuery(query2);
      System.out.println("Deleting " + nodeid + " will delete this information too: ");
      while (rs2.next()) {
        System.out.print("[NodeID: " + rs2.getString("nodeID") + "], ");
        System.out.print("[NodeType: " + rs2.getString("nodeType") + "], ");
        System.out.print("[LongName: " + rs2.getString("longName") + "], ");
        System.out.print("[ShortName: " + rs2.getString("shortName") + "], ");
        System.out.print("[XCord: " + rs2.getString("xcoord") + "], ");
        System.out.print("[YCord: " + rs2.getString("ycoord") + "], ");
        System.out.print("[Floor: " + rs2.getString("floor") + "], ");
        System.out.print("[Building: " + rs2.getString("building") + "], ");
        System.out.print("[StartNode: " + rs2.getString("startNode") + "], ");
        System.out.print("[EndNode: " + rs2.getString("endNode") + "]");
        System.out.println();
      }
    } catch (SQLException e2) {
      System.out.println("Delete Node Connection Error. ");
      throw e2;
    }
    System.out.print("Are you sure you want to delete node " + nodeid + "(Y/N)? ");
    String sureDelete = scanner.nextLine();
    if (sureDelete.equalsIgnoreCase("y") || sureDelete.equalsIgnoreCase("Y")) {
      String query2node = "Delete from \"Node\" " + "where \"nodeID\" = " + nodeid;
      String query2edge =
          "Delete from \"Edge\" "
              + "where \"startNode\" = "
              + nodeid
              + " OR \"endNode\" = "
              + nodeid;
      String query2move = "Delete from \"Move\" " + "where \"nodeID\" = " + nodeid;
      String query2Loc =
          "Delete from \"LocationName\"" + " where \"longName\" = '" + longName + "'";

      // delete in edge table
      try (PreparedStatement statement2 = connection.prepareStatement(query2edge)) {
        statement2.executeUpdate();
      } catch (SQLException e) {
        System.out.println("Delete in Edge table error.");
        throw e;
      }
      // delete in move table
      try (PreparedStatement statement3 = connection.prepareStatement(query2move)) {
        statement3.executeUpdate();
      } catch (SQLException e2) {
        System.out.println("Delete in Move table error.");
        throw e2;
      }
      // delete in locationName table
      try (PreparedStatement statement4 = connection.prepareStatement(query2Loc)) {
        statement4.executeUpdate();
      } catch (SQLException e4) {
        System.out.println("Delete in LocationName table error. " + e4);
        throw e4;
      }
      // delete in node table
      try (PreparedStatement statement = connection.prepareStatement(query2node)) {
        statement.executeUpdate();
      } catch (SQLException e3) {
        System.out.println("Delete in Node table error.");
        throw e3;
      }

      // check deleted
      try (Statement statement2 = connection.createStatement()) {
        ResultSet rs2 = statement2.executeQuery(query2);
        int count = 0;
        while (rs2.next()) count++;
        if (count == 0)
          System.out.println("Node " + nodeid + ", " + longName + " deleted successfully.");
        else System.out.println("Node " + nodeid + ", " + longName + " not found.");
      } catch (SQLException e5) {
        System.out.println("Error checking delete.");
        throw e5;
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
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the start node of the edge you want to delete: ");
    int startNodeID = scanner.nextInt();
    System.out.print("Enter the end node of the edge you want to delete: ");
    int endNodeID = scanner.nextInt();
    String query3 =
        "Select From \"Edge\" Where \"startNode\" = "
            + startNodeID
            + " AND \"endNode\" = "
            + endNodeID;
    // DONT TOUCH THIS v
    //        "Select *\n"
    //            + "From\n"
    //            + "    (Select\n"
    //            + "         new2.\"nodeType\", new2.\"longName\", new2.\"shortName\",
    // new2.\"nodeID\", new2.xcoord, "
    //            + "new2.ycoord, new2.floor, new2.building, e.\"startNode\", e.\"endNode\"\n"
    //            + "    From\n"
    //            + "        (Select\n"
    //            + "             new1.\"nodeType\", new1.\"longName\", new1.\"shortName\",
    // n.\"nodeID\", n.xcoord, n.ycoord, n.floor, n.building\n"
    //            + "        From\n"
    //            + "            (Select l.\"nodeType\", l.\"longName\", l.\"shortName\",
    // m.\"nodeID\"\n"
    //            + "            From\n"
    //            + "                \"LocationName\" l, \"Move\" m\n"
    //            + "            Where\n"
    //            + "                l.\"longName\" = m.\"longName\") as new1, \"Node\" as n\n"
    //            + "        Where\n"
    //            + "            new1.\"nodeID\" = n.\"nodeID\") as new2, \"Edge\" as e\n"
    //            + "    Where\n"
    //            + "        new2.\"nodeID\" = e.\"endNode\" OR new2.\"nodeID\" = e.\"startNode\")
    // as new3\n"
    //            + "Where new3.\"startNode\" = "
    //            + startNodeID
    //            + " AND new3.\"endNode\" = "
    //            + endNodeID;

    try (Statement statement3 = connection.createStatement()) {
      ResultSet rs3 = statement3.executeQuery(query3);
      System.out.println(
          "Deleting "
              + startNodeID
              + " to "
              + endNodeID
              + " will delete this node connection from the database");

      scanner.nextLine();
      System.out.print(
          "Are you sure you want to delete edge " + startNodeID + " to " + endNodeID + " (Y/N)? ");
      String sureDelete = scanner.nextLine();

      if (sureDelete.equalsIgnoreCase("y") || sureDelete.equalsIgnoreCase("Y")) {
        // delete edge
        String deleteQ =
            "Delete From \"Edge\" Where \"startNode\" = "
                + startNodeID
                + " AND \"endNode\" = "
                + endNodeID;
        try (PreparedStatement statement = connection.prepareStatement(deleteQ)) {
          int rowsDeleted = statement.executeUpdate();
          if (rowsDeleted > 0) {
            System.out.println(
                "Edge " + startNodeID + " to " + endNodeID + " successfully deleted.");
          } else {
            System.out.println(
                "Edge " + startNodeID + " to " + endNodeID + " not found in the database.");
          }
        } catch (SQLException e) {
          System.out.println(
              "Error deleting edge " + startNodeID + " to " + endNodeID + ": " + e.getMessage());
        }
      } else {
        System.out.println("Deletion terminated");
      }
    }
  }

  public static void Login() throws SQLException {
    Scanner scan = new Scanner(System.in);
    System.out.println("Type 1 to Login, 2 to create account, 3 reset password");
    int ans = scan.nextInt();
    if (ans == 1) {
      scan.nextLine();
      System.out.println("Username: ");
      String username = scan.nextLine();
      System.out.println("Password: ");
      String password = scan.nextLine();
      Login login = new Login(username, password);
      boolean l = login.LogInto();
      if (!l) Login();
    } else if (ans == 2) {
      Login login = new Login("", "");
      login.setLogin();
    } else if (ans == 3) {
      System.out.println("Username: ");
      scan.nextLine();
      String username = scan.nextLine();
      Login login = new Login(username, "");
      boolean r = login.resetPass();
      if (!r) Login();
    } else {
      System.out.println("Type 1 or 2 for login.");
      Login();
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
            + "\t-Gives all of the information regarding a specific node or all nodes\n"
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
            + "(10) Move a node"
            + "\t-Will swap the two inputted node locations with each other"
            + "(11) Request a service\n"
            + "\t Request one of the services listed"
            + "(12) Display Help\n"
            + "\t-Displays this text\n"
            + "(13) Login\n"
            + "\t-Log into the app as admin or staff.\n"
            + "(14) Exit\n"
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
            + "(10) Move a node"
            + "(11) Request a service\n"
            + "(12) Display help\n"
            + "(13) Login"
            + "(14) Exit\n");
    String optionChosen = "help";

    DatabaseConnection dbc = new DatabaseConnection();
    Connection connection = dbc.DbConnection();
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
          // runQuery(connection);
          break;
        case "10":
          moveNode(connection);
          break;
        case "11":
          requestService(connection);
          break;
        case "12":
        case "help":
        case "displayhelp":
          displayHelp();
          break;
        case "13":
          Login();
          break;
        case "14":
        case "exit":
          System.out.println("Terminating program.");
          connection.close();
          System.out.println("Connection closed.");
          running = false;
          break;
        default:
          System.out.println("Invalid command. Please try again");
          break;
      }
    }
  }
}
