package solution.string_problem;

// https://school.programmers.co.kr/learn/courses/30/lessons/150370?language=java

// 고객의 약관 동의를 얻어서 수집된
// 1~n번으로 분류되는 개인정보 n개가 있습니다.
// 약관 종류는 여러 가지 있으며
// 각 약관마다 개인정보 보관 유효기간이 정해져 있습니다.
// 당신은 각 개인정보가 어떤 약관으로 수집됐는지 알고 있습니다.
// 수집된 개인정보는 유효기간 전까지만 보관 가능하며,
// 유효기간이 지났다면 반드시 파기해야 합니다.

// 예를 들어, A라는 약관의 유효기간이 12 달이고,
// 2021년 1월 5일에 수집된 개인정보가 A약관으로 수집되었다면
// 해당 개인정보는 2022년 1월 4일까지 보관 가능하며
// 2022년 1월 5일부터 파기해야 할 개인정보입니다.

// 오늘 날짜를 의미하는 문자열 today,
// 약관의 유효기간을 담은 1차원 문자열 배열 terms와
// 수집된 개인정보의 정보를 담은 1차원 문자열 배열 privacies가
// 매개변수로 주어집니다.
// 이때 파기해야 할 개인정보의 번호를
// 오름차순으로 1차원 정수 배열에 담아
// return 하도록 solution 함수를 완성해 주세요.

// privacies를 순회하며
//      "약관 종류"를 key로 사용해서
//      "terms"에서 유효기간을 찾고,
//      "개인정보 수집 일자" + "유효 기간"을 한 뒤
//      그 값을 "today"와 비교하여 "today" 이하라면 answer에 추가합니다.
// answer를 반환합니다.

// 1. terms를 순회하며 <type:기간> 형식의 termsMap을 생성합니다.
// 2. privacies를 순회하며 해당 privacy에 담긴
//      약관 종류를 사용하여 "기간"을 가져오고
//      "개인정보 수집 일자" + "기간" <= "today" 를 만족한다면
//      arrayList에 추가합니다.
// 3. arrayList를 돌며 int[] 배열에 값을 옮깁니다.
// 4. int[] 배열을 반환합니다.

import java.util.*;

public class PersonalInformationCollectionValidityPeriod {

    private int getDate(String date) {
        String[] data = date.split("\\.");
        return (Integer.parseInt(data[0]) * 12 * 28)
                + (Integer.parseInt(data[1]) * 28)
                + (Integer.parseInt(data[2]));
    }

    public int[] solution(String today, String[] terms, String[] privacies) {
        // 1
        int date = getDate(today);
        Map<String, Integer> termsMap = new HashMap<>();
        for (String term : terms) {
            String[] data = term.split(" ");
            String type = data[0];
            int month = Integer.parseInt(data[1]);
            termsMap.put(type, month);
        }
        // 2
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            String[] data = privacies[i].split(" ");
            String contractDate = data[0];
            String contractType = data[1];
            int period = termsMap.get(contractType);
            //
            if (getDate(contractDate) + period * 28 <= date) res.add(i + 1);
        }
        // 3
        int[] answer = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            answer[i] = res.get(i);
        }
        // 4
        return answer;
    }
}
