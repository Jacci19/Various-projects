package Books_exercises.JavaReceptury.structure;

import java.util.*;

/**
 * Przedstawienie sposobu przetwarzania list w starszych wersjąch Javy
 * oraz w wersji Java 5 i nowszych.
 */
public class ListsOldAndNew {

    @SuppressWarnings("unchecked")
    public void oldWay() {
    @SuppressWarnings("rawtypes")
    // BEGIN oldWay
    List myList = new ArrayList();
    myList.add("dzień dobry");
    myList.add("do widzenia");

    // myList.add(new Date()); Tę instrukcję można skompilować, lecz później
    //                  mogłaby doprowadzić do błędów w działaniu aplikacji.

    for (int i = 0; i < myList.size(); i++) {
            String s = (String)myList.get(i);
            System.out.println(s);
    }
    // END oldWay
    }

    public void newWay() {
    // BEGIN newWay
    List<String> myList = new ArrayList<>(); // W wersji Java 5 należałoby
                            // zapisać to jako: new ArrayList<String>();
    myList.add("dzień dobry");
    myList.add("do widzenia");

    // myList.add(new Date()); Tej instrukcji nie uda się skompilować!

    for (String s : myList) {    // Patrzcie, rzutowanie w dół!
            System.out.println(s);
    }
    // END newWay
    }
}
