package edu.wpi.teamname.requestItems;

import lombok.Getter;

public class RequestItem {

  @Getter private final String name;

  @Getter private final double price;

  public RequestItem(String name, double price) {
    this.name = name;
    this.price = price;
  }

  @Override
  public String toString() {
    return this.getName();
  }
}
