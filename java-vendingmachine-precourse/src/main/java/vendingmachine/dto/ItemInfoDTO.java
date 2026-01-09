package vendingmachine.dto;

public record ItemInfoDTO (
        String name,
        int price,
        int count
) {
}
