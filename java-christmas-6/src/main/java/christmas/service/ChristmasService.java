package christmas.service;

import christmas.Constants;
import christmas.domain.Event;
import christmas.domain.Menu;
import christmas.domain.MenuRepository;
import christmas.domain.Order;
import christmas.dto.EventInfoDTO;
import christmas.dto.OrderInfoDTO;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class ChristmasService {
    private final MenuRepository menuRepository;

    public ChristmasService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public void init() {
        menuRepository.addMenu(new Menu("양송이수프", 6000, false, false));
        menuRepository.addMenu(new Menu("타파스", 5500, false, false));
        menuRepository.addMenu(new Menu("시저샐러드", 8000, false, false));
        menuRepository.addMenu(new Menu("티본스테이크",55000,true,false));
        menuRepository.addMenu(new Menu("바비큐립", 54000, true, false));
        menuRepository.addMenu(new Menu("해산물파스타", 35000, true,false));
        menuRepository.addMenu(new Menu("크리스마스파스타",25000,true,false));
        menuRepository.addMenu(new Menu("초코케이크",15000,false,true));
        menuRepository.addMenu(new Menu("아이스크림", 5000,false,true));
        menuRepository.addMenu(new Menu("제로콜라", 3000, false,false));
        menuRepository.addMenu(new Menu("레드와인", 60000, false,false));
        menuRepository.addMenu(new Menu("샴페인", 25000,false,false));
    }

    public EventInfoDTO getEventInfo(int date, List<OrderInfoDTO> orderInfos) {
        order(date, orderInfos);
        applyEvent(date);
        return new EventInfoDTO(
                orderInfos,
                Order.getTotalPrice(),
                findGiftInfo(),
                findEventInfos(),
                Order.getTotalDiscountPrice(),
                Order.getTotalPrice() - Order.getTotalDiscountPrice(),
                getEventBadge()
        );
    }

    private void applyEvent(int date) {
        String day = LocalDate.of(2023,12,date).getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN);
        if(date <= 25) Order.addEvent(Event.DDAY);
        if(Constants.weekdays.contains(day)) Order.addEvent(Event.WEEK);
        if(Constants.weekends.contains(day)) Order.addEvent(Event.WEEKEND);
        if(day.equals("일") || date == 25) Order.addEvent(Event.SPECIAL);
        if(Order.getTotalPrice() >= 120000) Order.addEvent(Event.GIFT);
    }

    private String getEventBadge() {
        int totalDiscountPrice = Order.getTotalDiscountPrice();
        if(totalDiscountPrice >= 20000) return "산다";
        if(totalDiscountPrice >= 10000) return "트리";
        if(totalDiscountPrice >= 5000) return "별";
        return null;
    }

    private OrderInfoDTO findGiftInfo() {
        if(Order.getTotalPrice() >= 120000) return new OrderInfoDTO("샴페인", 1);
        return null;
    }

    private List<EventInfoDTO.EventInfo> findEventInfos() {
        return Order.getEventAndDiscountPrice();
    }

    private void order(int date, List<OrderInfoDTO> orderInfos) {
        for(OrderInfoDTO orderInfoDTO : orderInfos) {
            String name = orderInfoDTO.name();
            int count = orderInfoDTO.count();
            for(int i=0; i<count; i++) Order.addOrder(getMenuByName(name));
        }
    }

    private Menu getMenuByName(String name) {
        return menuRepository.findByname(name).orElseThrow(
                () -> new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
        );
    }
}
