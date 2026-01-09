package menu.domain;

import java.util.*;

public class Coach {
    private final String name;
    public final Set<Food> ignoreFood = new HashSet<>();
    public EnumMap<Day,Food> eatenFoods = new EnumMap<>(Day.class);

    public Coach(String name) {
        this.name = name;
    }

    public void addIgnoreFood(Food food) {
        ignoreFood.add(food);
    }

    public boolean isIgnoreFood(Food food) {
        return ignoreFood.contains(food);
    }

    public void addFoodPerDay(Day day, Food food) {
        eatenFoods.put(day, food);
    }

    public boolean isAlreadyEat(Food food) {
        for(Day day : Day.values()) {
            if(eatenFoods.get(day) != null && eatenFoods.get(day).getName().equals(food.getName())) return true;
        }
        return false;
    }

    public boolean isMoreThanTwiceCategory(Food food) {
        int count = 0;
        for(Day day : Day.values()) {
            if(eatenFoods.get(day).getCategory().equals(food.getCategory())) count++;
        }
        return count >= 2;
    }

    public String getName() {
        return name;
    }

    public void update(Coach coach) {
        this.eatenFoods = coach.eatenFoods;
    }

    public Map<Day, Food> getEatenFoods() {
        return Collections.unmodifiableMap(eatenFoods);
    }
}
