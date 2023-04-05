package edu.wpi.teamname.servicerequests;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IItem {
  public void addItem(int id) throws SQLException;

  public void clearItems();

  public ArrayList<Integer> getAllIDs() throws SQLException;

  public ArrayList<String> getAllNames() throws SQLException;
}
