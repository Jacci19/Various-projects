package BlackJack_enum;

public enum Value {
    Dwa(2),
    Trzy(3),
    Cztery(4),
    Pięć(5),
    Sześć(6),
    Siedem(7),
    Osiem(8),
    Dziewięć(9),
    Dziesięć(10),
    Walet(10),
    Dama(10),
    Król(10),
    As(11);

    private final int value;

    private Value(int value){               //konstruktor
        this.value = value;
    }

    private int getValue(){
        return value;
    }

}
