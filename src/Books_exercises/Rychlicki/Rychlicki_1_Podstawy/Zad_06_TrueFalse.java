package Books_exercises.Rychlicki.Rychlicki_1_Podstawy;

public class Zad_06_TrueFalse {
    public Zad_06_TrueFalse() {

/*
        Zad 6_1   Napisz program, który w formie tabeli przedstawi działanie operatorów logicznych                                                                        */
        System.out.println("\n________________________Zad 6_1_______________________________________________________________________________________");
        boolean[] bool = {false, true};
        System.out.println("Operator negacji (NOT) - !");
        System.out.println("  p\t\t  !p");
        for (boolean p : bool)
            System.out.println(p + "\t" + !p);
        System.out.println();

        System.out.println("Operator koniunkcji (AND) - & lub &&");
        System.out.println("  p\t\t  q\t\tp & q");
        for (boolean p : bool)
            for (boolean q : bool)
                System.out.println(p + "\t" + q + "\t" + (p & q));
        System.out.println();

        System.out.println("Operator alternatywy (OR) - | lub ||");
        System.out.println("  p\t\t  q\t\tp | q");
        for (boolean p : bool)
            for (boolean q : bool)
                System.out.println(p + "\t" + q + "\t" + (p | q));
        System.out.println();

/*
        Napisz program , który w formie tabeli przedstawi dowód praw De Morgana (rychlicki str. 20)
*/

        System.out.println("\n________________________Zad 6_3_______________________________________________________________________________________");
        boolean[] bool63 = {false, true};
        System.out.println("Zaprzeczenie koniunkcji jest równoważne alternatywie zaprzeczeń");
        System.out.println("p\t\t      q\t\t    p&&q\t  !(p&&q)\t\t!p\t\t     !q\t\t   !p||!q\t !(p&&q)<=>(!p||!q)");
        for (boolean p : bool63)
            for (boolean q : bool63) {
                System.out.print(p + "\t\t" + q + "\t\t" + (p && q) + "\t\t" + !(p && q));
                System.out.print("\t\t" + !p + "\t\t" + !q + "\t\t" + (!p || !q));
                System.out.println("\t\t" + (!(p && q) == (!p || !q)));
            }

        System.out.println("\n");
        System.out.println("Zaprzeczenie alternatywy jest równoważne koniunkcji zaprzeczeń");
        System.out.println("  p\t\t      q\t\t    p||q\t  !(p||q)\t\t !p\t\t    !q\t\t   !p&&!q\t !(p||q)<=>(!p&&!q)");
        for (boolean p : bool63)
            for (boolean q : bool63) {
                System.out.print(p + "\t\t" + q + "\t\t" + (p || q) + "\t\t" + !(p || q));
                System.out.print("\t\t" + !p + "\t\t" + !q + "\t\t" + (!p && !q));
                System.out.println("\t\t" + (!(p || q) == (!p && !q)));
            }
        System.out.println("\n");

        System.out.println("\n________________________Inne_______________________________________________________________________________________");
        System.out.println("Boolean.compare(true, false): " + Boolean.compare(true, false));
        System.out.println("Boolean.compare(true, true): " + Boolean.compare(true, true));
        System.out.println("Boolean.toString(true): " + Boolean.toString(true));

/*
        Napisz program demonstrujący działanie metod isDigit(), isLetter(), isLetterOrDigit(), isLowerCase(), isSpaceChar(), isUpperCase()
        i isWhiteSpace(). Wyniki przedstaw w postaci tabeli. Zadanie wykonaj dla zestawu znaków zapisanego w łańcuchu:
*/

        System.out.println("\n________________________Zad 6_8_______________________________________________________________________________________");
        String str = "A\240b3&4\040"; // punkt a
        //String str = "Łoś_0+\t"; // punkt b
        //String str = "#\"\304\\\344\b\n"; // punkt c

        System.out.println("Znaki: "+str);
        char[] znaki = str.toCharArray();
        System.out.print("Znak           ");
        for(char z : znaki)
            System.out.print("\t\t"+z);
        System.out.println();

        System.out.print("Kod znaku      ");
        for(char z : znaki)
            System.out.print("\t\t"+(int)z);
        System.out.println();

        System.out.print("isDigit()        ");
        for(char z : znaki)
            System.out.print("\t"+Character.isDigit(z));
        System.out.println();

        System.out.print("isLetter()       ");
        for(char z : znaki)
            System.out.print("\t"+Character.isLetter(z));
        System.out.println();

        System.out.print("isLetterOrDigit()");
        for(char z : znaki)
            System.out.print("\t"+Character.isLetterOrDigit(z));
        System.out.println();

        System.out.print("isLowerCase()    ");
        for(char z : znaki)
            System.out.print("\t"+Character.isLowerCase(z));
        System.out.println();

        System.out.print("isSpaceChar()    ");
        for(char z : znaki)
            System.out.print("\t"+Character.isSpaceChar(z));
        System.out.println();

        System.out.print("isUpperCase()    ");
        for(char z : znaki)
            System.out.print("\t"+Character.isUpperCase(z));
        System.out.println();

        System.out.print("isWhitespace()   ");
        for(char z : znaki)
            System.out.print("\t"+Character.isWhitespace(z));
        System.out.println();
    }
}
