package generics;

public class Pair<E extends Sized & Colored /*, F*/> {
  private E left;
  private E right;
//  private F x = "Hello";

  public Pair(E left, E right) {
    this.left = left;
    this.right = right;
  }

  public E getLeft() {
    return left;
  }

  public void setLeft(E left) {
    this.left = left;
  }

  public /*<F>*/ E getRight() {
    return right;
  }

  public void setRight(E right) {
    this.right = right;
  }

  public boolean matched() {
    return left.getSize() == right.getSize()
        && left.getColor().equals(right.getColor());
  }

  public static <F> F extractLeft(Pair<F> p) {
    return p.getLeft();
  }

  @Override
  public String toString() {
    return "Pair{" +
        "left=" + left +
        ", right=" + right +
        '}';
  }
}
