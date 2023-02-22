package report6;

public class Report6_9 {

    //6-16. 다음 코드의 실행 결과를 예측하여 적어주세요.
    class Exercise6_16 {
        public static void change(String str) {
            str += "456";
        }

        public static void main(String[] args) {
            String str = "ABC123";
            System.out.println(str);
            change(str);
            System.out.println("After change:" + str);
        }
    }

    // main 메서드에서 str 변수에 ABC123 문자열 저장, 출력
    // change 메서드를 호출, 객체 변경을 시도하지만 변경이 되지 않고
    // ABC123출력

}
