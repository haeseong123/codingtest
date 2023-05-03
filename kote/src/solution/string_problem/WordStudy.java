package solution.string_problem;

// https://www.acmicpc.net/problem/1157

// 알파벳 대소문자로 된 단어가 주어지면,
// 이 단어에서 가장 많이 사용된 알파벳을
// 출력하는 프로그램을 작성해주세요.
// 단, 대문자와 소문자를 구분하지 않습니다.

// 사용된 알파벳을 대문자로 출력하면 됩니다.
// 단, 가장 많이 사용된 알파벳이 여러 개 존재하는 경우
// ?를 출력합니다.

// 예제 입력
// Mississipi
// 예제 출력
// ?

// 예제 입력
// zZa
// 예제 출력
// Z

// 예제 입력
// z
// 예제 출력
// Z

// 예제 입력
// baaa
// 예제 출력
// A

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// 0. 개수를 카운트할 HashMap을 만듭니다.
// 1. 문자를 입력 받습니다.
// 2. 입력받은 문자를 소문자로 변환합니다.
// 3. 문자를 순회하며 HashMap에 +1을 합니다.
// 4. HashMap의 value 중 가장 큰 수를 갖는 문자를 대문자로 반환합니다.
// 이때 가장 큰 수가 여러개라면 ?를 반환합니다.
public class WordStudy {
    public void solution() {
        // 0
        HashMap<String, Integer> map = new HashMap<>();
        // 1
        Scanner sc = new Scanner(System.in);
        // 2
        String word = sc.next().toLowerCase();
        // 3
        for (int i = 0; i < word.length(); i++) {
            String key = Character.toString(word.charAt(i));
            int value = map.getOrDefault(key, 0) + 1;
            map.put(key, value);
        }
        // 4
        String answer = "?";
        int count = -1;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (count == entry.getValue()) {
                answer = "?";
            }
            else if (count < entry.getValue()) {
                answer = entry.getKey();
                count = entry.getValue();
            }
        }
        System.out.println(answer.toUpperCase());
    }
}
