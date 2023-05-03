import solution.string_problem.NewIdRecommendation;
import solution.string_problem.ReportResult;

public class Main {
    public static void main(String[] args) {
        String[] a = {"muzi", "frodo", "apeach", "neo"};
        String[] b = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
        int k = 2;

        ReportResult r = new ReportResult();
        r.solution(a, b, k);
    }
}
