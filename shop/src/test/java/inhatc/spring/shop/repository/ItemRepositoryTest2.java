package inhatc.spring.shop.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import inhatc.spring.shop.constant.ItemSellStatus;
import inhatc.spring.shop.entity.Item;
import inhatc.spring.shop.entity.QItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static inhatc.spring.shop.entity.QItem.*; // QueryDSL - QItem 스태틱 임포트
import static org.junit.jupiter.api.Assertions.*; // JUnit5 - Assertions 스태틱 임포트

@SpringBootTest
@Transactional
class ItemRepositoryTest2 {

    private final ItemRepository itemRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public ItemRepositoryTest2(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @BeforeEach
    public void init() {
        for (int i = 1; i < 101; i++) {
            Item item = Item.builder()
                    .itemNm("테스트 상품" + i)
                    .price(10000 + i)
                    .stockNumber(100 + i)
                    .itemDetail("테스트 상품 상세 설명" + i)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .build();

            itemRepository.save(item);

        }
    }

    /**

     재고량이 160개 이상이고 상품명에 5가 포함되는 상품을 조회하는 테스트 코드를 작성

     나올 수 있는 결과

     Item(id=604, itemNm=테스트 상품65, price=10065, stockNumber=165, itemDetail=테스트 상품 상세 설명65, itemSellStatus=SELL, regTime=2023-09-22T23:45:34.425673, updateTime=null)
     Item(id=614, itemNm=테스트 상품75, price=10075, stockNumber=175, itemDetail=테스트 상품 상세 설명75, itemSellStatus=SELL, regTime=2023-09-22T23:45:34.450592, updateTime=null)
     Item(id=624, itemNm=테스트 상품85, price=10085, stockNumber=185, itemDetail=테스트 상품 상세 설명85, itemSellStatus=SELL, regTime=2023-09-22T23:45:34.472758, updateTime=null)
     Item(id=634, itemNm=테스트 상품95, price=10095, stockNumber=195, itemDetail=테스트 상품 상세 설명95, itemSellStatus=SELL, regTime=2023-09-22T23:45:34.488530, updateTime=null)

     */

    @Test
    @DisplayName("조건 : 재고량 -> 160개 이상 / 상품명 -> 5 포함" +
                 " + JPA")
    void findByStockNumberGreaterThanEqualAndItemNmContaining() {

        int stockNumber = 160;
        String itemNm = "5";

        itemRepository.findByStockNumberGreaterThanEqualAndItemNmContaining(stockNumber, itemNm)
                .forEach(System.out::println);

    }

    @Test
    @DisplayName("조건 : 재고량 -> 160개 이상 / 상품명 -> 5 포함" +
                 " + JPQL")
    void findByStockNumberAndItemNmQuery() {

            int stockNumber = 160;
            String itemNm = "5";

            itemRepository.findByStockNumberAndItemNmQuery(stockNumber, itemNm)
                    .forEach(System.out::println);
    }

    @Test
    @DisplayName("조건 : 재고량 -> 160개 이상 / 상품명 -> 5 포함" +
                 " + Native Query")
    void findByStockNumberAndItemNmQueryNative() {

            int stockNumber = 160;
            String itemNm = "5";

            itemRepository.findByStockNumberAndItemNmQueryNative(stockNumber, itemNm)
                    .forEach(System.out::println);
    }

    @Test
    @DisplayName("조건 : 재고량 -> 160개 이상 / 상품명 -> 5 포함" +
                 " + QueryDSL")
    void findByStockNumberAndItemNmQueryDSL() {

            int stockNumber = 160;
            String itemNm = "5";

            JPAQueryFactory queryFactory = new JPAQueryFactory(em);
            QItem qItem = item;

            queryFactory.selectFrom(qItem)
                    .where(qItem.stockNumber.goe(stockNumber)
                            .and(qItem.itemNm.contains(itemNm)))
                    .fetch()
                    .forEach(System.out::println);
    }

}