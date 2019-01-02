package Design_Patterns.Strateg.Sales_Manager.Strateg.strategie;

import Design_Patterns.Strateg.Sales_Manager.Strateg.SalesOrder;

public class InternationalShippingCostStrategy extends ShippingCostStrategy{

    public InternationalShippingCostStrategy(SalesOrder salesOrder) {
        super(salesOrder);
    }

    @Override
    public double shippingCost() {
        return internationalShippingCost();
    }

    private double internationalShippingCost() {
        if (cartItemsAreHeavy()){
            return 70.0;                                                //cena, jeśli za granicę i waga większa niż 10kg
        }
        return 50.0;                                                    //cena, jeśli za granicę i waga mniejsza niż 10kg
    }

    private boolean cartItemsAreHeavy() {
        return salesOrder.getCartItems().getTotalWeight() > 10.0;
    }


}









