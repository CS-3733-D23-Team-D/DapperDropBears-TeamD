package edu.wpi.teamname.servicerequests;

import edu.wpi.teamname.Node;
import edu.wpi.teamname.requestItems.RequestItem;
import java.time.LocalDate;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

public class ServiceRequest {

  @Setter private String staffName;
  @Setter private String patientName;

  @Setter private String roomNumber;
  private Node deliveryLocation;

  @Setter private String notes;

  @Setter @Getter private LocalDate deliverBy;
  private LocalDate requestedAt;

  private ArrayList<RequestItem> requestItems;

  public ServiceRequest() {
    deliverBy = LocalDate.now();
    requestedAt = LocalDate.now();
    requestItems = new ArrayList<RequestItem>();
  }

  @Override
  public String toString() {
    return this.getRequestInfo();
  }

  public void addItem(String item) {
    RequestItem reqestItem = new RequestItem(item);
    this.requestItems.add(reqestItem);
  }

  public String getRequestInfo() {
    String returnStr = "This Request has %s deliver (%s) to %s on %s. Notes %s";

    String requestStr = "";

    for (int i = 0; i < requestItems.size(); i++) {
      requestStr += requestItems.get(i);
      if (i != (requestItems.size() - 1)) {
        requestStr += " and ";
      }
    }

    String dateStr = deliverBy.toString();

    returnStr = String.format(returnStr, staffName, requestStr, patientName, dateStr, notes);
    return returnStr;
  }

  public void roomNumToLocationNode() {
    // Todo!!!
    // Convert the string property of this object into the corresponding node
  }
}
