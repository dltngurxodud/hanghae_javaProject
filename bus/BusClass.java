package bus;

public class BusClass {
    public static void main(String[] args) {
        Bus bus1 = new Bus(30,100,"11");
        Bus bus2 = new Bus(45, 100, "33");

        bus1.human(2); //탑승 승객
        // 잔여 승객 수
        // 요금 확인

        bus1.setFuel(0); // 주유량 확인

        bus1.stopIsDRiving(); //차고지

        bus1.goIsDriving(); // 버스 출발

        bus1.setFuel(10); // 주유 +10

        bus1.human(45); // 승객 초과

        bus1.stopIsDRiving(); //차고지

    }
}