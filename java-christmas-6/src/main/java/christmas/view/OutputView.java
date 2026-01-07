package christmas.view;

import christmas.dto.EventInfoDTO;
import christmas.dto.OrderInfoDTO;

import java.text.DecimalFormat;
import java.util.List;

public class OutputView {

    DecimalFormat df = new DecimalFormat("###,###");

    public void printMain() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printRequestVisitDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }

    public void printRequestOrder() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    }

    public void printRequestResultMessage(int date) {
        System.out.println("12월 " + date + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void printOrderMenus(List<OrderInfoDTO> orderInfoDTOS) {
        System.out.println();
        System.out.println("<주문 메뉴>");
        for(OrderInfoDTO orderInfoDTO : orderInfoDTOS) {
            System.out.println(orderInfoDTO.name() + " " + orderInfoDTO.count() + "개");
        }
    }

    public void printTotalPriceWithoutDiscount(int totalPrice) {
        System.out.println();
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(df.format(totalPrice) + "원");
    }

    public void printGift(OrderInfoDTO giftInfo) {
        System.out.println();
        System.out.println("<증정 메뉴>");
        if(giftInfo != null) {
            System.out.println(giftInfo.name() + " " + giftInfo.count());
            return;
        }
        System.out.println("없음");
    }

    public void printEventList(List<EventInfoDTO.EventInfo> eventInfos) {
        System.out.println();
        System.out.println("<혜택 내역>");
        if(eventInfos.size() > 0) {
            for(EventInfoDTO.EventInfo eventInfo : eventInfos) {
                String title = eventInfo.title();
                int price = eventInfo.discountPrice();
                System.out.println(title + ": " + df.format(-price) + "원");
            }
            return;
        }
        System.out.println("없음");
    }

    public void printTotalDiscountPrice(int totalDiscountPrice) {
        System.out.println();
        System.out.println("<총혜택 금액>");
        System.out.println(df.format(-totalDiscountPrice)+"원");
    }

    public void printResultPrice(int resultPrice) {
        System.out.println();
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(df.format(resultPrice)+"원");
    }

    public void printEventBadge(String eventBadge) {
        System.out.println();
        System.out.println("<12월 이벤트 배지>");
        if(eventBadge == null) {
            System.out.println("없음");
            return;
        }
        System.out.println(eventBadge);
    }

    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
