package Books_exercises.JavaReceptury.strings;

// BEGIN main
public class SubStringDemo {
    public static void main(String[] av) {
        String a = "Java jest super.";
        System.out.println(a);
        String b = a.substring(5);    // b to łańcuch "jest super."
        System.out.println(b);
        String c = a.substring(5,9);  // c to łańcuch "jest"
        System.out.println(c);
        String d = a.substring(5,a.length()); // d to "jest super."
        System.out.println(d);
    }
}
// END main
