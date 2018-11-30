package JavaFX.Izak;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;


public class Izak extends Pane {

    private int speed = 8;
    private int shootFrequency = 1;

    private ImageView bodyImageView, headImageView;
    private ArrayList<String> BodyFrontList = new ArrayList<>();
    private ArrayList<String> BodyBackList = new ArrayList<>();
    private ArrayList<String> BodyLeftList = new ArrayList<>();
    private ArrayList<String> BodyRightList = new ArrayList<>();
    private Position position;
    private Boolean isMoving = false;
    private Boolean isShooting = false;


    public Izak() {                                                                                         //konstruktor
        LoadIzakImages("BodyFront01", "HeadFront");
        fillFrameList(BodyFrontList, 10, "BodyFront");
        fillFrameList(BodyBackList, 10, "BodyBack");
        fillFrameList(BodyLeftList, 10, "BodyLeft");
        fillFrameList(BodyRightList, 10, "BodyRight");
        this.setPosition(Position.FRONT);
    }

    public void LoadIzakImages(String bodyImgName, String headImgName) {
        this.getChildren().clear();
        bodyImageView = new ImageView();
        bodyImageView.setImage(new Image("JavaFx/Izak/png/" + bodyImgName +".png"));
        bodyImageView.setX(16);
        bodyImageView.setY(64);
        this.getChildren().add(bodyImageView);

        headImageView = new ImageView();
        headImageView.setImage(new Image("JavaFx/Izak/png/" + headImgName + ".png"));
        this.getChildren().add(headImageView);
    }

    private void fillFrameList(ArrayList arrayList, int listLength, String fileName){
        for (int i=1; i<= listLength; i++){
            if (i<10){
                arrayList.add(fileName + "0" + i);
            }
            else{
                arrayList.add(fileName + i);
            }
        }
    }

                                                                                                    //gettery i settery
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public Boolean getMoving() {
        return isMoving;
    }
    public void setMoving(Boolean moving) {
        isMoving = moving;
    }
    public Boolean getShooting() {
        return isShooting;
    }
    public void setShooting(Boolean shoting) {
        isShooting = shoting;
    }
    public ArrayList<String> getBodyFrontList() {
        return BodyFrontList;
    }
    public ArrayList<String> getBodyBackList() {
        return BodyBackList;
    }
    public ArrayList<String> getBodyLeftList() {
        return BodyLeftList;
    }
    public ArrayList<String> getBodyRightList() {
        return BodyRightList;
    }
    public int getShootFrequency() {
        return shootFrequency;
    }
    public void setShootFrequency(int shootFrequency) {
        this.shootFrequency = shootFrequency;
    }
}
