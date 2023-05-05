package solution.implementation;

// https://school.programmers.co.kr/learn/courses/30/lessons/150369?language=java#

// 당신은 일렬로 나열된 n개의 집에 택배를 배달하려 합니다.
// 배달할 물건은 모두 크기가 같은 재활용 택배 상자에 담아 배달하며,
// 배달을 다니면서 빈 재활용 택배 상자들을 수거하려 합니다.
// 배달할 택배들은 모두 재활용 택배 상자에 담겨서 물류창고에 보관되어 있고,
// i번째 집은 물류창고에서 거리 i만큼 떨어져 있습니다.
// 또한 i번째 집은 j번째 집과 거리 j - i만큼 떨어져 있습니다. (1 ≤ i ≤ j ≤ n)
// 트럭에는 재활용 택배 상자를 최대 cap개 실을 수 있습니다.
// 트럭은 배달할 재활용 택배 상자들을 실어 물류창고에서 출발해 각 집에 배달하면서,
// 빈 재활용 택배 상자들을 수거해 물류창고에 내립니다.
// 각 집마다 배달할 재활용 택배 상자의 개수와 수거할 빈 재활용 택배 상자의 개수를 알고 있을 때,
// 트럭 하나로 모든 배달과 수거를 마치고 물류창고까지 돌아올 수 있는 최소 이동 거리를 구하려 합니다.
// 각 집에 배달 및 수거할 때, 원하는 개수만큼 택배를 배달 및 수거할 수 있습니다.

// cap: 실을 수 있는 상자의 최대 개수
// n: 집의 개수
// deliveries: 각 집에 배달해야 할 택배의 개수
// pickups: 각 집에서 가져와야할 택배의 개수
// answer: 제약 조건을 준수하는 최단 거리

// 갈 때 제일 멀리 가야 한다. (distance)
// 오면서 cap만큼 배달할 수 있고
//      cap만큼 수거할 수 있다.
// answer = answer + (distance * 2);
// 이것을 배달 & 수거가 모두 0일 때 까지 반복한다.
// 배달 & 수거 해야 할 집 중에 가장 먼 집의 위치를
//      d로 저장해 놓는다.

// 1. delivery를 해야할 index d와
//      pickup을 해야할 index p를 구합니다.
// 2. d 혹은 p가 -1 보다 클 때 동안 아래 3~5를 반복합니다.
// 3. answer += (long) Math.max(d + 1, p + 1) << 1 을 진행합니다.
// 4. delivery[d] 에서부터 시작하여 cap 만큼을 뺍니다.
//      cap에 대해서 delivery[d]만큼 뺍니다.
//      만약 cap이 0보다 크다면 d-- 를 하고 계속 진행합니다.
//      만약 cap이 0이라면 delivery[d]가 0이 아닐때 까지 d--를 합니다.
//      만약 cap이 0미만이라면 break 합니다.
// 5. pickups에 대해 동일하게 진행합니다.
// 6. answer를 반환합니다.

public class CourierDeliveryAndCollection {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        // 1
        long answer = 0;
        int d = -1;
        int p = -1;
        for (int i = n - 1; i > -1; i--) {
            if (deliveries[i] != 0) {
                d = i;
                break;
            }
        }
        for (int i = n - 1; i > -1; i--) {
            if (pickups[i] != 0) {
                p = i;
                break;
            }
        }
        // 2
        while (d > -1 || p > -1) {
            // 3
            answer += ((long) Math.max(d + 1, p + 1) << 1);
            int capacity = cap;
            // 4
            for (int i = d; i > -1; i--) {
                int tmp = capacity;
                capacity -= deliveries[i];
                deliveries[i] -= tmp;

                if (capacity > 0) {
                    d--;
                } else {
                    if (capacity == 0) {
                        while (d > -1 && deliveries[d] == 0) {
                            d--;
                        }
                    }
                    break;
                }
            }

            capacity = cap;
            // 5
            for (int i = p; i > -1; i--) {
                int tmp = capacity;
                capacity -= pickups[i];
                pickups[i] -= tmp;

                if (capacity > 0) {
                    p--;
                } else {
                    if (capacity == 0) {
                        while (p > -1 && pickups[p] == 0) {
                            p--;
                        }
                    }
                    break;
                }
            }
        }
        // 6
        return answer;
    }
}