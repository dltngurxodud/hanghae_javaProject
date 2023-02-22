package report5;

public class Report5_1 {

    public class Student {
        private String name;  // 학생 이름
        private int ban;      // 반
        private int no;       // 번호
        private int kor;      // 국어 점수
        private int eng;      // 영어 점수
        private int math;     // 수학 점수

        // 생성자
        public Student(String name, int ban, int no, int kor, int eng, int math) {
            this.name = name;
            this.ban = ban;
            this.no = no;
            this.kor = kor;
            this.eng = eng;
            this.math = math;
        }
    }
}
