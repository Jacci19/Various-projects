package Pokemon.pokemons;

import Pokemon.model.PokemonWater;

public class Buizell extends PokemonWater {

    public Buizell(int AP, int DP, int maxHP) {
        super(AP, DP, maxHP);
        super.attackName = "Shoal of herring attack";
        super.name = "Buizell";
    }

    public Buizell(int AP, int DP, int maxHP, String name) {
        this(AP, DP, maxHP);
        super.name = name;
    }


}
