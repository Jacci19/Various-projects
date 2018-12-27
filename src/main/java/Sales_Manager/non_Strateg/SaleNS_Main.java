package Sales_Manager.non_Strateg;

//https://www.youtube.com/watch?v=RADOhncoohY

public class SaleNS_Main {

    public static void main(String[] args) {

        System.out.println("PROGRAM SALE_MANAGER (non-strateg)\n");


        SalesOrder salesOrder = new SalesOrder();

        System.out.println("\n" + salesOrder + "\n");
        //System.out.println("\nCart items: " + salesOrder.getCartItems().getItemList());
        System.out.println("Total order's price: " + salesOrder.getCartItems().getTotalPrice() + "zl.");
        System.out.println("Total order's weight: " + salesOrder.getCartItems().getTotalWeight() + "kg.");
        System.out.println("Shipping cost: " + salesOrder.shippingCost() + "zl.");
        System.out.println("Only books: " + salesOrder.getCartItems().getOnlyBooks() + ".");
        System.out.println("Total order's cost: " + salesOrder.getTotalCost() + "zl.");
    }
}
