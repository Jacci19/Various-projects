package Pokemon.model;

public abstract class PokemonWater extends Pokemon {

    public PokemonWater(int AP, int DP, int maxHP) {
        super(AP, DP, maxHP);
        super.type = PokemonType.WATER;
        super.weakAgainst = PokemonType.GRASS;
        super.strongAgainst = PokemonType.FIRE;

    }
}
