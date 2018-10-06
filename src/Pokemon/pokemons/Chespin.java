package Pokemon.pokemons;

import Pokemon.model.PokemonGrass;

public class Chespin extends PokemonGrass {

    public Chespin(int AP, int DP, int maxHP) {
        super(AP, DP, maxHP);
        super.attackName = "Hail of acorn attack";
        super.name = "Chespin";
    }

    public Chespin(int AP, int DP, int maxHP, String name) {
        this(AP, DP, maxHP);
        super.name = name;
    }


}
