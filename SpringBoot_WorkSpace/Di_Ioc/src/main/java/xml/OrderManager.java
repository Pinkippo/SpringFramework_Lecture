package xml;

/**
 * 자동차 주문을 관리하는 주문 매니저 클래스
 */
public class OrderManager {
    private CarMaker maker; // 현대 자동차

    public OrderManager() { // 생성자
        this.maker = new HyundaiMaker();
    }

    public OrderManager(CarMaker maker) { // 생성자
        this.maker = maker;
    }

    public void order(int cost) { // 주문
        Money money = new Money(cost); // 돈 생성
        Car car = maker.sell(money); // 현대 자동차 판매
        System.out.println(" 판매상 (인수) 차 : " + car.getName());
    }

    public void setMaker(CarMaker maker) {
        this.maker = maker;
    }
}
