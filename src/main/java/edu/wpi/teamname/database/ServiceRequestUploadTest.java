package edu.wpi.teamname.database;

import edu.wpi.teamname.servicerequests.FlowerRequest;
import edu.wpi.teamname.servicerequests.MealRequest;
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
    mealRequest.addMeal(new Meal(1138));
    mealRequest.removeMeal(1101);

    // mealRequest.uploadRequestToDatabase();

    FlowerRequest flowerRequest = new FlowerRequest(2, "ImStaff", "o", "thisisaroom", date2);
    flowerRequest.addFlower(new Flower(1000));
    flowerRequest.addFlower(new Flower(1010));
    flowerRequest.addFlower(new Flower(1001));
    flowerRequest.removeFlower(1010);

    ArrayList<Integer> fid = flowerRequest.getAllFlowerIDs();
    ArrayList<String> fname = flowerRequest.getAllFlowerNames();
    for (int i = 0; i < fid.size(); i++) {
      System.out.println(fid.get(i) + " " + fname.get(i));
    }

    ArrayList<Integer> mid = mealRequest.getAllMealIDs();
    ArrayList<String> mname = mealRequest.getAllMealNames();
    for (int i = 0; i < mid.size(); i++) {
      System.out.println(mid.get(i) + " " + mname.get(i));
    }

    // flowerRequest.uploadRequestToDatabase();
  }
}
