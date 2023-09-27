package inhatc.spring.shop.repository;

import com.querydsl.core.BooleanBuilder;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import java.util.List;

import static inhatc.spring.shop.entity.QItem.*; // QueryDSL - QItem 스태틱 임포트
import static org.junit.jupiter.api.Assertions.*; // JUnit5 - Assertions 스태틱 임포트

@SpringBootTest // 스프링 컨테이너를 이용한 테스트
@Transactional // 테스트가 끝나면 롤백
class ItemRepositoryTest {

    private final ItemRepository itemRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public ItemRepositoryTest(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


//    @BeforeEach
//    public void init() {
//        for (int i = 1; i < 11; i++) {
//            Item item = Item.builder()
//                    .itemNm("테스트 상품" + i)
//                    .price(10000 + i)
//                    .stockNumber(100 + i)
//                    .itemDetail("테스트 상품 상세 설명" + i)
//                    .itemSellStatus(ItemSellStatus.SELL)
//                    .build();
//
//            itemRepository.save(item);
//
//        }
//    }

    /*
    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNmTest() {

        for (int i = 1; i < 11; i++) {
            Item item = Item.builder()
                    .itemNm("테스트 상품" + i)
                    .price(10000 + i)
                    .stockNumber(100 + i)
                    .itemDetail("테스트 상품 상세 설명")
                    .itemSellStatus(ItemSellStatus.SELL)
                    .build();
            itemRepository.save(item);

            itemRepository.findByItemNm("테스트 상품1").
                    forEach(item1 -> {
                        System.out.println("item1 = " + item1);
                        assertEquals(item1.getItemNm(), "테스트 상품1");
                        System.out.println("테스트 성공");
                    });
        }
    }
     */

    @Test
    @DisplayName("상품명, 상품상세설명 or 테스트")
    public void findByItemNmOrItemDetailTest() {
        for (int i = 1; i < 11; i++) {
            Item item = Item.builder()
                    .itemNm("테스트 상품" + i)
                    .price(10000 + i)
                    .stockNumber(100 + i)
                    .itemDetail("테스트 상품 상세 설명")
                    .itemSellStatus(ItemSellStatus.SELL)
                    .build();
            itemRepository.save(item);
        }

        itemRepository.findByItemNmOrItemDetail("테스트 상품1", "테스트 상품 상세 설명").
                forEach((item -> {
                    System.out.println("item = " + item);
                    assertEquals(item.getItemDetail(), "테스트 상품 상세 설명");
                }));

    }


    @Test
    @DisplayName("가격 LessThan 테스트")
    public void findByPriceLessThanTest() {
        for (int i = 1; i < 11; i++) {
            Item item = Item.builder()
                    .itemNm("테스트 상품" + i)
                    .price(10000 + i)
                    .stockNumber(100 + i)
                    .itemDetail("테스트 상품 상세 설명")
                    .itemSellStatus(ItemSellStatus.SELL)
                    .build();
            itemRepository.save(item);
        }

        itemRepository.findByPriceLessThanOrderByPriceDesc(10005).
                forEach((item -> {
                    System.out.println("item = " + item);
                }));

    }

    @Test
    @DisplayName("상품 상세 조회 테스트")
    public void findByDetailTest() {

        for (int i = 1; i < 11; i++) {
            Item item = Item.builder()
                    .itemNm("테스트 상품" + i)
                    .price(10000 + i)
                    .stockNumber(100 + i)
                    .itemDetail("테스트 상품 상세 설명" + i)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .build();
            itemRepository.save(item);
        }

        itemRepository.findByDetailNative("테스트 상품 상세 설명1").
                forEach((item -> {
                    System.out.println("item = " + item);
                }));

    }

    public void creationItem(){
        for (int i = 1; i < 5; i++) {
            Item item = Item.builder()
                    .itemNm("테스트 상품" + i)
                    .price(10000 + i)
                    .stockNumber(100 + i)
                    .itemDetail("테스트 상품 상세 설명")
                    .itemSellStatus(ItemSellStatus.SELL)
                    .build();
            itemRepository.save(item);
        }

        for (int i = 5; i < 11; i++) {
            Item item = Item.builder()
                    .itemNm("테스트 상품" + i)
                    .price(10000 + i)
                    .stockNumber(100 + i)
                    .itemDetail("테스트 상품 상세 설명")
                    .itemSellStatus(ItemSellStatus.SOLD_OUT)
                    .build();
            itemRepository.save(item);
        }

    }


    @Test
    @DisplayName("QueryDSL 테스트")
    public void queryDslTest2() {
        creationItem();

        BooleanBuilder builder = new BooleanBuilder();

        String itemDetail = "테스트";
        int price = 10004;
        String itemSellStatus = "SELL";

        QItem qItem = item;

        builder.and(qItem.itemDetail.like("%" + itemDetail + "%"));
        builder.and(qItem.price.goe(price));

        if(StringUtils.equals( itemSellStatus, ItemSellStatus.SELL )){
            builder.and(qItem.itemSellStatus.eq(ItemSellStatus.SELL));
        }

        Pageable pageable = Pageable.ofSize(5).withPage(0);

        Page<Item> itemPage =  itemRepository.findAll(builder, pageable);
        List<Item> itemList = itemPage.getContent();
        itemList.stream().forEach(item -> {
            System.out.println("item = " + item);
        });

    }

    @Test
    @DisplayName("QueryDSL 테스트")
    public void queryDslTest() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QItem qItem = item;

        queryFactory.selectFrom(qItem)
                .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qItem.itemDetail.like("%" + "1" + "%"))
                .orderBy(qItem.price.desc())
                .fetch()
                .forEach((item -> {
                    System.out.println("item = " + item);
                }));

    }

}