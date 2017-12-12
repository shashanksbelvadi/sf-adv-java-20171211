package students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

interface StudentCriterion {
  boolean test(Student s);
}

interface Silly {
  boolean daft(Object s);
}

public class School {
  public static List<Student> getStudentsByCriterion(List<Student> in, Silly crit) {
    return null;
  }

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

  public static StudentCriterion and(StudentCriterion first, StudentCriterion second) {
    return s -> first.test(s) && second.test(s);
  }

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
            Student.ofNameGradeCourses("Freddy", 3.6F, "Math", "Physics"),
            Student.ofNameGradeCourses("Frederick", 2.6F, "Math", "Physics"),
            Student.ofNameGradeCourses("Freda", 1.0F, "Math", "Physics"),
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
//    showAll(getStudentsByCriterion(roster, new Student.EnthusiasticCriterion()));
    showAll(getStudentsByCriterion(roster, Student.getEnthusiasticCriterion(2)));
    System.out.println("First half of alphabet:");
    showAll(getStudentsByCriterion(roster, (Student s) -> s.getName().charAt(0) <= 'M'));

//    StudentCriterion sss = s -> s.getCourses().size() > 2;
//    ((Silly)(s -> s.getCourses().size() > 2)).;

    StudentCriterion notVerySmart = s -> s.getGpa() < 3.5F;
    StudentCriterion somewhatSmart = s -> s.getGpa() > 2.5F;

    System.out.println("somewhat smart:");
    showAll(getStudentsByCriterion(roster, somewhatSmart));

    System.out.println("not very smart:");
    showAll(getStudentsByCriterion(roster, notVerySmart));

    System.out.println("somewhat and not very, smart:");
    StudentCriterion intersection = and(somewhatSmart, notVerySmart);
    showAll(getStudentsByCriterion(roster, intersection));

  }
}
