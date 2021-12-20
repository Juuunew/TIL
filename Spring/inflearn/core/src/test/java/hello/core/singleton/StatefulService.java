package hello.core.singleton;


public class StatefulService {

    // 상태를 유지하는 필드
/*    private int price;

    public void order(String name, int price) {

        System.out.println("name = " + name + " price = " + price);
        this.price = price; // 문제가 되는 지점
    }*/

    // 무상태로 설계계
   public int order(String name, int price) {

        System.out.println("name = " + name + " price = " + price);
        return price;
    }

/*    public int getPrice() {
        return price;
    }*/
}
