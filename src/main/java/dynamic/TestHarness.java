package dynamic;

import java.lang.reflect.Method;

public class TestHarness {
  public static void main(String[] args) throws Throwable {
//    System.setSecurityManager(new SecurityManager());
    String classToTest = "dynamic.TestMe";

    Class toTest = Class.forName(classToTest);
    Object obj = toTest.newInstance();

    System.out.println(obj);

    Method[] methods = toTest.getDeclaredMethods();
    for (Method m : methods) {
      System.out.println("> " + m);
      RunMe annot = m.getAnnotation(RunMe.class);
      if (annot != null) {
        System.out.println("***** @RunMe name = " + annot.name());
        m.setAccessible(true);
        m.invoke(obj);
      }
    }
  }
}
