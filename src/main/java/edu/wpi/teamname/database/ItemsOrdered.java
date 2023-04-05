package edu.wpi.teamname.database;

import lombok.Getter;
import lombok.Setter;

public class ItemsOrdered {
  @Setter @Getter private int requestID;
  @Setter @Getter private int itemID;
  @Setter @Getter private int quantity;

  public ItemsOrdered(int requestID, int itemID, int quantity) {
    this.requestID = requestID;
    this.itemID = itemID;
    this.quantity = quantity;
  }

  public String toString() {
    return "[" + requestID + " " + itemID + " " + quantity + "]";
  }
}
