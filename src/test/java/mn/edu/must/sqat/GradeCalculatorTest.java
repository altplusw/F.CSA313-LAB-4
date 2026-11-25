package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GradeCalculatorTest {
    @ParameterizedTest
    @DisplayName("Ердийн болон заагийн оноог үсгэн дүнд хөрвүүлэх")
    @CsvSource({"95,A", "85,B", "75,C", "65,D", "30,F", "89.99,B",
                "80,B", "79.99,C", "70,C", "69.99,D", "60,D", "59.99,F", "0,F", "100,A"})
    void gradesAcrossBands(double score, String expected) {
        var calculator = new GradeCalculator();
        String actual = calculator.letterGrade(score);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Яг 90 оноо A дүн авах ёстой")
    void exactNinetyIsA() {
        var calculator = new GradeCalculator();
        String actual = calculator.letterGrade(90);
        assertEquals("A", actual);
    }

    @Test
    @DisplayName("Сөрөг нийлбэр оноог хүлээн авахгүй")
    void rejectsNegativeGrade() {
        var calculator = new GradeCalculator();
        Executable grade = () -> calculator.letterGrade(-1);
        assertThrows(IllegalArgumentException.class, grade);
    }

    @Test
    @DisplayName("100-аас их оноог хүлээн авахгүй")
    void rejectsOversizedGrade() {
        var calculator = new GradeCalculator();
        Executable grade = () -> calculator.letterGrade(101);
        assertThrows(IllegalArgumentException.class, grade);
    }

    @Test
    @DisplayName("Тоон утгагүй оноог хүлээн авахгүй")
    void rejectsNaN() {
        var calculator = new GradeCalculator();
        Executable grade = () -> calculator.letterGrade(Double.NaN);
        assertThrows(IllegalArgumentException.class, grade);
    }

    @Test
    @DisplayName("Ирцийн сөрөг оноо нийлбэрт орохгүй")
    void rejectsNegativeAttendance() {
        var calculator = new GradeCalculator();
        Executable total = () -> calculator.totalScore(-5, 30, 8, 9, 27);
        assertThrows(IllegalArgumentException.class, total);
    }

    @Test
    @DisplayName("Лабораторийн оноо 40-өөс хэтрэх ёсгүй")
    void rejectsOversizedLabScore() {
        var calculator = new GradeCalculator();
        Executable total = () -> calculator.totalScore(8, 41, 8, 9, 27);
        assertThrows(IllegalArgumentException.class, total);
    }

    @ParameterizedTest
    @DisplayName("Таван хэсгийн оноог зөв нэгтгэх")
    @CsvSource({"10,40,10,10,30,100", "0,0,0,0,0,0", "8.5,33,7.5,9,26.5,84.5"})
    void addsValidScores(double attendance, double lab, double firstQuiz,
                        double secondQuiz, double exam, double expected) {
        var calculator = new GradeCalculator();
        double actual = calculator.totalScore(attendance, lab, firstQuiz, secondQuiz, exam);
        assertEquals(expected, actual, 0.000001);
    }

    @ParameterizedTest
    @DisplayName("Сорил болон шалгалтын хязгаарыг тус бүр шалгах")
    @CsvSource({"11,8,28", "7,-1,28", "7,8,31"})
    void rejectsInvalidQuizOrExam(double firstQuiz, double secondQuiz, double exam) {
        var calculator = new GradeCalculator();
        Executable total = () -> calculator.totalScore(8, 36, firstQuiz, secondQuiz, exam);
        assertThrows(IllegalArgumentException.class, total);
    }
}
