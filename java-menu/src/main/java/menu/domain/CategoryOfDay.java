package menu.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class CategoryOfDay {
    private final EnumMap<Day, Category> dayCategory = new EnumMap<>(Day.class);

    public boolean isCategoryMorethanTwice(Category category) {
        int count = 0;
        for(Day day : Day.values()) {
            if(dayCategory.get(day) != null && dayCategory.get(day).equals(category)) count++;
        }
        return count >= 2;
    }

    public void addCategoryOfDay(Day day, Category category) {
        dayCategory.put(day, category);
    }

    public Map<Day, Category> getDayCategory() {
        return Collections.unmodifiableMap(dayCategory);
    }
}
