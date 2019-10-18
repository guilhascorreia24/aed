import edu.princeton.cs.algs4.StdIn;
import pt.ualg.fct.aed.Arrays;

public class MSN_A<T extends Comparable<T>> {

	public void sort(T[] a) 
	{	}

	public boolean less(T x, T y) {
		return x.compareTo(y) <= 0;
	}

	public static <T> String stringOf(T[] a, String separator, int n) 
	{
		String result = "";
		String sep = "";
		for (int i = 0; i < n; i++) {
			result += sep + a[i];
			sep = separator;
		}
		return result;
	}
//---------------------------------------------------------------------------
	public int splitRuns(T[] a, T[] b, T[] c) 
	{
		b[0] = a[0];
		int m = 1;
		int n = 0;

		boolean first = true;

		for (int j = 1; j < a.length; j++) 
		{
			if (first) 
			{

				if (less(a[j - 1], a[j]))
					b[m++] = a[j];
				else {
					c[n++] = a[j];
					first = false;
				}
			}
			else 
			{

				if (less(a[j - 1], a[j]))
					c[n++] = a[j];
				else {
					b[m++] = a[j];
					first = true;
				}
			}
		}
		System.out.println();
		System.out.println(stringOf(b, " ", m));
		System.out.println(stringOf(c, " ", n));
		return 0;
	}

	public static void testInteger() 
	{
		Integer[] a = Arrays.integersFromInts(StdIn.readAllInts());
		Integer[] b = new Integer[a.length];
		Integer[] c = new Integer[a.length];

		MSN_A<Integer> inteiro = new MSN_A<Integer>();
		inteiro.splitRuns(a, b, c);
	}

	public static void testString() 
	{
		String[] a = StdIn.readAllStrings();
		String[] b = new String[a.length];
		String[] c = new String[a.length];

		MSN_A<String> stri = new MSN_A<String>();
		stri.splitRuns(a, b, c);
	}

	public static void main(String[] args) 
	{
		int esc = 5;
		if (args.length > 0)
			esc = Integer.parseInt(args[0]);
		switch (esc) 
		{
		case 5:
			testString();
			break;
		case 4:
			testInteger();
			break;
		default:
			break;
		}
	}
}