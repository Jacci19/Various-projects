package Sales_Manager.Common;


public class Item {

    private ItemType itemType;
    private String name;
    private double price;
    private double weight;


    public Item(ItemType itemType, String name, double price, double weight) {
        this.itemType = itemType;
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "\nItem{" +
                "itemType=" + itemType +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                "}";
    }
}

