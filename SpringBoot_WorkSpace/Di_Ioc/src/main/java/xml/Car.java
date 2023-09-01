package xml;

/**
 * 자동차를 나타내는 클래스
 */
public class Car {
    private String name; // 자동차 이름

    public Car(String name) { // 자동차 생성자
        this.name = name;
    }

    public String getName() { // 자동차 이름 반환
        return name;
    }

    public void setName(String name) { // 자동차 이름 설정
        this.name = name;
    }

}
