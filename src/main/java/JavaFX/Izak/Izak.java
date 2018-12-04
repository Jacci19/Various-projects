package JavaFX.Izak;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;


public class Izak extends Pane {

    private double speed        = 10.0;                                                   //im większa to szybszy izak
    private int shootFrequency  = 15;                                                    //im większa to rzadsze strzały
    private int shotRange       = 500;                                                 //im większa to większy zasięg pocisków
    private int shotSpeed       = 20;                                                  //im większa to większa szybkość pocisków


    private ImageView bodyImageView = new ImageView(), headImageView = new ImageView();
    private ArrayList<String> BodyFrontList = new ArrayList<>();
    private ArrayList<String> BodyBackList = new ArrayList<>();
    private ArrayList<String> BodyLeftList = new ArrayList<>();
    private ArrayList<String> BodyRightList = new ArrayList<>();
    private Position position;
    private Boolean isMoving = false;
    private Boolean isShooting = false;
    private Boolean isColliding = false;



    public Izak() {                                                                                         //konstruktor
        createIzakImage("BodyFront01", "HeadFront");
        fillFrameList(BodyFrontList, 10, "BodyFront");
        fillFrameList(BodyBackList, 10, "BodyBack");
        fillFrameList(BodyLeftList, 10, "BodyLeft");
        fillFrameList(BodyRightList, 10, "BodyRight");
        this.setPosition(Position.FRONT);
    }

    public void createIzakImage(String bodyImgName, String headImgName) {
        loadIzakBodyImage(bodyImgName);
        bodyImageView.setX(16);                                                                             //pozycja ciała względem głowy
        bodyImageView.setY(64);
        this.getChildren().add(bodyImageView);
        loadIzakHeadImage(headImgName);
        this.getChildren().add(headImageView);
    }

    public void loadIzakImages(String bodyImgName, String headImgName) {
        loadIzakBodyImage(bodyImgName);
        loadIzakHeadImage(headImgName);
    }

    public void loadIzakBodyImage(String bodyImgName) {
        bodyImageView.setImage(new Image("JavaFx/Izak/png/" + bodyImgName +".png"));
    }

    public void loadIzakHeadImage(String headImgName) {
        headImageView.setImage(new Image("JavaFx/Izak/png/" + headImgName + ".png"));
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
    public double getSpeed() {
        return speed;
    }
    public void setSpeed(double speed) {
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
    public Boolean getColliding() {
        return isColliding;
    }
    public void setColliding(Boolean colliding) {
        isColliding = colliding;
    }
    public ImageView getBodyImageView() {
        return bodyImageView;
    }
    public void setBodyImageView(ImageView bodyImageView) {
        this.bodyImageView = bodyImageView;
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
    public int getShotRange() {
        return shotRange;
    }
    public void setShotRange(int shotRange) {
        this.shotRange = shotRange;
    }
    public int getShotSpeed() {
        return shotSpeed;
    }
    public void setShotSpeed(int shotSpeed) {
        this.shotSpeed = shotSpeed;
    }
}
