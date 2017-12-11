package students;

import java.util.List;

public class Student {
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
    self.courses = 
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
    return courses;
  }
}
