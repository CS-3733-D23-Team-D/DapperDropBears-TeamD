package edu.wpi.teamname.servicerequests;

import edu.wpi.teamname.Node;
import java.time.Instant;
import lombok.Getter;

public abstract class ServiceRequest {
  private Node deliveryLocation;
  //  @Getter private int deliverBy; // Instant .now() .toString()
  private int requestedAt;

  @Getter private Instant deliverBy;

  public ServiceRequest() {
    deliverBy = Instant.now();
    requestedAt = 0;
  }

  public abstract String requestInfo();

  @Override
  public String toString() {
    return this.requestInfo();
  }

  public void setTime() {
    //    deliverBy = (int) (System.currentTimeMillis() / 1000);
    deliverBy = Instant.now();
  }
}
