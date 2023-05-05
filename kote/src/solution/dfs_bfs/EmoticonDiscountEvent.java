package solution.dfs_bfs;

// https://school.programmers.co.kr/learn/courses/30/lessons/150368

// 1. 가입자를 최대한 늘리자
// 2. 판매액을 최대한 늘리자
// "1"이 우선이며, "2"는 그 다음 목표

// 행사
//      n명의 카카오톡 사용자들에게
//      m개의 이모티콘을 할인하여 판매합니다.
//
//      이모티콘마다 할인율은 다를 수 있으며,
//      할인율은 10%, 20%, 30%, 40% 중 하나로 설정됩니다.

// 사용자들은 다음과 같은 기준을 따라
// 이모티콘을 사거나, 서비스에 가입합니다.
//      각 사용자들은 자신의 기준에 따라
//      일정 비율 이상 할인하는 이모티콘을 모두 구매합니다.
//
//      각 사용자들은 자신의 기준에 따라
//      이모티콘 구매 비용의 합이 일정 가격 이상이 된다면,
//      이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입합니다.
// -> 어느정도 할인하는 것들은 다산다.
// -> 근데 그 가격이 어느정도를 넘어가면 이모티콘을 사지 않고 서비스 가입한다.

// n: 사용자 명수
// users: 구매 기준 배열
// m: 이모티콘 개수
// emoticons: 이모티콘 정보
// return: 행사 목적을 최대한으로 달성했을 때(최대 가입, 최대 판매)
//          이모티콘 플러스 가입자와 이모티콘 매출액을
//          정수형 배열에 담아 return

// 1. 할인하는 모든 경우를 계산
// 2. "1"의 결과를 순회하며 2~5를 수행
// 3. 유저를 순회하며 할인율이 유저의 목표 할인율보다 높인지 확인
//      높다면 해당 유저가 구입함
// 4. 해당 유저가 살 모든 비용을 계산했으면
//      그 비용이 이모티콘 서비스 가입 비용 이상인지 확인
//      이상이라면 이모티콘 서비스 가입
//      미만이라면 돈만 냄
// 5. 총 결괏값과 answer를 목표 1과 목표 2를 확인하며 비교
// 6. answer 반환

import java.util.ArrayList;

public class EmoticonDiscountEvent {
    ArrayList<int[]> discountsList;
    int emoSize;
    int[] discountRate = {10, 20, 30, 40};

    private void generator(int[] tmp, int depth) {
        if (depth == emoSize) {
            discountsList.add(tmp.clone());
            return;
        }

        for (int rate : discountRate) {
            tmp[depth] = rate;
            generator(tmp, depth + 1);
        }
    }

    public int[] solution(int[][] users, int[] emoticons) {
        // 1
        int[] answer = {0, 0};
        discountsList = new ArrayList<>();
        emoSize = emoticons.length;
        generator(new int[emoSize], 0);
        // 2
        for (int[] discounts : discountsList) {
            int[] tmp = {0, 0};
            // 3
            for (int[] user : users) {
                int minDiscount = user[0];
                int minRegist = user[1];
                int acc = 0;
                // 4
                for (int i = 0; i < emoSize; i++) {
                    if (discounts[i] >= minDiscount) {
                        acc += emoticons[i] * ((100 - discounts[i]) * 0.01);
                    }
                }
                if (acc >= minRegist) tmp[0] += 1;
                else tmp[1] += acc;
            }
            // 5
            if (answer[0] < tmp[0] || (answer[0] == tmp[0] && answer[1] < tmp[1])) {
                answer[0] = tmp[0];
                answer[1] = tmp[1];
            }
        }
        // 6
        return answer;
    }
}
