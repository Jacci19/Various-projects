package Sales_Manager.Strateg;

//https://www.youtube.com/watch?v=RADOhncoohY

public class Sale_Main {

    public static void main(String[] args) {

        System.out.println("PROGRAM SALE_MANAGER\n");


        SalesOrder salesOrder = new SalesOrder();

        System.out.println("\n" + salesOrder + "\n");
        //System.out.println("\nCart items: " + salesOrder.getCartItems().getItemList());
        System.out.println("Total order's price: " + salesOrder.getCartItems().getTotalPrice() + "zl.");
        System.out.println("Total order's weight: " + salesOrder.getCartItems().getTotalWeight() + "kg.");
        System.out.println("Shipping cost: " + salesOrder.createShippingCostStrategy().shippingCost() + "zl.");
        System.out.println("Only books: " + salesOrder.cartContainsOnlyBooks() + ".");
        System.out.println("Total order's cost: " + salesOrder.getTotalCost() + "zl.");
    }
}



/**
 Progam napisany na podstawie filmu z youtuba.
 Proces przekształcania programu z non-strateg w strateg (czyli przebudowanie go wg wzorca projektowego "Strategia")
 cz 1. Ekstraktujemy elementy metod w oddzielne metody, np. "salesOrder.getCartItems().getTotalPrice() > 200"  na "isEligibleForBookPromo()";
 cz 2. Tworzymy oddzielną klasę ShippingCostStrategy i do niej przenosimy powiązane z tym działaniem metody
 cz 3. Zepchnięcie różnych kosztów wysyłki do poszczególnych strategii. ShippingCostStrategy staje się klasą abstrakcyjną a poszczególne strategie jej podklasami

 */
