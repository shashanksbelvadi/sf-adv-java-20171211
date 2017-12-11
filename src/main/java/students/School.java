package students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

interface StudentCriterion {
  boolean test(Student s);
}

public class School {
  public static List<Student> getStudentsByCriterion(List<Student> in, StudentCriterion crit) {
    List<Student> rv = new ArrayList<>();
    for (Student s : in) {
      if (crit.test(s)) {
        rv.add(s);
      }
    }
    return rv;
  }


//  public static List<Student> getSmartStudents(List<Student> in, float threshold) {
//    List<Student> rv = new ArrayList<>();
//    for (Student s : in) {
//      if (s.getGpa() > threshold) {
//        rv.add(s);
//      }
//    }
//    return rv;
//  }
//
//  public static List<Student> getEnthusiasticStudents(List<Student> in, int threshold) {
//    List<Student> rv = new ArrayList<>();
//    for (Student s : in) {
//      if (s.getCourses().size() > threshold) {
//        rv.add(s);
//      }
//    }
//    return rv;
//  }
//
  public static void showAll(Iterable<Student> students) {
    for (Student s : students) {
      System.out.println("> " + s);
    }
    System.out.println("------------------------------");
  }

  public static void main(String[] args) {
    List<Student> roster = new ArrayList<>(
        Arrays.asList(
            Student.ofNameGradeCourses("Fred", 3.7F, "Math", "Physics"),
            Student.ofNameGradeCourses("Jim", 2.7F, "Art"),
            Student.ofNameGradeCourses("Sheila", 3.9F,
                "Math", "Physics", "Astrophysics")
        ));
    showAll(roster);
    System.out.println("grade order");
    roster.sort(new GradeOrder());
    showAll(roster);

    System.out.println("name order");
    roster.sort(new NameOrder());
    showAll(roster);

//    System.out.println("Smart:");
//    showAll(getSmartStudents(roster, 3.0F));
//    System.out.println("Enthusiastic:");
//    showAll(getEnthusiasticStudents(roster, 2));

    System.out.println("Smart:");
    showAll(getStudentsByCriterion(roster, Student.getSmartCriterion()));
    System.out.println("Enthusiastic:");
    showAll(getStudentsByCriterion(roster, new Student.EnthusiasticCriterion()));

  }
}
