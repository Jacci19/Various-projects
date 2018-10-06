package Pokemon.pokemons;

import Pokemon.model.PokemonGrass;

public class Bulbasaur extends PokemonGrass {

    public Bulbasaur(int AP, int DP, int maxHP) {
        super(AP, DP, maxHP);
        super.attackName = "Leaf storm attack";
        super.name = "Bulbasaur";
    }

    public Bulbasaur(int AP, int DP, int maxHP, String name) {
        this(AP, DP, maxHP);
        super.name = name;
    }


}
