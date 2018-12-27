package Sales_Manager.Strateg.strategie;

import Sales_Manager.Strateg.SalesOrder;

public class BooksPromoShippingCostStrategy extends ShippingCostStrategy {

    public BooksPromoShippingCostStrategy(SalesOrder salesOrder) {
        super(salesOrder);
    }

    @Override
    public double shippingCost() {
        return booksPromoShippingCost();
    }


    private double booksPromoShippingCost() {
        if (isEligibleForBookPromo()){                                  // jeśli spełnia warunki promocji to....
            return 0;                                                   // cena, jeśli są tylko książki i suma > 200zł
        }
        return 5;                                                       //cena, jeśli tylko książki i suma < 200zł
    }

    private boolean isEligibleForBookPromo() {
        return salesOrder.getCartItems().getTotalPrice() > 200;
    }

}
