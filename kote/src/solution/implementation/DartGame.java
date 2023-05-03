package solution.implementation;

// https://school.programmers.co.kr/learn/courses/30/lessons/17682?language=java

// 카카오톡 게임별의 하반기 신규 서비스로 다트 게임을 출시하기로 했다.
// 다트 게임은 다트판에 다트를 세 차례 던져
// 그 점수의 합계로 실력을 겨루는 게임으로,
// 모두가 간단히 즐길 수 있다.
// 갓 입사한 무지는 코딩 실력을 인정받아
// 게임의 핵심 부분인 점수 계산 로직을 맡게 되었다.
// 다트 게임의 점수 계산 로직은 아래와 같다.

// 1. 다트 게임은 총 3번의 기회로 구성된다.
// 2. 각 기회마다 얻을 수 있는 점수는 0점에서 10점까지이다.
// 3. 점수와 함께 Single(S), Double(D), Triple(T) 영역이 존재하고
//      각 영역 당첨 시 점수에서 1제곱, 2제곱, 3제곱
//      (점수1 , 점수2 , 점수3 )으로 계산된다.
// 4. 옵션으로 스타상(*) , 아차상(#)이 존재하며
//      스타상(*) 당첨 시 해당 점수와 바로 전에 얻은 점수를 각 2배로 만든다.
//      아차상(#) 당첨 시 해당 점수는 마이너스된다.
// 5. 스타상(*)은 첫 번째 기회에서도 나올 수 있다.
//      이 경우 첫 번째 스타상(*)의 점수만 2배가 된다. (예제 4번 참고)
// 6. 스타상(*)의 효과는 다른 스타상(*)의 효과와 중첩될 수 있다.
//      이 경우 중첩된 스타상(*) 점수는 4배가 된다. (예제 4번 참고)
// 7. 스타상(*)의 효과는 아차상(#)의 효과와 중첩될 수 있다.
//      이 경우 중첩된 아차상(#)의 점수는 -2배가 된다. (예제 5번 참고)
// 8. Single(S), Double(D), Triple(T)은
//      점수마다 하나씩 존재한다.
// 9. 스타상(*), 아차상(#)은 점수마다 둘 중
//      하나만 존재할 수 있으며, 존재하지 않을 수도 있다.

// 0~10의 정수와 문자 S, D, T, *, #로 구성된 문자열이 입력될 시 총점수를 반환하는 함수를 작성하라.

// [입력 형식]
// "점수|보너스|[옵션]"으로 이루어진 문자열 3세트.
// 예) 1S2D*3T
// 점수는 0에서 10 사이의 정수이다.
// 보너스는 S, D, T 중 하나이다.
// 옵선은 *이나 # 중 하나이며, 없을 수도 있다.

// [출력 형식]
// 3번의 기회에서 얻은 점수 합계에 해당하는 정수값을 출력한다.
// 예) 37

// [입출력 예제]
// dartResult = 1S2D*3T
// answer = 37
// 설명: 1^1 * 2^2 * 2 + 3^3

// 1. i와 acc, stack을 선언합니다.
// 2. i < dartResult.length() - 1을 만족하는 동안 3 ~ 7을 반복합니다.
// 3. "S" 혹은 "D" 혹은 "T"가 적힌 글자의 인덱스를 구합니다.
//      이를 powerIndex라 합니다.
// 4. i부터 powerIndex까지 문자열을 자르고 int형으로 변환합니다.
//      이를 score라 합니다.
// 5. powerIndex의 문자가 'S' 인지 'D' 인지 'T' 인지 구별하여
//      power에 적절한 숫자를 넣습니다.
// 6. score를 power만큼 제곱합니다.
// 7. 만약 다음 글자가 존재하고,
//      다음 글자가 '*'이라면,
//          현재 수와 이전 수에 2를 곱합니다. 단, 이전 수는 없을 수도 있습니다.
//      혹은 '#' 이라면
//          현재 수에 -1을 곱합니다
// 8. stack을 pop하며 acc에 누적합니다.
// 9. acc를 반환합니다.

import java.util.Stack;

public class DartGame {
    public int solution(String dartResult) {
        // 1
        int i = 0;
        int acc = 0;
        Stack<Integer> stack = new Stack<>();
        // 2
        while (i < dartResult.length() - 1) {
            // 3
            int powerIndex = i;
            while (Character.isDigit(dartResult.charAt(++powerIndex))) {
            }
            // 4~6
            int score = Integer.parseInt(dartResult.substring(i, powerIndex));
            char powerStr = dartResult.charAt(powerIndex);
            int power = powerStr == 'S' ? 1 : powerStr == 'D' ? 2 : 3;
            stack.add((int) Math.pow(score, power));
            i = powerIndex + 1;
            // 7
            if (i < dartResult.length()) {
                char nxt = dartResult.charAt(i);
                if (nxt == '*') {
                    if (stack.size() > 1) {
                        stack.set(stack.size() - 1, stack.get(stack.size() - 1) * 2);
                        stack.set(stack.size() - 2, stack.get(stack.size() - 2) * 2);
                    } else {
                        stack.set(0, stack.get(0) * 2);
                    }
                    i += 1;
                } else if (nxt == '#') {
                    stack.set(stack.size() - 1, stack.get(stack.size() - 1) * -1);
                    i += 1;
                }
            }
        }
        // 8
        while (!stack.isEmpty()) {
            acc += stack.pop();
        }
        // 9
        return acc;
    }
}