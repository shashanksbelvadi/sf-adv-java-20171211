package generics;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BadLists {

  public static void breakIt(List l) {
    l.add(LocalDateTime.now());
  }

  public static void main(String[] args) {
    List<String> ls = new ArrayList<>();
    ls.add("Hello");
    String s = ls.get(0);
//    ls.add(LocalDateTime.now());
    breakIt(ls);

    for (String st : ls) {
      System.out.println(st);
    }
  }
}
