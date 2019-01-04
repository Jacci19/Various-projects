package Design_Patterns.Factory.CarFactory;

/** https://www.youtube.com/watch?v=NgWfETJ33dM   */

import java.util.Scanner;

public class CarFactory {
    public static void main(String[] args) {
        CarFactory carFactory = new CarFactory();
        System.out.println("Enter the car brand (upercases): ");
        Scanner scanner = new Scanner(System.in);
        String enteredBrand = scanner.nextLine();

        String brand = carFactory.takeCar(enteredBrand).getBrand();
        String whichClass = carFactory.takeCar(enteredBrand).getClass().getName();

        System.out.println("Brand: " + brand);
        System.out.println("Class: " + whichClass);
    }

    public Car takeCar(String brand) {
        Car car;

        if (brand.equals("FIAT")) {
            car = new Fiat();
        } else if (brand.equals("FORD")) {
            car = new Ford();
        } else if (brand.equals("OPEL")) {
            car = new Opel();
        } else {
            car = new Car();
        }
        return car;
    }
}