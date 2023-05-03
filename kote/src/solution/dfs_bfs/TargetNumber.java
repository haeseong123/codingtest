package solution.dfs_bfs;

// https://school.programmers.co.kr/learn/courses/30/lessons/43165

/**
 * numbers가 주어졌을 때
 * numbers를 가지고 target을 만드는
 * 방법의 수를 return해 주세요.
 * <p>
 * 단, numbers의 순서는 바꾸지 않아야 하며
 * 각 숫자를 빼거나 더할 수 있습니다.
 * <p>
 * 예를 들어 numbers = [1, 1, 1, 1, 1], target = 3 인 경우
 * -1 +1 +1 +1 +1 = 3
 * +1 -1 +1 +1 +1 = 3
 * +1 +1 -1 +1 +1 = 3
 * +1 +1 +1 -1 +1 = 3
 * +1 +1 +1 +1 -1 = 3
 * 주어진 numbers를 가지고 target을 만드는 경우는 총 5가지 이므로
 * 5를 return 합니다.
 * <p>
 * <문제 풀이>
 * numbers를 순회하며
 * 각 자리의 숫자들을 더하거나/빼거나 하는 모든 "순열"을 구하고
 * 전체 값이 target이 되는 경우의 수를 count해서
 * return 하면 됩니다.
 * <p>
 * 각 숫자를 더하거나/빼는 동작을 구현하는 것이
 * 해당 문제에서 해결해야 할 주요 부분입니다.
 * 이 부분은 재귀 호출을 통해 구현하는 것이 간결합니다.
 * <p>
 * 단, numbers의 모든 원소를 사용해야 한다는 것을 인지하여야 합니다.
 * <p>
 * <의사 코드>
 * int backtrack(acc, index) {
 * if (index == numbers.length()) {
 * if (acc == target) {
 * return 1;
 * } else {
 * return 0;
 * }
 * }
 * <p>
 * return backtrack(acc + numbers[index], index + 1) + backtrack(acc - numbers[index], index + 1)
 * }
 * <p>
 * <시간 복잡도, 공간 복잡도>
 * 시간 복잡도: numbers 원소 하나 당 더하거나 빼는 총 두 번의 경우를 나누어서 생각하므로
 * O(2^n)
 * 공간 복잡도: numbers를 저장할 공간이 필요하므로 O(n)
 * <p>
 * 시간 복잡도가 O(2^n)이므로, numbers의 길이가 길거나
 * 주어지는 시간이 짧은 경우 적합한 풀이가 아닐 수 있습니다.
 *
 * <배운 것>
 *     자바에서는 함수 안에 함수를 선언할 수 없다.
 *
 *     배열을 선언하려면 int[] x = {}; 식으로 즉시 만들거나
 *     int[] x = null;
 *     x = new int[3];
 *     for(i = 0; i < x.length; i++) {
 *         x[i] = i;
 *     } 식으로 만들어야 한다.
 *
 *     순열의 경우 모든 순열을 고려하지 않는다면 for 문을 돌리지 않아도 된다.
 *     예를 들어, P(n, 1), P(n, 2), ..., P(n, n) 인 경우 for 문을 돌려야하지만,
 *     P(n, n)만 하는 경우 for문을 돌리지 않아도 된다.
 *     이것이 당연한 이유가 for문을 돌리는 이유가 애초에 모든 경우의 순열을 구하기 위해서이기 때문이다.
 */

public class TargetNumber {
    private int backtack(int[] numbers, int target, int acc, int index) {
        if (index == numbers.length) {
            return acc == target ? 1 : 0;
        }

        return backtack(numbers, target, acc + numbers[index], index + 1) +
                backtack(numbers, target, acc - numbers[index], index + 1);
    }

    public int solution(int[] numbers, int target) {
        return backtack(numbers, target, 0, 0);
    }
}
