package SmallProgs.Kurs_Java8_ZaczProg.L08_SupplierAndBooleanSupplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/**      http://zacznijprogramowac.net/omowienie-interfejsow-supplier-oraz-booleansupplier/
 https://github.com/ZacznijProgramowac/KursJava8/blob/0e4db44c08f714bddc8b342cd76165f756d077b5/src/main/java/interfacesAll/SupplierAndBooleanSupplier.java   */

public class SupplierAndBooleanSupplier {

    public static void main(String[] args) {

        //Supplier
        System.out.println("\n______________Supplier______________");
        List<Book> list = new ArrayList<>();

        Supplier<Book> supplier = Book::new;
        for (int i = 0; i < 100; i++) {
            list.add(supplier.get());
        }
        list.forEach(e -> System.out.println(e));


        //BooleanSupplier
        System.out.println("\n______________BooleanSupplier______________");
        Book book = new Book(19.99, "Czysty kod", "twarda");

        BooleanSupplier booleanSupplier = () -> {
            Random random = new Random();
            return random.nextBoolean();
        };
        book.setPromotion(booleanSupplier.getAsBoolean());

        System.out.println(book.isPromotion);
    }
}
