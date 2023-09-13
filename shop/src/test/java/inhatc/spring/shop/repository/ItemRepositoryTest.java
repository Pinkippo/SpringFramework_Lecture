package inhatc.spring.shop.repository;

import inhatc.spring.shop.constant.ItemSellStatus;
import inhatc.spring.shop.entity.Item;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 스프링 컨테이너를 이용한 테스트
@Transactional // 테스트가 끝나면 롤백
class ItemRepositoryTest {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemRepositoryTest(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Test
    @DisplayName("상품 생성 테스트")
    public void test() {
        Item item = Item.builder()
                .itemNm("테스트 상품")
                .price(10000)
                .stockNumber(100)
                .itemDetail("테스트 상품 상세 설명")
                .itemSellStatus(ItemSellStatus.SELL)
                .build();

        System.out.println("item = " + item);

        Item savedItem = itemRepository.save(item);

        System.out.println("savedItem = " + savedItem);

    }

}