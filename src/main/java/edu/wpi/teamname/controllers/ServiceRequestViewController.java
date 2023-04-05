package edu.wpi.teamname.controllers;

import edu.wpi.teamname.database.DatabaseConnection;
import java.io.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class ServiceRequestViewController {
  @FXML TableView table;
  //    @FXML
  //    TableColumn table1;
  //    @FXML
  //    TableColumn table2;
  //    @FXML
  //    TableColumn table3;
  //    @FXML
  //    TableColumn table4;
  //    @FXML
  //    TableColumn table5;
  //    @FXML

  //    TableColumn table6;
  public void buildData() {
    //        Connection c ;
    ObservableList<ObservableList> data = FXCollections.observableArrayList();
    try {
      DatabaseConnection dbc = new DatabaseConnection();
      Connection c = dbc.DbConnection();

      // SQL FOR SELECTING ALL OF CUSTOMER
      String SQL = "SELECT * from \"ServiceRequest\"";
      // ResultSet
      ResultSet rs = c.createStatement().executeQuery(SQL);

      /**
       * ******************************** TABLE COLUMN ADDED DYNAMICALLY *
       * ********************************
       */
      for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
        // We are using non property style for making dynamic table
        final int j = i;
        TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
        col.setCellValueFactory(
            new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
              public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(j).toString());
              }
            });

        table.getColumns().addAll(col);
        System.out.println("Column [" + i + "] ");
      }

      /**
       * ****************************** Data added to ObservableList *
       * ******************************
       */
      while (rs.next()) {
        // Iterate Row
        ObservableList<String> row = FXCollections.observableArrayList();
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
          // Iterate Column
          row.add(rs.getString(i));
        }
        System.out.println("Row [1] added " + row);
        data.add(row);
      }

      // FINALLY ADDED TO TableView
      table.setItems(data);
      c.close();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error on Building Data");
    }
  }

  @FXML
  public void initialize() {
    buildData();
  }
}
