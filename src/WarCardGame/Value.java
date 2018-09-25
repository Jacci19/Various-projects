package WarCardGame;

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
    Walet(11),
    Dama(12),
    Król(13),
    As(14);

    private final int value;

    Value(int value){               //konstruktor
        this.value = value;
    }

    public int getValue(){
        return value;
    }

}
