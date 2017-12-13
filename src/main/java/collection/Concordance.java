package collection;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Concordance {
  private static final Pattern WORD_BOUNDARIES = Pattern.compile("\\W+");

  public static void main(String[] args) {
    try (Stream<String> in = Files.lines(Paths.get("PrideAndPrejudice.txt"))) {
      in
          .map(s -> s.toLowerCase())
          .flatMap(s -> WORD_BOUNDARIES.splitAsStream(s))
          .filter(s -> s.length() > 0)
          .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
          .entrySet().stream()
          .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
          .limit(200)
          .map(e -> String.format("%20s : %5d", e.getKey(), e.getValue()))
          .forEach(s -> System.out.println(s));
    } catch (IOException ioe) {
      ioe.printStackTrace(System.err);
    }
  }
}