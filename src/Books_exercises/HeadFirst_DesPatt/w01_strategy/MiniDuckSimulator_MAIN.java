package Books_exercises.HeadFirst_DesPatt.w01_strategy;

public class MiniDuckSimulator_MAIN {
 
	public static void main(String[] args) {
 
		Duck_Mallard mallard = new Duck_Mallard();			//kaczka krzyżówka
		Duck_Rubber rubberDuckie = new Duck_Rubber();		//kaczka gumowa
		Duck_Decoy decoy = new Duck_Decoy();				//kaczka wabik
		Duck_Model model = new Duck_Model();				//kaczka drewniany model

		mallard.performQuack();
		rubberDuckie.performQuack();
		decoy.performQuack();
   
		model.performFly();	
		model.setFlyBehavior(new Fly_RocketPowered());
		model.performFly();
	}
}
