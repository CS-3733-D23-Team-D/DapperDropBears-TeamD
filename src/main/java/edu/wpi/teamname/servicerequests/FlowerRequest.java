package edu.wpi.teamname.servicerequests;

import edu.wpi.teamname.requestItems.Flower;
import java.util.ArrayList;

public class FlowerRequest extends ServiceRequest {
  private ArrayList<Flower> flowers;

  public FlowerRequest() {
    super();
  }

  public String requestInfo() {
    return flowers.get(0).getName();
  }
}
