package christmas.controller;

import christmas.domain.MenuRepository;
import christmas.domain.Order;
import christmas.dto.EventInfoDTO;
import christmas.dto.OrderInfoDTO;
import christmas.service.ChristmasService;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;

public class ChristmasController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final MenuRepository menuRepository = new MenuRepository();
    private final ChristmasService christmasService = new ChristmasService(menuRepository);

    public void run() {
        christmasService.init();
        int date = getDate();
        List<OrderInfoDTO> orderInfos = getOrderInfoDTOS();
        outputView.printRequestResultMessage(date);
        Order.init(date);
        EventInfoDTO eventInfoDTO = christmasService.getEventInfo(date, orderInfos);
        printResult(eventInfoDTO);
    }

    private void printResult(EventInfoDTO eventInfoDTO) {
        outputView.printOrderMenus(eventInfoDTO.orderInfos());
        outputView.printTotalPriceWithoutDiscount(eventInfoDTO.totalPrice());
        outputView.printGift(eventInfoDTO.giftInfo());
        outputView.printEventList(eventInfoDTO.eventInfos());
        outputView.printTotalDiscountPrice(eventInfoDTO.totalDiscountAmount());
        outputView.printResultPrice(eventInfoDTO.resultPrice());
        outputView.printEventBadge(eventInfoDTO.eventBadge());
    }

    private List<OrderInfoDTO> getOrderInfoDTOS() {
        while(true) {
            try {
                outputView.printRequestOrder();
                List<OrderInfoDTO> orderInfos = inputView.inputOrders();
                return orderInfos;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private int getDate() {
        while(true) {
            try {
                outputView.printMain();
                outputView.printRequestVisitDate();
                int date = inputView.inputDate();
                return date;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }
}
