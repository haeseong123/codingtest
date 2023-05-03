package solution.implementation;

// https://school.programmers.co.kr/learn/courses/30/lessons/120924

// 등차수열 혹은 등비수열 common이 매개변수로 주어질 때,
// 마지막 원소 다음으로 올 숫자를 return 해주세요.

// [제한사항]
// 2 < common의 길이 < 1,000
// -1,000 < common의 원소 < 2,000
// common의 원소는 모두 정수입니다.
// 등차수열 혹은 등비수열이 아닌 경우는 없습니다.
// 등비수열인 경우 공비는 0이 아닌 정수입니다.

// 배열의 첫 번째 원소와 두 번째 원소의 차이가
// 두 번째 원소와 세 번째 원소의 차이와 같으면
// 등차수열이고, 아니면 등비수열입니다.
// 등차수열이라면 마지막 원소 + diff를 하면되고
// 등비수열이라면 마지막 원소 * diff를 하면 됩니다.

public class NextNumber {
    public int solution(int[] common) {
        if (common[1] - common[0] == common[2] - common[1]) {
            return common[common.length - 1] + (common[2] - common[1]);
        } else {
            return common[common.length - 1] * (common[1] / common[0]);
        }
    }
}
