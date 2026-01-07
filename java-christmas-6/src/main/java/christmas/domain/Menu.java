package christmas.domain;

public class Menu {
    private final String name;
    private final int price;
    private final boolean isMainMenu;
    private final boolean isDessert;

    public Menu(String name, int price, boolean isMainMenu, boolean isDessert) {
        this.name = name;
        this.price = price;
        this.isMainMenu = isMainMenu;
        this.isDessert = isDessert;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean isMainMenu() {
        return isMainMenu;
    }

    public boolean isDessert() {
        return isDessert;
    }
}
