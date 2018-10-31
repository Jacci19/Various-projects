package Pokemon.pokemons;

import Pokemon.model.PokemonWater;

public class Mudkip extends PokemonWater {

    public Mudkip(int AP, int DP, int maxHP) {
        super(AP, DP, maxHP);
        super.attackName = "Angry shark attack";
        super.name = "Mudkip";
    }

    public Mudkip(int AP, int DP, int maxHP, String name) {
        this(AP, DP, maxHP);
        super.name = name;
    }


}
