package report5;



class Student{
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

    // info() 메서드
    public String info() {
        int total = kor + eng + math;  // 총점 계산
        float average = total / 3.0f;  // 평균 계산

        return name + ", " + ban + ", " + no + ", " + kor + ", " + eng + ", " + math + ", " + total + ", " + average;  // 결과 문자열 생성하여 반환
    }

}

class Report5_2{
    public static void main(String[] args){
        Student s = new Student("홍길동", 1, 1, 100, 60, 76);

        String str = s.info();
        System.out.println(str);
    }// 예상 결과 : 홍길동, 1, 1, 100, 60, 76, 236, 78.7
}
