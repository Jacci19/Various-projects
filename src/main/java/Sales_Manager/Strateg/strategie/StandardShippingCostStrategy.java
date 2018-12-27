package Sales_Manager.Strateg.strategie;

import Sales_Manager.Strateg.SalesOrder;

public class StandardShippingCostStrategy extends ShippingCostStrategy{

    public StandardShippingCostStrategy(SalesOrder salesOrder) {
        super(salesOrder);
    }

    @Override
    public double shippingCost() {
        return 15;
    }
}
