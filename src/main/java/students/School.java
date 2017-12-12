package students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class School {
  public static <E> List<E> getByCriterion(List<E> in, Predicate<E> crit) {
    List<E> rv = new ArrayList<>();
    for (E s : in) {
      if (crit.test(s)) {
        rv.add(s);
      }
    }
    return rv;
  }

  public static <E> void showAll(Iterable<E> in) {
    for (E s : in) {
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

    System.out.println("Smart:");
    showAll(getByCriterion(roster, Student.getSmartCriterion()));
    System.out.println("Enthusiastic:");
    showAll(getByCriterion(roster, Student.getEnthusiasticCriterion(2)));
    System.out.println("First half of alphabet:");
    showAll(getByCriterion(roster, (Student s) -> s.getName().charAt(0) <= 'M'));

    Predicate<Student> notVerySmart = s -> s.getGpa() < 3.5F;
    Predicate<Student> somewhatSmart = s -> s.getGpa() > 2.5F;

    System.out.println("somewhat smart:");
    showAll(getByCriterion(roster, somewhatSmart));

    System.out.println("not very smart:");
    showAll(getByCriterion(roster, notVerySmart));

    System.out.println("somewhat and not very, smart:");
    Predicate intersection = somewhatSmart.and(notVerySmart);
    showAll(getByCriterion(roster, intersection));

    List<String> names = Arrays.asList("Fred", "Jim", "Sheila", "van Buren", "van Rental");
    showAll(getByCriterion(names, s -> s.length() > 4));
    showAll(getByCriterion(names, (s -> Character.isLowerCase(s.charAt(0)))));

  }
}
