package christmas.dto;

import java.util.List;

public record EventInfoDTO(
        List<OrderInfoDTO> orderInfos,
        int totalPrice,
        OrderInfoDTO giftInfo,
        List<EventInfo> eventInfos,
        int totalDiscountAmount,
        int resultPrice,
        String eventBadge
) {
    public record EventInfo(
            String title,
            int discountPrice
    ){}
}
