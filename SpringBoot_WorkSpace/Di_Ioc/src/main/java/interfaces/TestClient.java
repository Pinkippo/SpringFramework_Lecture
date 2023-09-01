package interfaces;

public class TestClient {
    public static void main(String[] args) {
        OrderManager manager = new OrderManager(); // 주문 매니저 생성
        manager.setMaker(new KiaMaker()); // 기아 자동차 설정
        manager.order(20000); // 주문

    }
}
