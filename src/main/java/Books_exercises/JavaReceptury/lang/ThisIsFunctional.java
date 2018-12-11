package Books_exercises.JavaReceptury.lang;

@FunctionalInterface
public interface ThisIsFunctional {
	int compute(int x);
	// Jeśli usuniemy komentarz z poniższej definicji metody, ze względu
	// na użycie adnotacji @FunctionalInterface interfejsu nie uda się
	// skompilować, gdyż będzie on zawierać dwie metody, a zatem 
	// przestanie być interfejsem funkcyjnym.
	// int recompute(int x);
}