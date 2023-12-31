package inhatc.spring.shop.dto;

import inhatc.spring.shop.constant.ItemSellStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
public class ItemDto {
    private Long id; // 상품 번호
    private String itemNm; // 상품 이름
    private int price; // 상품 가격
    private int stockNumber; // 상품 재고 수량
    private String itemDetail; // 상품 상세 설명
    private String itemSellStatus; // 상품 판매 상태
    private LocalDateTime regTime; // 상품 등록 시간
    private LocalDateTime updateTime; // 상품 수정 시간

}
