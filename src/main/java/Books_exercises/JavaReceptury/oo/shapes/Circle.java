package Books_exercises.JavaReceptury.oo.shapes;

public class Circle extends Shape {
	double radius;
	public double computeArea() {
		return Math.PI * radius * radius;
	}
}
