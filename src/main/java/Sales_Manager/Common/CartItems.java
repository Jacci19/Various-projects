package Sales_Manager.Common;

import java.util.ArrayList;

public class CartItems {

    private ArrayList<Item> itemList = new ArrayList<>();
    private ShopItems shopItems = new ShopItems();

    public CartItems() {
        fillCart();
    }

    private void fillCart() {
        addItemToCart(shopItems.getBook_carrie());
        addItemToCart(shopItems.getBook_hobbit());
        //addItemToCart(shopItems.getCloth_jeansBlue());
        //addItemToCart(shopItems.getFood_banana());
    }

    public void addItemToCart(Item item){
        itemList.add(item);
    }

    public double getTotalWeight() {
        double totalWeight = 0;
        for (Item item: itemList){
            totalWeight += item.getWeight();
        }
        return totalWeight;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (Item item: itemList){
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

    public Boolean getOnlyBooks() {
        boolean onlyBooks = true;
        for (Item item: itemList){
            if (item.getItemType() != ItemType.BOOK){
                onlyBooks = false;
            }
        }
        return onlyBooks;
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }


}
