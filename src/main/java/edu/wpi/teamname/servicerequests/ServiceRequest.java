package edu.wpi.teamname.servicerequests;

import edu.wpi.teamname.Node;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

public class ServiceRequest {

  @Getter @Setter private int requestID;
  @Setter @Getter private String staffName;
  @Setter @Getter private String patientName;

  @Setter @Getter private String roomNumber;
  private Node deliveryLocation;

  @Setter private String notes;

  @Setter @Getter private LocalDateTime deliverBy;
  @Setter @Getter private LocalDateTime requestedAt;

  public ServiceRequest(
      int requestID,
      String staffName,
      String patientName,
      String roomNumber,
      LocalDateTime deliverBy) {
    this.requestID = requestID;
    this.staffName = staffName;
    this.patientName = patientName;
    this.roomNumber = roomNumber;
    this.deliverBy = deliverBy;
    requestedAt = LocalDateTime.now();
  }

  public ServiceRequest() {
    requestedAt = LocalDateTime.now();
  }

  @Override
  public String toString() {
    return "";
    // return this.getRequestInfo();
  }

  /*public String getRequestInfo() {
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
  */
  public void roomNumToLocationNode() {
    // Todo!!!
    // Convert the string property of this object into the corresponding node
  }
}
