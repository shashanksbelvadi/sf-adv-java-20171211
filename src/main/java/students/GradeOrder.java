package students;

import java.util.Comparator;

public class GradeOrder implements Comparator<Student> {
  @Override
  public int compare(Student student, Student t1) {
    return Float.compare(student.getGpa(), t1.getGpa());
  }
}
