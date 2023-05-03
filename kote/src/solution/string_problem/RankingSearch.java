package solution.string_problem;

// https://school.programmers.co.kr/learn/courses/30/lessons/72412?language=java

// 정확성 + 효율성

// 지원자는 아래 4가지 항목을 반드시 선택
// - 코딩테스트 참여 개발언어 항목에 cpp, java, python 중 하나 선택
// - 지원 직군 항목에 backend와 frontend 중 하나를 선택
// - 지원 경력구분 항목에 junior와 senior 중 하나를 선택
// - 선호하는 소울푸드로 chicken과 pizza 중 하나 선택

// 지원자들의 지원 조건을 선택하면 해당 조건에 맞는 지원자가 몇 명인지 쉽게 알 수 있는 도구를 만들자.
// 예를 들어, 아래와 같습니다.
// "코딩테스트에 java로 참여했으며,
// backend 직군을 선택했고,
// junior 경력이면서,
// 소울푸드로 pizza를 선택한 사람 중
// 코딩테스트 점수를 50점 이상 받은 지원자는 몇 명인가?"

// 이를 일반화하면 아래와 같습니다.
// [조건]을 만족하는 사람 중
// 코딩테스트 점수를 x점 이상 받은 사람은 모두 몇 명인가?

// 지원자가 지원서에 입력한 4가지의 정보와 획득한 코테 점수를 하나의 문자열로 구성한 값의 배열 info
// 개발팀이 궁금해하는 문의조건이 문자열 형태로 담긴 배열 query
// 가 주어졌을 때 각 문의조건에 해당하는 사람들의 숫자를 순서대로 배열에 담아 return 해주세요.

// 1. HashMap<String, ArrayList<Integer>> 를 만듭니다.
// key는 String 값인 정보가 들어가고 value는 해당 정보에 해당되는 점수들이 들어갑니다.
// 단, - 를 고려해야 하므로 하나의 info 당 총 16개를 만들어야 합니다.
// 2. map의 value인 ArrayList<Integer>를 오름차순으로 정렬합니다.
// 3. query를 순회하며 조건을 만족하는 점수들을 가져옵니다.
// 4. 가져온 점수들 중 기준 점수 이상인 원소의 개수를 반환합니다.
//      이때 이분 탐색을 사용합니다.

import java.util.ArrayList;
import java.util.HashMap;

public class RankingSearch {
    public int[] solution(String[] info, String[] query) {
        // 1
        HashMap<String, ArrayList<Integer>> hashMap = new HashMap<>();
        for (String i : info) {
            String[] data = i.split(" ");
            String[] languages = {data[0], "-"};
            String[] jobs = {data[1], "-"};
            String[] exps = {data[2], "-"};
            String[] foods = {data[3], "-"};
            Integer value = Integer.parseInt(data[4]);

            for (String lang : languages) {
                for (String job : jobs) {
                    for (String exp : exps) {
                        for (String food : foods) {
                            String key = lang + job + exp + food;
                            ArrayList<Integer> arr = hashMap.getOrDefault(key, new ArrayList<>());

                            arr.add(value);
                            hashMap.put(key, arr);
                        }
                    }
                }
            }
        }

        // 2
        for (ArrayList<Integer> arr : hashMap.values()) {
            arr.sort(null);
        }

        // 3
        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String[] data = query[i].split(" and ");
            int target = Integer.parseInt(data[3].split(" ")[1]);
            data[3] = data[3].split(" ")[0];
            String key = String.join("", data);

            if (!hashMap.containsKey(key)) continue;

            ArrayList<Integer> arr = hashMap.get(key);
            int left = 0;
            int right = arr.size();
            while (left < right) {
                int mid = (left + right) / 2;
                if (arr.get(mid) >= target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            answer[i] = arr.size() - left;
        }

        return answer;
    }
}
