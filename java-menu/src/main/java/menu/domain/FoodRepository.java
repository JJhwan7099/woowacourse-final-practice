package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;
import menu.constants.Error;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FoodRepository {
    private final List<Food> foods = new ArrayList<>();

    public void add(Food food) {
        foods.add(food);
    }

    public List<Food> getFoods() {
        return Collections.unmodifiableList(foods);
    }

    public Food findFoodByName(String name) {
        return foods.stream()
                .filter(food -> food.getName().equals(name))
                .findFirst().orElseThrow(
                        () -> new IllegalArgumentException(Error.FOOD_NOT_FOUND.getMessage())
                );
    }

    public boolean isExist(String name) {
        return foods.stream()
                .anyMatch(food -> food.getName().equals(name));
    }

    public String getRandomMenuName() {
        return Randoms.shuffle(new ArrayList<>(foods)).get(0).getName();
    }

    public String getRandomMenuNameByCategory(Category category) {
        List<String> foodsPerCategory = foods.stream()
                .filter(food -> food.getCategory().equals(category))
                .map(food -> food.getName())
                .toList();
        return Randoms.shuffle(foodsPerCategory).get(0);
    }
}
