package report5;

class PlayingCard {
    int kind;
    int num;
    static int width;
    static int height;


    PlayingCard(int k, int n) {
        this.kind = k;
        this.num = n;
    }

}


public class Report5_4 {
    //6-5. 다음의 코드에 정의된 변수들을 종류별(클래스 변수,인스턴스 변수, 지역변수)로 구분해서 적으세요.

        public static void main(String args[]) {
            PlayingCard card = new PlayingCard(1,1);
        }
//        클래스 변수: width, height
//        인스턴스 변수: kind, num
//        지역변수: k, n, card, args (매개변수)
    }


