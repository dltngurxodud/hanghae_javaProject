package report7;



public class Report7_5 {


    class Exercise6_20 {
        public static int max(int[] arr) {
            if (arr == null || arr.length == 0) { // 배열이 null이거나 크기가 0인 경우
                return -999999; // 특정 값을 반환
            }
            int max = arr[0]; // 초기값 설정
            for (int i = 1; i < arr.length; i++) { // 배열을 순회하면서
                if (max < arr[i]) { // 배열의 현재 요소가 최대값보다 크다면
                    max = arr[i]; // 최대값을 현재 요소로 업데이트
                }
            }
            return max; // 최대값 반환
        }

        public static void main(String[] args) {
            int[] data = {3, 2, 9, 4, 7};
            System.out.println(java.util.Arrays.toString(data));
            System.out.println("최대값: " + max(data));
            System.out.println("최대값: " + max(null));
            System.out.println("최대값: " + max(new int[]{})); // 크기가 0인 배열
        }
    }
}