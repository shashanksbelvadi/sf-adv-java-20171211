package collection;

import students.Student;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentGrades {
  public static String getGrade(Student s) {
    float gpa = s.getGpa();
    if (gpa > 3.6) return "A";
    if (gpa > 3.5) return "B";
    if (gpa > 2.7) return "C";
    if (gpa > 2.5) return "D";
    return "F";
  }

  public static void main(String[] args) {
    List<Student> listRoster = Arrays.asList(
        Student.ofNameGradeCourses("Fred", 3.7F, "Math", "Physics"),
        Student.ofNameGradeCourses("Freddy", 3.6F, "Math", "Physics"),
        Student.ofNameGradeCourses("Frederick", 2.6F, "Math", "Physics"),
        Student.ofNameGradeCourses("Freda", 1.0F, "Math", "Physics"),
        Student.ofNameGradeCourses("Jim", 2.7F, "Art"),
        Student.ofNameGradeCourses("Sheila", 3.9F,
            "Math", "Physics", "Astrophysics"));
    Map<String, String> results = listRoster.stream()
        .collect(Collectors.groupingBy(StudentGrades::getGrade/*s -> getGrade(s)*/,
            Collectors.mapping(s -> s.getName(), Collectors.joining(", "))));

    results.entrySet().stream()
        .sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey()))
        .forEach(e -> System.out.printf(
            "Students " + e.getValue() + " got grade " + e.getKey() + "\n"));
  }
}
