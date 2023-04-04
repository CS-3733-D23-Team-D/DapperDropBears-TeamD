package edu.wpi.teamname.database;

import lombok.Getter;
import lombok.Setter;

public class ItemsOrdered {
  @Setter @Getter private int requestID;
  @Setter @Getter private int itemID;

  public ItemsOrdered(int requestID, int itemID) {
    this.requestID = requestID;
    this.itemID = itemID;
  }
}
