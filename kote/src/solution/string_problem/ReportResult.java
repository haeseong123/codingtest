package solution.string_problem;

// https://school.programmers.co.kr/learn/courses/30/lessons/92334

// 각 유저는 한 번에 한명의 유저를 신고합니다.
//  신고 횟수에 제한은 없습니다.
//  한 유저를 여러 번 신고할 수 있지만, 동일한 유저에 대한 신고 횟수는 1회로 처리됩니다.
// k번 이상 신고된 유저는 게시판 이용이 정지되며, 해당 유저를 신고한 모든 유저에게 정지 사실을 메일로 발송합니다.
//  유저가 신고한 모든 내용을 취합하여 마지막에 한꺼번에 게시판 이용 정지를 시키면서 정지 메일을 발송합니다.
// 이요자의 ID가 담긴 문자열 배열 id_list,
// 각 이용자가 신고한 이용자의 ID 정보가 담긴 문자열 배열 report
// 정지 기준이 되는 신고 횟수 k가 주어질 때
// 각 유저별로 처리 결과 메일을 받은 횟수를 배열에 담아 return 해주세요.

// 0. int[] result = new int[id_list.length()]를 선언한다.
//      "접수자-피신고자" map을 작성한다.
//      "피신고자-신고 당한 횟수" map을 작성한다.
// 1. report를 순회하며 "접수자-피신고자" map을 채워 넣는다.
//  이때 "피신고자-신고 당한 횟수" map도 함께 채워 넣는다.
// 2. id_list를 순회하며 그 안에서 "접수자-피신고자" map을 순회한다.
//  "피신고자"가 "피신고자-신고 당한 횟수" map에서 k 이상인지 확인한다.
//  k 이상이라면 result[index] += 1 을 한다.
// 3. result를 반환한다.


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class ReportResult {
    public int[] solution(String[] id_list, String[] report, int k) {
        // 0
        int[] result = new int[id_list.length];
        Map<String, Set<String>> reportReportedMap = new HashMap<>();
        Map<String, Integer> reportedCountMap = new HashMap<>();
        for (String id : id_list) {
            reportReportedMap.put(id, new HashSet<>());
            reportedCountMap.put(id, 0);
        }
        // 1
        Set<String> reportedSet;
        for (String r : report) {
            String[] report2reported = r.split(" ");
            reportedSet = reportReportedMap.get(report2reported[0]);
            String reportedName = report2reported[1];

            if (reportedSet.contains(report2reported[1])) {
                continue;
            }

            reportedSet.add(reportedName);
            reportedCountMap.replace(reportedName, reportedCountMap.get(reportedName) + 1);
        }
        // 2
        for (int i = 0; i < id_list.length; i++) {
            int count = 0;
            for (String reported : reportReportedMap.get(id_list[i])) {
                if (reportedCountMap.get(reported) >= k) {
                    count++;
                }
            }
            result[i] = count;
        }
        // 3
        return result;
    }
}
