package dynamic;

//@RunMe
public class TestMe {

  @RunMe(name="Sheila", value="x")
  public void doStuff() {
    System.out.println("doStuff");
  }

  @RunMe("Clever")
  public void doMoreStuff() {
    System.out.println("doMoreStuff");
  }

  public void dontDoStuff() {
    System.out.println("dontDoStuff");
  }

  @RunMe(name="Fred", value="Y")
  private void secretStuff() {
    System.out.println("secretStuff");
  }

  public String toString() {
    return "This is the test me class!!!";
  }
}
