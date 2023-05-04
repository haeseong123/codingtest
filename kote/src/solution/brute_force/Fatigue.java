package solution.brute_force;

// https://school.programmers.co.kr/learn/courses/30/lessons/87946?language=java

// XX게임에는 피로도 시스템(0 이상의 정수로 표현합니다)이 있으며,
// 일정 피로도를 사용해서 던전을 탐험할 수 있습니다.
// 이때, 각 던전마다 탐험을 시작하기 위해 필요한
// "최소 필요 피로도"와 던전 탐험을 마쳤을 때
// 소모되는 "소모 피로도"가 있습니다.
// "최소 필요 피로도"는 해당 던전을 탐험하기 위해
// 가지고 있어야 하는 최소한의 피로도를 나타내며,
// "소모 피로도"는 던전을 탐험한 후 소모되는 피로도를 나타냅니다.
// 예를 들어 "최소 필요 피로도"가 80,
// "소모 피로도"가 20인 던전을 탐험하기 위해서는
// 유저의 현재 남은 피로도는 80 이상 이어야 하며,
// 던전을 탐험한 후에는 피로도 20이 소모됩니다.

// 이 게임에는 하루에 한 번씩 탐험할 수 있는 던전이 여러개 있는데,
// 한 유저가 오늘 이 던전들을 최대한 많이 탐험하려 합니다.
// 유저의 현재 피로도 k와
// 각 던전별 "최소 필요 피로도", "소모 피로도"가 담긴 2차원 배열 dungeons 가
// 매개변수로 주어질 때,
// 유저가 탐험할수 있는 최대 던전 수를 return 하도록 solution 함수를 완성해주세요.

// 순열을 사용하여 던전을 가는 모든 경우를 따져서 가장 많이 방문할 수 있을 때의
// 던전 방문 수를 반환하면 됩니다.

// 1. 모든 경우에 따른 던전 방문 수를 넣을
//      countList를 선언합니다.
// 2. 순열을 만들며 아래 사항을 수행합니다.
//      만약 순열을 모두 만들었다면 countList에 count를 추가하고 빠져나갑니다.
//      아직 방문하지 않은 dungeons을 순회하며 최소 피로도를 충족하는지 확인합ㄴ다.
//          최소 피로도를 충족하지 못하면 countList에 count를 추가하고 continue합니다.
//          최소 피로도를 충족한다면 순열을 계속 만듭니다.
// 3. countList에서 가장 큰 수를 반환합니다.

import java.util.ArrayList;
import java.util.Collections;

public class Fatigue {
    private void permutations(ArrayList<Integer> list, int k, int[][] dungeons, int size, int depth, int count, boolean[] visited) {
        if (size == depth) {
            list.add(count);
            return;
        }

        for (int i = 0; i < dungeons.length; i++) {
            if (visited[i]) continue;
            if (dungeons[i][0] > k) {
                list.add(count);
                continue;
            }
            visited[i] = true;
            permutations(list, k - dungeons[i][1], dungeons, size, depth + 1, count + 1, visited);
            visited[i] = false;
        }
    }

    public int solution(int k, int[][] dungeons) {
        ArrayList<Integer> countList = new ArrayList<>() {{
            add(0);
        }};
        boolean[] visited = new boolean[dungeons.length];
        permutations(countList, k, dungeons, dungeons.length, 0, 0, visited);

        return Collections.max(countList);
    }
}