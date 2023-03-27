package edu.wpi.teamname.servicerequests;

import edu.wpi.teamname.requestItems.MealItem;
import java.util.ArrayList;

public class MealRequest extends ServiceRequest {
  private ArrayList<MealItem> main;
  private ArrayList<MealItem> side;
  private ArrayList<MealItem> drink;

  public MealRequest() {
    super();
    main = new ArrayList<MealItem>();
    side = new ArrayList<MealItem>();
  }

  public String requestInfo() {
    return main.get(0).getName() + " and " + side.get(0).getName();
  }

  public void addMain(MealItem item) {
    main.add(item);
  }

  public void addSide(MealItem item) {
    side.add(item);
  }
}
