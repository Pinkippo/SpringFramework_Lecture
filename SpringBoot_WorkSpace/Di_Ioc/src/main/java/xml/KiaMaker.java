package xml;

/**
 * 기아 자동차를 나타내는 클래스 - 인터페이스 구현
 */
public class KiaMaker implements CarMaker {

    // 자동차를 판매하는 메소드 - 추상 메소드
    @Override
    public Car sell(Money money) {
        System.out.println(" 기아 자동차 (입금) : " + money.getAmount() + "원");
        return new Car("K5");
    }

}
