package solution.brute_force;

// https://school.programmers.co.kr/learn/courses/30/lessons/84512?language=java

// 사전에 알파벳 모음 'A', 'E', 'I', 'O', 'U'만을
// 사용하여 만들 수 있는, 길이 5 이하의 모든 단어가 수록되어 있습니다.
// 사전에서 첫 번째 단어는 "A"이고,
// 그다음은 "AA"이며,
// 마지막 단어는 "UUUUU"입니다.

// 단어 하나 word가 매개변수로 주어질 때,
// 이 단어가 사전에서 몇 번째 단어인지
// return 하도록 solution 함수를 완성해주세요.

// [제한사항]
// word의 길이는 1 이상 5 이하입니다.
// word는 알파벳 대문자 'A', 'E', 'I', 'O', 'U'로만 이루어져 있습니다.

// [입출력 예]
/*
   word	   result
  "AAAAE"	 6
  "AAAE"	 10
  "I"	   1563
  "EIO"	   1189
* */

// 1. 순열을 사용하여 A, E, I, O, U로 만들 수 있는
//      모든 단어를 어레이에 넣습니다.
// 2. 어레이를 오름차순으로 정렬합니다.
// 3. 주어진 word가 array의 몇 번째 인덱스에
//      존재하는지 찾고 +1한 뒤 반환합니다.

import java.util.ArrayList;
import java.util.Collections;

public class VowelDictionary {
    private void permutations(String[] vowels, String[] output, int depth, int size, ArrayList<String> dictionary) {
        if (depth == size) {
            dictionary.add(String.join("", output));
            return;
        }

        for (String vowel : vowels) {
            output[depth] = vowel;
            permutations(vowels, output, depth + 1, size, dictionary);
        }
    }

    public int solution(String word) {
        String[] vowels = {"A", "E", "I", "O", "U"};
        ArrayList<String> dictionary = new ArrayList<>();
        // 1
        for (int i = 1; i < vowels.length + 1; i++) {
            permutations(vowels, new String[i], 0, i, dictionary);
        }
        // 2
        Collections.sort(dictionary);
        // 3
        return dictionary.indexOf(word) + 1;
    }
}
