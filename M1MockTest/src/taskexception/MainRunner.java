package taskexception;
import java.util.Arrays;

import entity.Food;
import entity.Review;
import entity.Type;

public class MainRunner {

    public static void main(String[] args) throws InvalidFoodRegionException {

        FoodService service = new FoodService();

        Food food1 = new Food("", 8,null,"Indian", Arrays.asList(new Review("A", 0, "Good")));

        Food food2 = new Food();
        food2.setFoodItemName("French Green Salad");
        food2.setCost(1000);
        food2.setRegion("Indian");
        food2.setAllReviews(Arrays.asList(new Review("B", 1, "Average")));

        try {
            System.out.println(service.validateFood(food2));
            System.out.println(service.validateFood(food1));
        } catch (InvalidFoodDetailsException e) {
            System.out.println("Invalid food details: " + e.getMessage());
        }
        catch (NullPointerException e) {
            System.out.println("Null pointer exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}


