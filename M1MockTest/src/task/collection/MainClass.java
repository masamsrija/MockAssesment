package task.collection;

import java.util.List;
import java.util.Map;

import entity.Food;

import entity.Type;

/* Change code accordingly*/

public class MainClass {

	public static void main(String[] args) {

		BusinessCollectionOperation task = new BusinessCollectionOperation();

		System.out.println(" Print Food Based on Price High To Low");

		List<Food> nonVegFoods = task.getFoodByPriceHighToLow(Type.NON_VEG);

		for (Food food : nonVegFoods) {

			System.out.println(food);

		}

		System.out.println("---------------------------------");

		System.out.println(" Print Food Based on Reviews");

		// task.getFoodByAvgReview(null);

		Map<String, Float> avgRatingsMap = task.getFoodByAvgReview("Indian");

		for (Map.Entry<String, Float> entry : avgRatingsMap.entrySet()) {

			System.out.println(entry.getKey() + " - Average Rating: " + entry.getValue());

		}

		System.out.println("---------------------------------------");

		System.out.println(" Print Food Based on Type & Region");

		Map<Type, Map<String, List<Food>>> groupedFood = task.groupFoodBasedOnTypeAndRegion();

		for (Map.Entry<Type, Map<String, List<Food>>> entry : groupedFood.entrySet()) {

			System.out.println("Food Type: " + entry.getKey());

			for (Map.Entry<String, List<Food>> regionEntry : entry.getValue().entrySet()) {

				System.out.println("Region: " + regionEntry.getKey());

				for (Food food : regionEntry.getValue()) {

					System.out.println(food);

				}

			}

		}

	}

}
