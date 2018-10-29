package Z_inne;
/*
public class Notes {

//WIELOKROPEK - przy diowolnej liczbie parametrów
    public static void srednia(int... zbior){   // - ... oznacza, że liczba parametrów typu int przekazywanych do metody jest nieokreślona
        for (int x: zbior){
            liczSrednią();
        }

       // wywołanie funkcji:
        srednia(21, 43, 56, 22,11)  //- dla każdej ilości argumentów metoda się wykona

//----------------------------------------------------------------------------------------------------
}
*/

//ZAMYKAJ SCANNER !!!!,
//skaner.nextLine() - jak chcemy opróżnić bufor skanera, żeby mógł przejśc do następnej linii (przy try - catch wykorzystywany)

//https://www.youtube.com/watch?v=IHcTGxFQSm8&list=PLED70A92187B1406A&index=30    - ENUM
//-------------------------------------------------------------------------------------------------------------

class Test{
    static void zwieksz(int liczba){
        liczba++;
    }
}

class Main{
    public static void main(String[] args) {
        int a = 5;
        Test.zwieksz(a);
        System.out.println(a);
    }
}
// Zmienna "a" nie uległa zmianie. Typy proste przekazane jako argument nie są bezpośrednio modyfikowane.
// Jeśli jako argument przekazalibyśmy obiekt to zostałby on zmodyfikowany
// https://javastart.pl/baza-wiedzy/darmowy-kurs-java/programowanie-obiektowe/argumenty-metod
//-------------------------------------------------------------------------------------------------------------
//BigInteger    (filmy Coraxa)
//      https://www.youtube.com/watch?v=Dz9JQ_p1_4c&list=PLED70A92187B1406A&index=33
//      https://www.youtube.com/watch?v=nnROT534wVY&index=34&list=PLED70A92187B1406A