package christmas.domain;

import christmas.dto.EventInfoDTO;

import java.util.*;

public class Order {
    private static final Map<Menu, Integer> orders = new HashMap<>();
    private static final Map<Event, Integer> events = new EnumMap<>(Event.class);
    private static int orderDate;
    private static int totalPrice = 0;
    private static int totalDiscountPrice = 0;

    public static void init(int orderDate) {
        Order.orderDate = orderDate;
        for(Event event : Event.values()) {
            events.put(event, 0);
        }
    }

    public static void addOrder(Menu menu) {
        if(orders.get(menu) != null) {
            orders.put(menu, orders.get(menu) + 1);
            return;
        }
        orders.put(menu, 1);
        totalPrice += menu.getPrice();
    }

    public static void addEvent(Event event) {
        if(event == Event.DDAY && orderDate > 25) return;
        if(events.get(event) != null) {
            events.put(event, events.get(event) + 1);
            return;
        }
        totalDiscountPrice += Event.getDiscountPrice(event, orderDate, getMainMenuCount(), getDessertCount());
    }

    public static int getTotalPrice() {
        return totalPrice;
    }

    public static int getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

    private static int getMainMenuCount() {
        int count = 0;
        for(Menu menu : orders.keySet()) {
            if(menu.isMainMenu()) count += orders.get(menu);
        }
        return count;
    }

    private static int getDessertCount() {
        int count = 0;
        for(Menu menu : orders.keySet()) {
            if(menu.isDessert()) count += orders.get(menu);
        }
        return count;
    }

    public static List<EventInfoDTO.EventInfo> getEventAndDiscountPrice() {
        List<EventInfoDTO.EventInfo> eventInfos = new ArrayList<>();
        for(Event event : events.keySet()) {
            int discountPrice = Event.getDiscountPrice(event, orderDate, getMainMenuCount(), getDessertCount());
            if(events.get(event) != 0 && discountPrice > 0) {
                eventInfos.add(new EventInfoDTO.EventInfo(event.getTitle(), discountPrice));
            }
        }
        return eventInfos;
    }
}
