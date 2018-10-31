package Pokemon.model;

import Pokemon.exceptions.BadPotionException;
import Pokemon.exceptions.DeadPokemonException;

public abstract class Pokemon {                         //właściwości klasy pokemon
    private int attackPoints;
    private int defensePoints;
    private int currentHP;
    private int maxHP;
    private boolean isAlive = true;
    protected String name;
    protected String attackName;
    protected PokemonType type;
    protected PokemonType strongAgainst;
    protected PokemonType weakAgainst;
    private int dmg;
    private float posFactor = 1.5f;                    // positive factor for effective attack
    private float negFactor = 0.7f;                    // negative factor for non-effective attack


    public Pokemon(int AP, int DP, int maxHP){              //konstruktor
        this.attackPoints = AP;
        this.defensePoints = DP;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
    }


    public void healEx (int hp) throws BadPotionException, DeadPokemonException{          //heal method
        if (hp < 0){
            throw new BadPotionException("__Heal value can only be positive__");
        }
        if (currentHP > 0){
            if (currentHP + hp > maxHP){
                currentHP = maxHP;
            }
            else{
                currentHP += hp;
            }
        }
        else{
            throw new DeadPokemonException("__Your pokemon is dead...__");
        }
    }





    public int getAttackPoints() {                          //gettery
        return attackPoints;
    }

    public int getDefensePoints() {
        return defensePoints;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public String getName() {
        return name;
    }

    public String getAttackName() {
        return attackName;
    }

    public PokemonType getType() {
        return type;
    }

    public PokemonType getStrongAgainst() {
        return strongAgainst;
    }

    public PokemonType getWeakAgainst() {
        return weakAgainst;
    }



    public void attack(Pokemon other){                                              //attack method
        if (this.isAlive && other.isAlive){
            System.out.println("_________" + name + " attacks " + other.name + "________");
            this.show2(other);
            dmg = attackPoints;
            System.out.println(getName() + " used " + getAttackName() + ".");
            if (other.type.equals(this.strongAgainst)){
                dmg = (int)(dmg * posFactor);
                superEffectiveMessage();
            }
            else if (other.type.equals(this.weakAgainst)){
                dmg = (int)(dmg * negFactor);
                notEffectiveMessage();
            }
            attackInfo(dmg);
            other.currentHP -= getAttacked(other, dmg);
            defenseInfo(other, dmg);
            this.show2(other);
            System.out.println("________End of fight________\n");

        }
        else {
            if (!this.isAlive) System.out.println("Attack is impossible, " + name + " is dead.");
            if (!other.isAlive) System.out.println("Attack is impossible, " + other.name + " is dead.");
        }
    }

    private int getAttacked(Pokemon pok, int damg) {
        dmg = damg - (int)(pok.defensePoints/4);
        if (pok.currentHP > dmg) {
            pok.currentHP -= dmg;
        }
        else{
            pok.currentHP = 0;
            deadInfo();
            pok.isAlive = false;
        }
        return dmg;
    }


    private final void deadInfo() {                                                 //infos and messages
        System.out.println(getName() + " is dead.");
    }

    private void attackInfo(int dmg) {
        System.out.println(getName() + " dealt " + dmg + " damage.");
    }

    private void defenseInfo(Pokemon pok, int dmg) {
        System.out.println(pok.getName() + " received " + dmg + " damage.");
    }

    private final void superEffectiveMessage() {
        System.out.println(getAttackName() + " is super effective!");
    }

    private final void notEffectiveMessage() {
        System.out.println(getAttackName() + " is not very effective!");
    }

    public void show(){
        System.out.println("  - " + name + " <" + type + "> (" + attackPoints + ", " + defensePoints + ", " + currentHP + "/" + maxHP +")  Alive: " + isAlive);
    }
    public void show2(Pokemon p){
        System.out.println("  - " + name + " <" + type + "> (" + attackPoints + ", " + defensePoints + ", " + currentHP + "/" + maxHP +")  Alive: " + isAlive);
        System.out.println("  - " + p.name + " <" + p.type + "> (" + p.attackPoints + ", " + p.defensePoints + ", " + p.currentHP + "/" + p.maxHP +")  Alive: " + p.isAlive);
    }

}
