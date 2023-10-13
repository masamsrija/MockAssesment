package taskstream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import entity.Food;
 import entity.Review;
 import entity.Type;



public class BusinessOperation {



    List<Food> allFoods;



    public BusinessOperation() {
        allFoods = new ArrayList<>();



        List<Review> reviewList1 = Arrays.asList(
                new Review("A", 4, "Good"),
                new Review("B", 2, "Not good"),
                new Review("C", 4, "Good"));
        List<Review> reviewList2 = Arrays.asList(
                new Review("A", 5, "Good"),
                new Review("B", 1, "Not good"),
                new Review("C", 3, "Good"),
                new Review("D", 4, "very Good"),
                new Review("E", 5, "nice"));



        List<Review> reviewList3 = Arrays.asList(
                new Review("A", 4, "Good"),
                new Review("B", 1, "Not good"));



        allFoods.add(new Food("Dal Rice", 150, Type.VEG, "Indian", reviewList1));
        allFoods.add(new Food("Veg Noodles", 200, Type.VEG, "Asian", reviewList2));
        allFoods.add(new Food("Mix Veg", 150, Type.VEG, "Indian", reviewList2));
        allFoods.add(new Food("Veg Soup", 120, Type.VEG, "Europe", reviewList3));
        allFoods.add(new Food("Tandori Bread", 50, Type.VEG, "Middle East", reviewList3));



        allFoods.add(new Food("Chicken Tikka", 320, Type.NON_VEG, "Indian", reviewList2));
        allFoods.add(new Food("Chicken Biryani", 420, Type.NON_VEG, "Indian", reviewList2));
        allFoods.add(new Food("Fish Curry", 480, Type.NON_VEG, "Indian", reviewList1));
        allFoods.add(new Food("Chicken Pasta", 320, Type.NON_VEG, "Italian", reviewList3));
    }



    public int avgCostByCategory(Type category) {
        return (int) allFoods.stream()
                .filter(food -> food.getCategory() == category)
                .mapToInt(Food::getCost)
                .average()
                .orElse(0);
    }



    public List<Food> increaseCostOfFoodByRegion(String filterRegion) {
        return allFoods.stream()
                .filter(food -> food.getRegion().equals(filterRegion))
                .peek(food -> food.setCost((int) (food.getCost() * 1.2))) // Increase the cost by 20%
                .collect(Collectors.toList());
    }



    public Map<String, Integer> getFoodDetails() {
        return allFoods.stream()
                .collect(Collectors.toMap(Food::getFoodItemName, food -> food.getAllReviews().size()));
    }





}


