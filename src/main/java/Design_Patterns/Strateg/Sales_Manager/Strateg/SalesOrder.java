package Design_Patterns.Strateg.Sales_Manager.Strateg;

import Design_Patterns.Strateg.Sales_Manager.Common.CartItems;
import Design_Patterns.Strateg.Sales_Manager.Common.Client;
import Design_Patterns.Strateg.Sales_Manager.Strateg.strategie.BooksPromoShippingCostStrategy;
import Design_Patterns.Strateg.Sales_Manager.Strateg.strategie.InternationalShippingCostStrategy;
import Design_Patterns.Strateg.Sales_Manager.Strateg.strategie.ShippingCostStrategy;
import Design_Patterns.Strateg.Sales_Manager.Strateg.strategie.StandardShippingCostStrategy;
import com.darwinsys.geo.Country;

public class SalesOrder {

    private Client client = new Client("Jan_Nowak", Country.CA, true);
    private CartItems cartItems = new CartItems();

    public SalesOrder() {                                                                           //konstruktor (brak)
    }

    public double getTotalCost(){
        return cartItems.getTotalPrice() + createShippingCostStrategy().shippingCost();              // koszt produktów + koszt wysyłki
    }



    ShippingCostStrategy createShippingCostStrategy() {
        if (orderIsInternational()) {
            return new InternationalShippingCostStrategy(this);
        }
        if (cartContainsOnlyBooks()) {
            return new BooksPromoShippingCostStrategy(this);
        }
        return new StandardShippingCostStrategy(this);

    }

    boolean cartContainsOnlyBooks() {
        return cartItems.getOnlyBooks();
    }

    boolean orderIsInternational() {
        return !client.getCountry().equals(Country.PL);
    }

    public Client getClient() {
        return client;
    }

    public CartItems getCartItems() {
        return cartItems;
    }



    @Override
    public String toString() {
        return "SalesOrder{" +
                "client=" + client +
                ", \ncartItems=" + cartItems.getItemList() +
                '}';
    }
}
