package solution.brute_force;

// 수포자는 수학을 포기한 사람의 준말입니다.
// 수포자 삼인방은 모의고사에 수학 문제를 전부 찍으려 합니다.
// 수포자는 1번 문제부터 마지막 문제까지 다음과 같이 찍습니다.

// 1번 수포자가 찍는 방식: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
// 2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
// 3번 수포자가 찍는 방식: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...

// 1번 문제부터 마지막 문제까지의
// 정답이 순서대로 들은 배열
// answers가 주어졌을 때,
// 가장 많은 문제를 맞힌 사람이
// 누구인지 배열에 담아 return 해주세요.
// 단, 가장 높은 점수를 받은 사람이
// 여럿일 경우 return하는 값을
// 오름차순 정렬해주세요.

// 1. 1번, 2번, 3번 수포자가 찍는 방식을 배열로 선언합니다.
// 2. answers를 순회하며 1번, 2번, 3번 수포자의 답이 맞는지
//      확인합니다.
// 3. 가장 많이 받은 수포자를 오름차순으로 정렬한 뒤
//      반환합니다.

// 아래처럼 구현했는데
// supoza를 ArrayList로 만들지 말고
// 배열로 만드는 것이 더 좋아보입니다.
import java.util.ArrayList;

public class MockExam {
    public int[] solution(int[] answers) {
        int[] correct = new int[3];
        ArrayList<ArrayList<Integer>> supoza = new ArrayList<>(){{
            add(new ArrayList<>() {{
                add(1); add(2); add(3); add(4); add(5);
            }});
            add(new ArrayList<>() {{
                add(2); add(1); add(2); add(3);
                add(2); add(4); add(2); add(5);
            }});
            add(new ArrayList<>() {{
                add(3); add(3);
                add(1); add(1);
                add(2); add(2);
                add(4); add(4);
                add(5); add(5);
            }});
        }};

        for (int i = 0; i < answers.length; i++) {
            if(supoza.get(0).get(i%5) == answers[i]) correct[0] += 1;
            if(supoza.get(1).get(i%8) == answers[i]) correct[1] += 1;
            if(supoza.get(2).get(i%10) == answers[i]) correct[2] += 1;
        }

        int max = Math.max(correct[0], Math.max(correct[1], correct[2]));
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (correct[i] == max) res.add(i);
        }
        int[] result = new int[res.size()];

        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i) + 1;
        }

        return result;
    }
}
