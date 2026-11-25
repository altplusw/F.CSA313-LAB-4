package mn.edu.must.sqat;

public class GradeCalculator {
    public String letterGrade(double score) {
        requireScore("score", score, 100);
        if (score >= 90) {
            return "A";
        }
        if (score >= 80) {
            return "B";
        }
        if (score >= 70) {
            return "C";
        }
        if (score >= 60) {
            return "D";
        }
        return "F";
    }

    public double totalScore(double attendance, double lab, double quiz1, double quiz2, double exam) {
        requireScore("attendance", attendance, 10);
        requireScore("lab", lab, 40);
        requireScore("quiz1", quiz1, 10);
        requireScore("quiz2", quiz2, 10);
        requireScore("exam", exam, 30);
        return attendance + lab + quiz1 + quiz2 + exam;
    }

    private static void requireScore(String component, double score, int maximum) {
        if (!Double.isFinite(score) || score < 0 || score > maximum) {
            throw new IllegalArgumentException(component + " must be between 0 and " + maximum);
        }
    }
}
