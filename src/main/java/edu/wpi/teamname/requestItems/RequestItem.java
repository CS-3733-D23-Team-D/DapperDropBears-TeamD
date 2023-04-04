package edu.wpi.teamname.requestItems;

import lombok.Getter;

public class RequestItem {

  @Getter private final String name;

  public RequestItem(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return this.getName();
  }


}
