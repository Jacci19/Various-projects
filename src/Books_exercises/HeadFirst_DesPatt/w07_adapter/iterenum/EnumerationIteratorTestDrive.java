package Books_exercises.HeadFirst_DesPatt.w07_adapter.iterenum;

import java.util.*;

public class EnumerationIteratorTestDrive {
	public static void main (String args[]) {
		Vector v = new Vector(Arrays.asList(args));
		Iterator iterator = new EnumerationIterator(v.elements());
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
