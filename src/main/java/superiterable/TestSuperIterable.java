package superiterable;

import students.Student;

import java.util.Arrays;
import java.util.List;

public class TestSuperIterable {
  public static void main(String[] args) {
    List<Student> listRoster = Arrays.asList(
        Student.ofNameGradeCourses("Fred", 3.7F, "Math", "Physics"),
        Student.ofNameGradeCourses("Freddy", 3.6F, "Math", "Physics"),
        Student.ofNameGradeCourses("Frederick", 2.6F, "Math", "Physics"),
        Student.ofNameGradeCourses("Freda", 1.0F, "Math", "Physics"),
        Student.ofNameGradeCourses("Jim", 2.7F, "Art"),
        Student.ofNameGradeCourses("Sheila", 3.9F,
            "Math", "Physics", "Astrophysics"));

    SuperIterable<Student> roster = new SuperIterable<>(listRoster);

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

    System.out.println(" ----------------- Stream Versions --------------------");

    listRoster.stream().forEach(x -> System.out.println(x));
    System.out.println("----------------------");

    listRoster.stream()
        .filter(s -> s.getGpa() > 2.9F)
        .map(s -> s.getName())
        .map(s -> s.toUpperCase())
        .forEach(x -> System.out.println(x));
    System.out.println("----------------------");

    listRoster.stream()
        .filter(s -> s.getGpa() > 2.9F)
        .map(x -> x.getCourses()) // probably not what we meant! See below...
        .forEach(x -> System.out.println(x));
    System.out.println("----------------------");

    listRoster.stream()
        .filter(s -> s.getGpa() > 2.9F)
        .flatMap(x -> x.getCourses().stream()) // loses boundaries!!!
        .forEach(x -> System.out.println(x));
    System.out.println("----------------------");

    listRoster.stream()
        .filter(s -> s.getGpa() > 2.9F)
        .flatMap(x -> x.getCourses().stream())
        .distinct()
        .forEach(x -> System.out.println(x));
    System.out.println("----------------------");

    listRoster.stream()
        .filter(s -> s.getGpa() > 2.9F)
        .flatMap(x -> x.getCourses().stream().map(
            y -> "Student " + x.getName() + " takes " + y))
        .forEach(x -> System.out.println(x));
    System.out.println("----------------------");

    listRoster.stream()
        .filter(s -> s.getGpa() > 2.9F)
        .map(s -> s.getName())
        .forEach(x -> System.out.println(x));
    System.out.println("----------------------");

    listRoster.stream()
        .filter(s -> s.getGpa() > 2.9F)
        .flatMap(x -> x.getCourses().stream())
        .distinct()
        .map(x -> x.toUpperCase())
        .forEach(x -> System.out.println(x));
    System.out.println("----------------------");

    long count = listRoster.stream()
        .filter(s -> s.getGpa() > 2.9F)
        .flatMap(x -> x.getCourses().stream())
        .distinct()
        .count();
        System.out.println("There are " + count + " distinct courses being taken by students");
    System.out.println("----------------------");

  }
}
