package students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public final class Student {
  private String name;
  private float gpa;
  private List<String> courses; // "should" be a set...!

  public void invariant()  {
    if (name == null || gpa < 0 || gpa > 4.0f) {
      throw new RuntimeException("Bad data");
    }
  }

  private Student() {}

  public static Student ofNameGradeCourses(String name, float gpa, String ... courses) {
    Student self = new Student();
    self.name = name;
    self.gpa = gpa;
    self.courses = new ArrayList<>(Arrays.asList(courses));
    self.invariant();
    return self;
  }

  public String getName() {
    return name;
  }

  public float getGpa() {
    return gpa;
  }

  public List<String> getCourses() {
    return Collections.unmodifiableList(courses);
  }

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", gpa=" + gpa +
        ", courses=" + courses +
        '}';
  }

  public static Predicate<Student> getSmartCriterion() {
    return smartCriterion;
  }

  private static final Predicate<Student> smartCriterion = s ->  s.gpa > 3.0F ;

  public static Predicate<Student> getEnthusiasticCriterion(int threshold) {
    return s -> s.getCourses().size() > threshold;
  }
}
