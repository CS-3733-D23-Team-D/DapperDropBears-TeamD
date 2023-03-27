package edu.wpi.teamname.requestItems;

import lombok.Getter;

public class RequestItem {

  @Getter private String name;

  public RequestItem(String name) {
    this.name = name;
  }
}
