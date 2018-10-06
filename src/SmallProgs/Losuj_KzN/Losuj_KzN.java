//program losuje k liczb z puli n liczb
//wykorzystanie: ArrayList, Random
//na podstawie filmu Coraxa: https://www.youtube.com/watch?v=gvtt4m1aiwI&list=PLED70A92187B1406A&index=28

package SmallProgs.Losuj_KzN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Losuj_KzN {

    public static int[] losuj (int k, int n){
        Random crazy = new Random();
        ArrayList<Integer> pula = new ArrayList<>(n);
        int[] wylosowane = new int[k];

        for (int i=1; i<=n; i++){                    //wype³nienie listy "pula" kolejnymi liczbami z zakresu (1, n)
            pula.add(i);
        }

        int counter = n;
        for(int j=0; j<k; j++){
            int index = crazy.nextInt(counter);     //losuje liczbê naturaln¹ mniejsz¹ od counter
            wylosowane[j] = pula.get(index);
            pula.remove(index);
            counter--;
        }
        return wylosowane;
    }




    public static void main(String[] args) {
        int[] wyniki;
        wyniki = losuj(50, 50);
        Arrays.sort(wyniki);
        System.out.println(Arrays.toString(wyniki));
    }
}


