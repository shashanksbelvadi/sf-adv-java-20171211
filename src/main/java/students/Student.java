package students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

  // mutation should verify invariants at end
  // WOULD optimize out entirely, but don't do it!!!
//  private static final boolean DEVELOPMENT = false;
//  public void setGpa(float gpa) {
//    this.gpa = gpa;
//    if (DEVELOPMENT) invariant();
//   run this code with -ea (-enableassertions) flag
//    assert invariant(); // invariant check
//  }

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

  public static StudentCriterion getSmartCriterion() {
//    return new SmartCriterion();
    return smartCriterion;
//    return s -> s.gpa > 3.0F;
  }

  private static final StudentCriterion smartCriterion = s ->  s.gpa > 3.0F ;

//  private static final StudentCriterion smartCriterion = (s) -> /*{*/
//    /*return*/ s.gpa > 3.0F /*;*/
//  /*}*/;
//
//  private static final StudentCriterion smartCriterion = (s) -> {
//      System.out.println("lambda inner...");
//      return s.gpa > 3.0F;
//    };

//  private static final StudentCriterion smartCriterion = /*new StudentCriterion() {*/
////    @Override
//    /*public boolean test*/(/*Student*/ s) -> {
//    System.out.println("anonymous inner...");
//    return s.gpa > 3.0F;
//  }
//  /*}*/;
//

//  private static final StudentCriterion smartCriterion = new StudentCriterion() {
//    @Override
//    public boolean test(Student s) {
//      System.out.println("anonymous inner...");
//      return s.gpa > 3.0F;
//    }
//  };

  //  private static final StudentCriterion smartCriterion = new /*SmartCriterion();
//
//  private static class SmartCriterion implements */StudentCriterion() {
//    @Override
//    public boolean test(Student s) {
//      System.out.println("anonymous inner...");
//      return s.gpa > 3.0F;
//    }
//  };
//
//  static class EnthusiasticCriterion implements StudentCriterion {
//    @Override
//    public boolean test(Student s) {
//      return s.courses.size() >= 3;
//    }
//  }

  public static StudentCriterion getEnthusiasticCriterion(int threshold) {
//    int mythreshold = threshold;
//    mythreshold ++;
    return s -> s.getCourses().size() > threshold;
  }
}
