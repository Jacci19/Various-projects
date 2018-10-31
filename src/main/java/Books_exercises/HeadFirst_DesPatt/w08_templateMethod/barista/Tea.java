package Books_exercises.HeadFirst_DesPatt.w08_templateMethod.barista;

public class Tea extends CaffeineBeverage {
	public void brew() {
		System.out.println("Steeping the tea");
	}
	public void addCondiments() {
		System.out.println("Adding Lemon");
	}
}
