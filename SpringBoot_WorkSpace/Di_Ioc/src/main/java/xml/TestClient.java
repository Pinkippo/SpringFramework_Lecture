package xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestClient {
    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("car-config.xml"); // 설정 파일 로딩

        OrderManager manager = context.getBean("manager" , OrderManager.class); // Bean 을 통해서 주문 매니저 생성

        manager.order(30000); // 주문

    }
}
