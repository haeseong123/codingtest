package solution.binary_search;

// https://school.programmers.co.kr/learn/courses/30/lessons/43238?language=java

// n명이 입국심사를 위해 줄을 서서 기다리고 있습니다.
// 각 입국심사대에 있는 심사관마다 심사하는데 걸리는 시간은 다릅니다.

//처음에 모든 심사대는 비어있습니다.
// 한 심사대에서는 동시에 한 명만 심사를 할 수 있습니다.
// 가장 앞에 서 있는 사람은 비어 있는 심사대로 가서 심사를 받을 수 있습니다.
// 하지만 더 빨리 끝나는 심사대가 있으면 기다렸다가 그곳으로 가서 심사를 받을 수도 있습니다.

//모든 사람이 심사를 받는데 걸리는 시간을 최소로 하고 싶습니다.

//입국심사를 기다리는 사람 수 n,
// 각 심사관이 한 명을 심사하는데 걸리는 시간이 담긴 배열 times가 매개변수로 주어질 때,
// 모든 사람이 심사를 받는데 걸리는 시간의 최솟값을 return 하도록 solution 함수를 작성해주세요.

// [제한사항]
// 입국심사를 기다리는 사람은 1명 이상 1,000,000,000명 이하입니다.
// 각 심사관이 한 명을 심사하는데 걸리는 시간은 1분 이상 1,000,000,000분 이하입니다.
// 심사관은 1명 이상 100,000명 이하입니다.

// 문제 풀이
// 제한사항에서 주어지는 값의 크기가 매우 크므로 시간복잡도를 고려하며 풀어야 합니다.
// return 해야 할 값은 최소 소요 "시간" 이므로
// 시간을 기준으로 어떻게 작업을 하면 될 것 같습니다.

// "시간"을 기준으로
// 최소 소요 시간 = 0과
// 최대 소요 시간 = times.max를
// 각 left, right로 잡고
// mid 값을 구한 뒤, 해당 시간 내에 n명의 사람을 처리할 수 있는지 계산합니다.
// 만약 처리할 수 없다면 시간이 더 필요한 것이므로 left 값을 mid + 1로 바꾸고
// 처리할 수 있다면 시간이 더 부족할 때에도 처리할 수 있는지 right 값을 mid - 1로 바꿉니다.
// 이것을 (left <= right) 일때 동안 반복합니다.
import java.util.*;

public class ImmigrationInspection {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long answer = 0;
        long left = 0;
        long right = (long) n * times[times.length - 1];
        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = 0;
            for (int time : times) {
                sum += mid / time;
            }
            if (sum < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
                answer = mid;
            }
        }
        return answer;
    }
}
