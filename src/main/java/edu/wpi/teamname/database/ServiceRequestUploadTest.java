package edu.wpi.teamname.database;

import edu.wpi.teamname.servicerequests.FlowerRequest;
import edu.wpi.teamname.servicerequests.MealRequest;
import edu.wpi.teamname.servicerequests.ServiceRequest;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

public class ServiceRequestUploadTest {
  public static void main(String args[]) throws SQLException {

    String string = "SELECT \"flowerID\" FROM Flowers";
    System.out.println(string);
    LocalDateTime date = LocalDateTime.of(1000, Month.JUNE, 29, 19, 30, 40);
    LocalDateTime date2 = LocalDateTime.of(1033, Month.MAY, 29, 10, 30, 40);

    MealRequest mealRequest =
        new MealRequest(3, "stadfsdfff", "patiefsdfsadfnt", "roomfsdfa2", date);
    mealRequest.addMeal(new Meal(1100));
    mealRequest.addMeal(new Meal(1101));
    mealRequest.addMeal(new Meal(1104));
    mealRequest.addMeal(new Meal(1113));
    mealRequest.addMeal(new Meal(1133));
    mealRequest.addMeal(new Meal(1133));
    mealRequest.addMeal(new Meal(1133));
    mealRequest.addMeal(new Meal(1133));
    mealRequest.addMeal(new Meal(1133));
    mealRequest.addMeal(new Meal(1133));
    mealRequest.addMeal(new Meal(1133));
    mealRequest.addMeal(new Meal(1133));
    mealRequest.addMeal(new Meal(1133));
    mealRequest.addMeal(new Meal(1138));
    mealRequest.addMeal(new Meal(1138));
    mealRequest.addMeal(new Meal(1138));
    mealRequest.addMeal(new Meal(1138));
    mealRequest.addMeal(new Meal(1138));
    mealRequest.removeMeal(1138);

    mealRequest.uploadRequestToDatabase();

    FlowerRequest flowerRequest = new FlowerRequest(12, "HELLOStaff", "o", "thisisaroom", date2);
    // System.out.println(flowerRequest.getQuantity(2, 1011));

    flowerRequest.addFlower(new Flower(1000));
    flowerRequest.addFlower(new Flower(1001));
    flowerRequest.removeFlower(1000);
    flowerRequest.removeFlower(1000);
    flowerRequest.removeFlower(1000);
    // 1000 - 0, 1001 - 1
    flowerRequest.addFlower(new Flower(1007));
    flowerRequest.addFlower(new Flower(1030));
    flowerRequest.addFlower(new Flower(1030));
    flowerRequest.addFlower(new Flower(1030));
    flowerRequest.addFlower(new Flower(1030));
    flowerRequest.removeFlower(1007);

    flowerRequest.addFlower(new Flower(1010));
    flowerRequest.addFlower(new Flower(1010));
    flowerRequest.addFlower(new Flower(1010));
    flowerRequest.removeFlower(1010);
    flowerRequest.removeFlower(1010);

    flowerRequest.addFlower(new Flower(1038));
    flowerRequest.addFlower(new Flower(1038));
    flowerRequest.addFlower(new Flower(1038));
    flowerRequest.removeFlower(1038);

    System.out.println(ServiceRequest.getAllServiceRequests());

    System.out.println(ServiceRequest.getAllItemsOrdered());

    // ArrayList<Integer> fid = flowerRequest.getAllFlowerIDs();
    // ArrayList<String> fname = flowerRequest.getAllFlowerNames();
    /*for (int i = 0; i < fid.size(); i++) {
      System.out.println(fid.get(i) + " " + fname.get(i));
    }

    ArrayList<Integer> mid = mealRequest.getAllMealIDs();
    ArrayList<String> mname = mealRequest.getAllMealNames();
    for (int i = 0; i < mid.size(); i++) {
      System.out.println(mid.get(i) + " " + mname.get(i));
    }*/

    // flowerRequest.uploadRequestToDatabase();
    mealRequest.uploadRequestToDatabase();

    flowerRequest.uploadRequestToDatabase();
  }
}
