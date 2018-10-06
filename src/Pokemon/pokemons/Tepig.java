package Pokemon.pokemons;

import Pokemon.model.PokemonFire;

public class Tepig extends PokemonFire {

    public Tepig(int AP, int DP, int maxHP) {
        super(AP, DP, maxHP);
        super.attackName = "Fire ball attack";
        super.name = "Tepig";
    }

    public Tepig(int AP, int DP, int maxHP, String name) {
        this(AP, DP, maxHP);
        super.name = name;
    }


}
