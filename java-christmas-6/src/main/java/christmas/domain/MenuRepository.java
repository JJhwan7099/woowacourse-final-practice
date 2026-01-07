package christmas.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MenuRepository {
    private final List<Menu> menus = new ArrayList<>();

    public void addMenu(Menu menu) {
        menus.add(menu);
    }

    public Optional<Menu> findByname(String name) {
        return menus.stream()
                .filter(menu -> menu.getName().equals(name))
                .findFirst();
    }

    public boolean isExistMenu(String menuName) {
        return menus.stream()
                .anyMatch(menu -> menu.getName().equals(menuName));
    }
}
