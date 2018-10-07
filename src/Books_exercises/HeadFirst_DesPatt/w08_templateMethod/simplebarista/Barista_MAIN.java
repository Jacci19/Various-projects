package Books_exercises.HeadFirst_DesPatt.w08_templateMethod.simplebarista;

public class Barista_MAIN {
 
	public static void main(String[] args) {
		Tea tea = new Tea();
		Coffee coffee = new Coffee();
		System.out.println("Making tea...");
		tea.prepareRecipe();
		System.out.println("Making coffee...");
		coffee.prepareRecipe();
	}
}
