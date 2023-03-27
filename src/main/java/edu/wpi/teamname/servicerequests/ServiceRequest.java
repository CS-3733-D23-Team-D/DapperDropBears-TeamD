package edu.wpi.teamname.servicerequests;

import edu.wpi.teamname.Node;
import edu.wpi.teamname.requestItems.RequestItem;
import java.time.Instant;
import java.util.ArrayList;
import lombok.Getter;

public class ServiceRequest {
  private Node deliveryLocation;
  @Getter private Instant deliverBy;
  private Instant requestedAt;

  private ArrayList<RequestItem> requestItems;

  public ServiceRequest() {
    deliverBy = Instant.now();
    requestedAt = Instant.now();
    requestItems = new ArrayList<RequestItem>();
  }

  @Override
  public String toString() {
    return this.getRequestInfo();
  }

  public void setTime() {
    deliverBy = Instant.now();
  }

  public void addItem(String item) {
    RequestItem reqestItem = new RequestItem(item);
    this.requestItems.add(reqestItem);
  }

  public String getRequestInfo() {
    String returnStr = "";
    for (int i = 0; i < requestItems.size(); i++) {
      returnStr += requestItems.get(i);
      if (i != (requestItems.size() - 1)) {
        returnStr += " and ";
      }
    }
    return returnStr;
  }
}
