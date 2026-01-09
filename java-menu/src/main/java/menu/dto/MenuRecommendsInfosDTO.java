package menu.dto;

import menu.domain.*;

import java.util.*;

public record MenuRecommendsInfosDTO (
        List<String> categorySequnce,
        Map<String, List<String>> menuRecommendsPerCoach
) {

    public static MenuRecommendsInfosDTO from(CategoryOfDay categoryOfDay, List<Coach> coaches) {
        Map<Day, Category> dayCategory = categoryOfDay.getDayCategory();
        List<String> categorySequence = new ArrayList<>();
        Map<String, List<String>> menuRecommendsPerCoach = new HashMap<>();
        for(Day day : Day.values()) categorySequence.add(dayCategory.get(day).getType());
        for(Coach coach : coaches) {
            Map<Day, Food> eatenFoods = coach.getEatenFoods();
            List<String> foods = new ArrayList<>();
            for(Day day : Day.values()) {
                foods.add(eatenFoods.get(day).getName());
            }
            menuRecommendsPerCoach.put(coach.getName(), foods);
        }
        return new MenuRecommendsInfosDTO(categorySequence, menuRecommendsPerCoach);
    }
}
