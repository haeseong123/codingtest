package solution.string_problem;

// https://school.programmers.co.kr/learn/courses/30/lessons/72410

// 아이디를 규칙에 맞게 변환한 뒤 추천해주자 !

// - 아이디의 길이는 3 <= n <= 15
// - 아이디는 알파벳 소문자, 숫자, -, _, . 만 사용 가능하다.
// - 단, .는 처음과 긑에 사용할 수 없으며, 연속으로 사용할 수 없다.
// 아래 과정을 따르면 됩니다.

// 1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
// 2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
// 3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
// 4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
// 5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
// 6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
//     만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
// 7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
public class NewIdRecommendation {
    public String solution(String new_id) {
        StringBuilder sb = new StringBuilder();
        // 1
        new_id = new_id.toLowerCase();
        // 2
        for (int i = 0; i < new_id.length(); i++) {
            char c = new_id.charAt(i);
            if (Character.isAlphabetic(c) || Character.isDigit(c) || c == '-' || c == '_' || c == '.') {
                sb.append(c);
            }
        }
        new_id = sb.toString();
        // 3
        while (new_id.contains("..")) {
            new_id = new_id.replace("..", ".");
        }
        // 4
        if (!new_id.isEmpty() && new_id.charAt(0) == '.') {
            new_id = new_id.substring(1);
        }
        if (!new_id.isEmpty() && new_id.charAt(new_id.length() - 1) == '.') {
            new_id = new_id.substring(0, new_id.length() - 1);
        }
        // 5
        if (new_id.isEmpty()) {
            new_id = "a";
        }
        // 6
        if (new_id.length() >= 16) {
            new_id = new_id.substring(0, 15);
            if (new_id.charAt(new_id.length() - 1) == '.') new_id = new_id.substring(0, new_id.length() - 1);
        }
        // 7
        while (new_id.length() < 3) {
            new_id += new_id.charAt(new_id.length() - 1);
        }

        return new_id;
    }
}
