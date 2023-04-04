package edu.wpi.teamname.servicerequests;

import java.sql.SQLException;

public interface IItem {
  public void addItem(int id) throws SQLException;

  public void clearItems();
}
