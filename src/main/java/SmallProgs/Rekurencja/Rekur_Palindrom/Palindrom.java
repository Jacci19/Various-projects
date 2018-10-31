package SmallProgs.Rekurencja.Rekur_Palindrom;

public class Palindrom {
    public static void main(String[] args) {

        char[] slowo = {'m','a','d','a','m'};
        System.out.println("Czy palindrom? " + czyPalindrom(slowo, 0, 4));
    }


    private static boolean czyPalindrom(char[] slowo, int p, int k){
        if (p >= k){
            return true;
        }
        else if (slowo[p] != slowo[k]){
            return false;
        }
        else{
            return czyPalindrom(slowo, p+1, k-1);
        }
    }


}

