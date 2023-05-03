package solution.implementation;

// https://school.programmers.co.kr/learn/courses/30/lessons/120876?language=java

// 선분 3개가 평행하게 놓여 있습니다.
// 세 선분의 시작과 끝 좌표가 [[start, end], [start, end], [start, end]]
// 형태로 들어있는 2차원 배열 lines가 매개변수로 주어질 때,
// 두 개 이상의 선분이 겹치는 부분의 길이를 return 해주세요.

// [제한사항]
// lines의 길이 = 3
// lines의 원소의 길이 = 2
// 모든 선분은 길이가 1 이상입니다.
// lines의 원소는 [a, b] 형태이며 a, b는 각각 선분의 양 끝점입니다.
//      -100 <= a < b <= 100

// 입출력 예
// lines = [[0, 1], [2, 5], [3, 9]]
// result = 2
// 두 번째, 세 번째 선분 [2, 5], [3, 9]가
// [3, 5] 구간에 겹쳐있으므로 2를 return 합니다.

// 1. 좌표의 최소가 -100이고, 최대가 100이므로
//      int[201] 크기의 배열을 선언합니다.
// 2. lines를 순회하며 해당 배열의 값을 +1 합니다.
// 3. int[] 배열을 순회하며 크기가 2 이상인 것의 개수를 셉니다.
// 4. 센 값을 반환합니다.
public class LengthOfOverlappingSegments {
    public int solution(int[][] lines) {
        // 1
        int[] x = new int[201];
        // 2
        for (int[] line : lines) {
            int start = line[0] + 100;
            int end = line[1] + 100;

            for (int i = start; i < end; i++) {
                x[i] += 1;
            }
        }
        // 3
        int answer = 0;
        for (int v : x) {
            if (v >= 2) answer++;
        }
        // 4
        return answer;
    }
}
