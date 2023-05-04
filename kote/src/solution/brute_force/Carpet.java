package solution.brute_force;

// https://school.programmers.co.kr/learn/courses/30/lessons/42842?language=java

// Leo는 카펫을 사러 갔다가 아래 그림과
// 같이 중앙에는 노란색으로 칠해져 있고
// 테두리 1줄은 갈색으로 칠해져 있는
// 격자 모양 카펫을 봤습니다.

// Leo는 집으로 돌아와서 아까 본 카펫의
// 노란색과 갈색으로 색칠된
// 격자의 개수는 기억했지만,
// 전체 카펫의 크기는 기억하지 못했습니다.

// Leo가 본 카펫에서 갈색 격자의 수 brown,
// 노란색 격자의 수 yellow가 매개변수로 주어질 때
// 카펫의 가로, 세로 크기를 순서대로
// 배열에 담아 return 해주세요.

// 1. brown + yellow 는 넓이입니다.
//      이를 size 변수에 저장합니다.
// 2. size 변수의 약수를 찾습니다.
// 3. 약수가 (2*w + 2*h - 4 == brown) 의 조건을 만족하는지 확인합니다.
//      만족한다면 해당 {w, h}를 반환합니다.
public class Carpet {
    public int[] solution(int brown, int yellow) {
        // 1
        int size = brown + yellow;
        for (int i = 3; i < (int) (Math.sqrt(size) + 1); i++) {
            // 2
            if (size % i != 0) continue;

            int w = size / i;
            int h = i;
            // 3
            if (((2 * w) + (2 * h) - 4) == brown) return new int[]{w, h};
        }
        return new int[]{0, 0};
    }
}
