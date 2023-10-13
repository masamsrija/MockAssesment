package task.collection;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.Collections;

import java.util.Comparator;

import java.util.HashMap;

import java.util.List;

import java.util.Map;

import java.util.Set;

import java.util.stream.Collectors;

import entity.Food;

import entity.Review;

import entity.Type;

public class BusinessCollectionOperation {

	List<Food> allFoods;

	public BusinessCollectionOperation() {

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

		allFoods.add(new Food("Hamas", 250, Type.VEG, "Middle East", reviewList3));

		allFoods.add(new Food("Farm House Pizza", 550, Type.VEG, "Italian", reviewList3));

		allFoods.add(new Food("Fresh Fries", 550, Type.VEG, "Europe", reviewList3));

		allFoods.add(new Food("Chicken Tikka", 320, Type.NON_VEG, "Indian", reviewList2));

		allFoods.add(new Food("Chicken Biryani", 420, Type.NON_VEG, "Indian", reviewList2));

		allFoods.add(new Food("Fish Curry", 480, Type.NON_VEG, "Indian", reviewList1));

		allFoods.add(new Food("Chicken Pasta", 320, Type.NON_VEG, "Italian", reviewList3));

		allFoods.add(new Food("Chicken Pizza", 820, Type.NON_VEG, "Italian", reviewList1));

		allFoods.add(new Food("Chicken - XYZ", 820, Type.NON_VEG, "Middle East", reviewList2));

	}

	/*
	 * 
	 * method is used to sort the food items based on food type
	 * 
	 * food has higher cost, should be the first item in the list
	 * 
	 */

	public List<Food> getFoodByPriceHighToLow(Type foodType)

	{

		List<Food> foodbasedOnType = new ArrayList<>();

		for (Food food : allFoods) {

			if (food.getCategory().equals(foodType)) {

				foodbasedOnType.add(food);

			}

		}

		Collections.sort(foodbasedOnType, new Comparator<Food>() {

			@Override

			public int compare(Food food1, Food food2) {

				return food2.getCost() - food1.getCost();

			}

		});

		return foodbasedOnType;

	}

	/*
	 * 
	 * 
	 * 
	 * method will return the average review sorted by FoodName
	 * 
	 * 
	 * 
	 * filtered by region
	 * 
	 * 
	 * 
	 * String : food item name
	 * 
	 * 
	 * 
	 * Float : average review raitings
	 * 
	 * 
	 * 
	 */

	public Map<String, Float> getFoodByAvgReview(String region)

	{

		Map<String, Float> avgReview = new HashMap<>();

		for (Food food : allFoods) {

			if (region == null || food.getRegion().equalsIgnoreCase(region)) {

				String foodName = food.getFoodItemName();

				List<Review> allreviews = food.getAllReviews();

				if (!allreviews.isEmpty()) {

					float totalRating = 0;

					for (Review review : allreviews) {

						totalRating += review.getRating();

					}

					float avgRating = (float) totalRating / allreviews.size();

					avgReview.put(foodName, avgRating);

				}

			}

		}

		avgReview = avgReview.entrySet().stream()

				.sorted((entry1, entry2) -> entry1.getKey().compareTo(entry2.getKey()))

				.collect(HashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()),
						(m1, m2) -> m1.putAll(m2));

		return avgReview;

	}

	/*
	 * 
	 * 
	 * 
	 * method shouldreturn a Map<Type,Map<String, List<Food>>>
	 * 
	 * 
	 * 
	 * where Type is Food type enum for Veg and Non Veg
	 * 
	 * 
	 * 
	 * String will hold the value of region
	 * 
	 * 
	 * 
	 * which consist all food items based on region
	 * 
	 * 
	 * 
	 */

	public Map<Type, Map<String, List<Food>>> groupFoodBasedOnTypeAndRegion()

	{

		Map<Type, Map<String, List<Food>>> groupedMap = new HashMap<>();

		for (Type type : Type.values()) {

			groupedMap.put(type, new HashMap<>());

		}

		for (Food food : allFoods) {

			Type type = food.getCategory();

			String region = food.getRegion();

			if (!groupedMap.get(type).containsKey(region)) {

				groupedMap.get(type).put(region, new ArrayList<>());

			}

			groupedMap.get(type).get(region).add(food);

		}

		return groupedMap;

	}
}