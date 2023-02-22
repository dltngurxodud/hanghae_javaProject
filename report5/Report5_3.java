package report5;

class Student1 {
    String name;    // 학생 이름
    int ban;        // 반
    int no;         // 번호
    int kor;        // 국어 점수
    int eng;        // 영어 점수
    int math;       // 수학 점수

    // 6-3. getTotal 메서드 정의
    public int getTotal() {
        return kor + eng + math;
    }

    // 6-3. getAverage 메서드 정의
    public float getAverage() {
        int total = getTotal();
        return Math.round((float) total / 3 * 10) / 10.0f;
    }

    // 6-2. info 메서드 정의
    public String info() {
        return name + ", " + ban + ", " + no + ", " + kor + ", " + eng + ", " + math + ", " + getTotal() + ", " + getAverage();
    }
}

class Exercise6_3 {
    public static void main(String args[]) {
        Student1 s = new Student1();
        s.name = "홍길동";
        s.ban = 1;
        s.no = 1;
        s.kor = 100;
        s.eng = 60;
        s.math = 76;
        System.out.println("이름 : " + s.name);
        System.out.println("총점 : " + s.getTotal());
        System.out.println("평균 : " + s.getAverage());
    } // 예상 결과 : 이름 : 홍길동, 총점 : 236, 평균 : 78.7
}
