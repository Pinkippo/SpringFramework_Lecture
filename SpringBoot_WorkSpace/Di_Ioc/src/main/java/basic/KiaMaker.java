package basic;

/**
 * 기아 자동차를 나타내는 클래스
 */
public class KiaMaker {

        // 자동차를 판매하는 메소드
        public Car sell(Money money) {
            System.out.println(" 기아 자동차 (입금) : " + money.getAmount() + "원");
            return new Car("K5");
        }

}
