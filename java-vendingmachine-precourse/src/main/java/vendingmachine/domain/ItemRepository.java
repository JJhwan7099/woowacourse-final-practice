package vendingmachine.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ItemRepository {
    private final List<Item> items = new ArrayList<>();

    public void add(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public boolean isExist(String itemName) {
        return items.stream()
                .anyMatch(i -> i.getName().equals(itemName));
    }

    public int findItemPriceByItemName(String itemName) {
        return items.stream()
                .filter(item -> item.getName().equals(itemName))
                .map(item -> item.getPrice()).findFirst().orElseThrow(
                        () -> new IllegalArgumentException("[ERROR] 상품이 존재하지 않습니다.")
                );
    }

    public void removeItemByItemName(String itemName) {
        for(int i=0; i<items.size(); i++) {
            if(items.get(i).getName().equals(itemName)) {
                items.remove(i);
                return;
            }
        }
    }


    public int findMinimumItemPrice() {
        Item item = items.stream()
                .sorted(Comparator.comparing(i -> i.getPrice(), Comparator.naturalOrder()))
                .findFirst().orElseThrow(
                        () -> new IllegalArgumentException("[ERROR] 상품이 존재하지 않습니다.")
                );
        return item.getPrice();
    }

    public int findItemsSize() {
        return items.size();
    }
}
