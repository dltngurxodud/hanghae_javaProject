package bus;

import java.util.Scanner;

class Bus {
    int maxPassenger; // 최대 승객수
    int currentPassenger; // 현재 승객수
    int fare; // 요금
    String busNumber; // 버스 번호
    int fuel; // 주유량
    int currentSpeed; // 현재 속도
    String isDriving; // 상태 (운행, 차고지 행)

    // 객체 생성 부분
    public Bus (int maxPassenger, int fare, String busNumber) {
        this.maxPassenger = maxPassenger;
        this.currentPassenger = 0;
        this.fare = fare;
        this.busNumber = busNumber;
        this.fuel = 50; // 초기 주유량 50
        this.currentSpeed = 0;
        this.isDriving = "운행"; // 최초 상태는 '운행'
    }

    // 운행 버스 1번 기능
    public void goIsDriving() {
        if (this.fuel < 10) {
            System.out.println("주유가 필요합니다.");
            return;
        }
        this.isDriving = "운행";
        System.out.println(this.busNumber + "번 버스 운행 시작");
    }

    // 차고지에 있는 버스 2번 기능
    public void stopIsDRiving() {
        if (this.isDriving.equals("운행")) {
            this.isDriving = "차고지 행";
            System.out.println(this.busNumber + "번 버스 차고지 행으로");
        } else {
            System.out.println(this.busNumber + "번 버스 차고지에 있음");
        }
    }

//    승객 탑승 3번기능
//    this.maxPassenger = maxPassenger;
//    this.currentPassenger = 0;
    public void human(int Passenger) {
        if (this.isDriving.equals("운행")) {
            if ( currentPassenger + Passenger <= maxPassenger) {
                System.out.println("탑승 가능합니다.");
                currentPassenger += Passenger;
                System.out.println("현재 탑승 인원 :" + currentPassenger + "명");
                System.out.println("잔여 승객 수 : " + (maxPassenger - currentPassenger) );
                System.out.println("버스 요금 : " + (Passenger * fare));
            } else {
                System.out.println("최대 승객수를 초과하여 탑승 불가");
            }
        } else {
            System.out.println("운행중이 아닙니다.");
        }
    }

    // 속도 변경
    public void speed() {
        if ( fuel < 10 ) {
            System.out.println("운행 전 주유가 필요합니다.");
            return;
        }

        if (!this.isDriving.equals("운행")) {
            System.out.println("운행중이 아닙니다.");
            return;
        }

        System.out.println("변경 할 속도를 입력해 주세요!!!");
        Scanner scanner = new Scanner(System.in);
        int changeSpeed = scanner.nextInt();

        System.out.println("변경 전 현재 속도 : " + currentSpeed);

//        if ( currentSpeed + changeSpeed >= 0 ) {
//            currentSpeed += changeSpeed;
//        } else {
//            currentSpeed = 0;
//        }

        currentSpeed = currentSpeed + changeSpeed >= 0 ? currentSpeed + changeSpeed : 0; // if - else일 경우에만 사용 가능

        System.out.println("변경 후 속도 : " + currentSpeed);
    }

    // 주유량 확인
    //this.fare = fare;
    public void setFuel (int plusFuel) {
        System.out.println("주유 전 주유량 : " + fuel );

        if ( fuel + plusFuel <= 100 ) {
            fuel += plusFuel;
            System.out.println("주유 후 주유량 : " + fuel);
        } else {
            System.out.println("주유 불가");
        }
    }

}








