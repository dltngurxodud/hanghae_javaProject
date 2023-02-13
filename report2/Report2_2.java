package choiwonbin.report2;
// 3-2번 문제
public class Report2_2 {
    public static void main(String[] args) {
        int x = 2;
        int y = 5;
        char c = 'A'; // 'A'의 문자코드는 65

        System.out.println(y >= 5 || x < 0 && x > 2); // true || false && false => &&이 우선순위 먼저 실행하고  true || false 식 실행 true || false  = true
        System.out.println(y += 10 - x++); // 5 + 10 = 15 - 2 = 13
        System.out.println(x += 2); // 2 + 2 = 4
        System.out.println(!('A' <= c && c <= 'Z')); // && 연산자부터 실행 true && true = true 그다음 논리부정 ! 실행 => false
        System.out.println('C' - c); // 67 - 65 = 2
        System.out.println('5' - '0'); // 85 - 80 = 5
        System.out.println(c + 1); // 65 + 1 = 66
        System.out.println(++c); //  전위연산으로 +1 시키고 +1+65 = 'B'
        System.out.println(c++); // 후위연산 'B' + 1 = 'B'
        System.out.println(c); // C
    }
}
