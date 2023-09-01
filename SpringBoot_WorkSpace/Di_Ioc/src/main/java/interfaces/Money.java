package interfaces;

/**
 * 자동차를 구매할 때 돈을 나타내는 클래스
 */
public class Money {
    private int amount; // 돈의 액수

    public Money(int amount) { // 돈 생성자
        this.amount = amount;
    }

    public int getAmount() { // 돈의 액수 반환
        return amount;
    }

    public void setAmount(int amount) { // 돈의 액수 설정
        this.amount = amount;
    }

}
