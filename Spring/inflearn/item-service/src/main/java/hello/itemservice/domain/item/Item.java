package hello.itemservice.domain.item;

import lombok.Data;

/**
 * @Data
 *
 * 핵심 도메인모델에 사용하게 되면 예측하지 못하게 동작할 위험이 있음 (주의)
 *      -> @ToString 의 경우 객체의 양방향 연관관계 (a -> b, b -> a)가 있을 때 무한 루프로 호출될 수 있음
 *      -> Equals 와 hashCode 도 모든 필드를 다 기준으로 잡기 때문에 주의
 */
@Data
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
