package Pokemon.pokemons;

import Pokemon.model.PokemonFire;

public class Torkoal extends PokemonFire {

    public Torkoal(int AP, int DP, int maxHP) {
        super(AP, DP, maxHP);
        super.attackName = "Hundred matches attack";
        super.name = "Torkoal";
    }

    public Torkoal(int AP, int DP, int maxHP, String name) {
        this(AP, DP, maxHP);
        super.name = name;
    }


}
