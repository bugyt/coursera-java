public class Test
{
  public static void main(String[] args)
  {
    MyClass c1 = new MyClass(30, 123.9);
    MyClass c2 = new MyClass(30, 29.7);
    MyClass c3 = new MyClass(c1.a, c2.b);

    System.out.println(c2.same(c3));
  }
}
