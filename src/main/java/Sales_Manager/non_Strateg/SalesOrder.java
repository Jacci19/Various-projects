package Sales_Manager.non_Strateg;

//https://www.youtube.com/watch?v=RADOhncoohY

import com.darwinsys.geo.Country;
import Sales_Manager.Common.CartItems;
import Sales_Manager.Common.Client;


public class SalesOrder {

    private Client client = new Client("Jan_Nowak", Country.PL, true);
    private CartItems cartItems = new CartItems();

    public SalesOrder() {                                               //konstruktor (brak)
    }

    public double getTotalCost(){
        return cartItems.getTotalPrice() + shippingCost();              // koszt produktów + koszt wysyłki
    }

    public double shippingCost(){
        if (!client.getCountry().equals(Country.PL)){
            if (cartItems.getTotalWeight() > 10.0){
                return 70;                                              //cena, jeśli za granicę i waga większa niż 10kg
            }
            return 50;                                                  //cena, jeśli za granicę i waga mniejsza niż 10kg
        }

        if (cartItems.getOnlyBooks()){
            if (cartItems.getTotalPrice() > 200){
                return 0;                                               //cena, jeśli tylko książki i suma > 200zł
            }
            return 5;                                                   //cena, jeśli tylko książki i suma < 200zł
        }
        return 15;                                                      // domyślny koszt wysyłki
    }

    public Client getClient() {
        return client;
    }

    CartItems getCartItems() {
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
