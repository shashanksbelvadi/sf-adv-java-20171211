package students;

import java.util.Comparator;

public class NameOrder implements Comparator<Student> {
  @Override
  public int compare(Student student, Student t1) {
    return student.getName().compareTo(t1.getName());
  }
}
