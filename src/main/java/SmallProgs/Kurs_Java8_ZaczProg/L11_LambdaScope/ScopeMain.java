package SmallProgs.Kurs_Java8_ZaczProg.L11_LambdaScope;

import java.util.function.Predicate;

/**        https://github.com/ZacznijProgramowac/KursJava8/tree/3ce588ee88b307da22799de6733e776c84e5695c/src/main/java/lambdaScope
 http://zacznijprogramowac.net/zasieg-zmiennych-lokalnych-i-pol-w-lambdach/                          */

public class ScopeMain {


    public static void main(String[] args) {
        TestLambdaScope scopeMain = new TestLambdaScope();
        scopeMain.checkPrice();

    }

    static class TestLambdaScope {
        Book book = new Book(29.99, "Pani jeziora", "miękka");
        double maxPrice = 30.0;

        public void checkPrice() {
            Predicate<Book> checkPriceBook = book -> {
                maxPrice = 19.99;
                return book.price > maxPrice;
            };

            System.out.println(checkPriceBook.test(book));
        }

        public void checkPriceFinal() {
            final double maxPriceFinal = 39.99;
            Predicate<Book> checkPriceBook = book -> {
                return book.price > maxPrice;
            };
            System.out.println(checkPriceBook.test(book));
        }
    }
}
