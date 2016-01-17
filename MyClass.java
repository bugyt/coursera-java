
public class MyClass {
	  public int a;
	  public double b;
	  public MyClass(int first, double second)
	  {
	    this.a = first;
	    this.b = second;
	  }
	  public boolean same(MyClass other)
	  {
	    return other.a == this.a && other.b == this.b;
	  }
}
