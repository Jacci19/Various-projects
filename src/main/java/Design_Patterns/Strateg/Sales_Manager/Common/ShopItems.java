package Design_Patterns.Strateg.Sales_Manager.Common;

public class ShopItems {

    private Item book_cujo = new Item(ItemType.BOOK, "Cujo", 12.00, 0.2);
    private Item book_hobbit = new Item(ItemType.BOOK, "Hobbit", 15.00, 0.2);
    private Item book_carrie = new Item(ItemType.BOOK, "Carrie", 14.00, 0.2);
    private Item book_javaCourse = new Item(ItemType.BOOK, "Course of Java", 14.00, 0.3);

    private Item cloth_jeansBlue = new Item(ItemType.CLOTH, "Blue Jeans", 24.00, 0.8);
    private Item cloth_socksBlack = new Item(ItemType.CLOTH, "Black Socks", 8.00, 0.1);
    private Item cloth_shirtWhite = new Item(ItemType.CLOTH, "White Shirt", 21.00, 0.3);
    private Item cloth_hatBrown = new Item(ItemType.CLOTH, "Brown Hat", 32.00, 0.4);

    private Item el_cameraCanon = new Item(ItemType.ELECTRONICS, "Camera Canon", 432.00, 3.2);
    private Item el_printerEpson = new Item(ItemType.ELECTRONICS, "Printer Epson", 153.00, 12.4);

    private Item food_potatoes = new Item(ItemType.FOOD, "Potato", 0.3, 1.0);
    private Item food_bread = new Item(ItemType.FOOD, "Bread", 0.2, 0.5);
    private Item food_banana = new Item(ItemType.FOOD, "Banana", 0.5, 1.0);

    private Item oth_basket = new Item(ItemType.FOOD, "Basket", 0.9, 0.6);
    private Item oth_chair = new Item(ItemType.FOOD, "Chair", 12.9, 5.2);
    private Item oth_bed = new Item(ItemType.FOOD, "Bed", 740.9, 25.2);



    public Item getBook_cujo() {
        return book_cujo;
    }

    public Item getBook_hobbit() {
        return book_hobbit;
    }

    public Item getBook_carrie() {
        return book_carrie;
    }

    public Item getBook_javaCourse() {
        return book_javaCourse;
    }

    public Item getCloth_jeansBlue() {
        return cloth_jeansBlue;
    }

    public Item getCloth_socksBlack() {
        return cloth_socksBlack;
    }

    public Item getCloth_shirtWhite() {
        return cloth_shirtWhite;
    }

    public Item getCloth_hatBrown() {
        return cloth_hatBrown;
    }

    public Item getEl_cameraCanon() {
        return el_cameraCanon;
    }

    public Item getEl_printerEpson() {
        return el_printerEpson;
    }

    public Item getFood_potatoes() {
        return food_potatoes;
    }

    public Item getFood_bread() {
        return food_bread;
    }

    public Item getFood_banana() {
        return food_banana;
    }

    public Item getOth_basket() {
        return oth_basket;
    }

    public Item getOth_chair() {
        return oth_chair;
    }

    public Item getOth_bed() {
        return oth_bed;
    }
}


