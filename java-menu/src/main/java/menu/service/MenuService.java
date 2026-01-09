package menu.service;

import menu.domain.*;
import menu.dto.MenuRecommendsInfosDTO;
import menu.util.MDFileReader;

import java.net.URISyntaxException;
import java.util.List;

public class MenuService {
    private final FoodRepository foodRepository;
    private final CoachRepository coachRepository;

    public MenuService(FoodRepository foodRepository, CoachRepository coachRepository) {
        this.foodRepository = foodRepository;
        this.coachRepository = coachRepository;
        init();
    }

    private void init() {
        for(Category category : Category.values()) {
            setFoodsPerCategory(category.toString(), category);
        }
    }

    private void setFoodsPerCategory(String categoryStr, Category category) {
        List<String> foods = MDFileReader.readFile(categoryStr.toLowerCase()+"-menu.md");
        for(String foodName: foods) foodRepository.add(new Food(foodName, category));
    }

    public void registerCoaches(List<String> coachNames) {
        for(String name: coachNames) {
            coachRepository.add(new Coach(name));
        }
    }

    public void setIgnoreFoodsPerCoach(String name, List<String> ignoreFoods) {
        Coach coach = coachRepository.findCoachByName(name);
        for(String foodName : ignoreFoods)
            coach.addIgnoreFood(foodRepository.findFoodByName(foodName));
    }

    public MenuRecommendsInfosDTO runMenuRecommend() {
        CategoryOfDay categoryOfDay = new CategoryOfDay();
        for(Day day : Day.values()) {
            while(true) {
                Category category = Category.getRandomCategory();
                if (!categoryOfDay.isCategoryMorethanTwice(category)) {
                    categoryOfDay.addCategoryOfDay(day, category);
                    for (Coach coach : coachRepository.getCoaches()) {
                        while(true) {
                            String menuName = foodRepository.getRandomMenuNameByCategory(category);
                            Food food = foodRepository.findFoodByName(menuName);
                            if (!coach.isAlreadyEat(food) && !coach.isIgnoreFood(food)) {
                                coach.addFoodPerDay(day, food);
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        }
        return MenuRecommendsInfosDTO.from(categoryOfDay, coachRepository.getCoaches());
    }
}
