package Sales_Manager.Strateg.strategie;

import Sales_Manager.Strateg.SalesOrder;

public abstract class ShippingCostStrategy {

    protected SalesOrder salesOrder;

    public ShippingCostStrategy(SalesOrder salesOrder){                 //konstruktor
        this.salesOrder = salesOrder;
    }

    public abstract double shippingCost();

}
