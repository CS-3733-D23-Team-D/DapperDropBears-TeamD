package edu.wpi.teamname.servicerequests;

public enum Status {
  BLANK("blank"),
  PROCESSING("Processing"),
  DONE("Done");

  private final String status;

  Status(String status) {
    this.status = status;
  }

  public String getStatusString() {
    return status;
  }
}
