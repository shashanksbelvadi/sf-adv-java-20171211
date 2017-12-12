package superiterable;

import students.Student;

import java.util.Arrays;

public class TestSuperIterable {
  public static void main(String[] args) {
    SuperIterable<Student> roster = new SuperIterable<>(Arrays.asList(
        Student.ofNameGradeCourses("Fred", 3.7F, "Math", "Physics"),
        Student.ofNameGradeCourses("Freddy", 3.6F, "Math", "Physics"),
        Student.ofNameGradeCourses("Frederick", 2.6F, "Math", "Physics"),
        Student.ofNameGradeCourses("Freda", 1.0F, "Math", "Physics"),
        Student.ofNameGradeCourses("Jim", 2.7F, "Art"),
        Student.ofNameGradeCourses("Sheila", 3.9F,
            "Math", "Physics", "Astrophysics")));

    roster.forEach(x -> System.out.println(x));
    System.out.println("----------------------");

    roster
        .filter(s -> s.getGpa() > 2.9F)
        .map(s -> s.getName())
        .map(s -> s.toUpperCase())
        .forEach(x -> System.out.println(x));
    System.out.println("----------------------");

    roster
        .filter(s -> s.getGpa() > 2.9F)
        .map(x -> x.getCourses()) // probably not what we meant! See below...
        .forEach(x -> System.out.println(x));
    System.out.println("----------------------");

    roster
        .filter(s -> s.getGpa() > 2.9F)
        .flatMap(x -> new SuperIterable<>(x.getCourses())) // loses boundaries!!!
        .forEach(x -> System.out.println(x));
    System.out.println("----------------------");

    roster
        .filter(s -> s.getGpa() > 2.9F)
        .flatMap(x -> new SuperIterable<>(x.getCourses()))
        .distinct()
        .forEach(x -> System.out.println(x));
    System.out.println("----------------------");

    roster
        .filter(s -> s.getGpa() > 2.9F)
        .flatMap(x -> new SuperIterable<>(
            x.getCourses()).map(
                y -> "Student " + x.getName() + " takes " + y))
        .forEach(x -> System.out.println(x));
    System.out.println("----------------------");

  }
}
