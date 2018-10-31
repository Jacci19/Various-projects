package Pokemon;

import Pokemon.model.Pokemon;
import Pokemon.pokemons.*;

import Pokemon.exceptions.BadPotionException;
import Pokemon.exceptions.DeadPokemonException;


public class Pokemon_MAIN {

    public static void main(String[] args) {
        Bulbasaur b1 = new Bulbasaur(25, 20, 100);
        Buizell b2 = new Buizell(20, 10, 50);
        Chespin b3 = new Chespin(15, 25, 200);
        Mudkip c1 = new Mudkip(12, 15, 150);
        Tepig c2 = new Tepig(30, 20, 80);
        Torkoal c3 = new Torkoal(20,30, 40);

        c2.attack(b1);
        c2.attack(b1);
        //c2.attack(b1);
        //c2.attack(b1);
        //c2.attack(b1);


        heal(b1, -10);
        b1.show();
    }



    static void heal (Pokemon pok, int healValue){
/*
         try {
             pok.healEx(healValue);
        }
        catch (BadPotionException e){
            e.getMessage();
        }
*/
        System.out.println(pok.getName() + " is healing by " + healValue + "HP.");

    }
}
