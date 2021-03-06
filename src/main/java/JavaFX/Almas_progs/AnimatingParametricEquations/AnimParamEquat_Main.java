package JavaFX.Almas_progs.AnimatingParametricEquations;

/**  https://github.com/AlmasB/FXTutorials/blob/master/src/com/almasb/parametric/DrawingApp.java
 https://www.youtube.com/watch?v=KvtqeYpvrnk&index=12&list=PL4h6ypqTi3RR_bhBk6PtLfD83YkaJXXxw
 https://sites.google.com/site/topinfo12/home/obrazy-z-formul-mat/krzywe-parametryczne                  */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.*;


public class AnimParamEquat_Main extends Application {

    private GraphicsContext g;

    private double t = 0.0;
    private double oldX = 800, oldY = 500;

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(1600, 1000);

        Canvas canvas = new Canvas(1600, 1000);
        g = canvas.getGraphicsContext2D();


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                t += 0.01;                                                         //reguluje szybkość rysowania poprzez gęstość punktóe (kanciatość)
                if (t%2 < 1) {
                    draw(Color.GREEN);
                }
                else {
                    draw(Color.RED);
                }

                //draw(Color.RED);
            }
        };
        timer.start();

        root.getChildren().add(canvas);
        return root;
    }

    private void draw(Color color) {
        Point2D p = curveFunction();

        //System.out.println("t: " + t + "______X = " + p.getX() + "______Y = " + p.getY());

        g.setStroke(color);

        double newX = 800 + p.getX();                                                     //regulacja połozenia wykresu x,y
        double newY = 500 + p.getY();

        if (oldX != 800 && oldY != 500)
            g.strokeLine(oldX, oldY, newX, newY);

        //g.strokeOval(newX, newY, 5, 5);                                                 //dodaje kółeczka na liniach

        oldX = newX;
        oldY = newY;
    }

    private Point2D curveFunction() {
        double x1 = sin(t) * (pow(E, cos(t)) - 2 * cos(4*t) - pow(sin(t/12), 5));         //motylek
        double y1 = cos(t) * (pow(E, cos(t)) - 2 * cos(4*t) - pow(sin(t/12), 5));
        double x2 = 3 * cos(t);                                                           //okrąg  (owal)
        double y2 = 3 * sin(t);
        double x3 = 0.02 * t * sin(3 * t);                                                //spirala archimedesa
        double y3 = 0.02 * t * cos(3 * t);
        double x3a = 60/t * sin(6 * t);                                                   //spirala odwrotna
        double y3a = 40/t * cos(6 * t);
        double x4 = 0.01 * t * (2 * cos(t) + cos(2 * t));                                 //deltoida       x = a(2cos(t) + cos(2t)), y = a(2sin(t) - sin(2t))
        double y4 = 0.01 * t * (2 * sin(t) - sin(2 * t));
        double x5 = 30/sin(t) * cos(t);                                                   //kratka
        double y5 = 30/cos(t) * sin(t);
        double x6 = 6 * sin(t);                                                           //figura lissajous
        double y6 = 3 * sin(5 * t + 2);
        double x7 = 0.04 * t - 6 * sin(1 * t);                                            //cykloida
        double y7 = 0.04 - 6 * cos(1 * t);
        double x8 = 2 * (2 * cos(0.01 * t) - cos (2 * t));                                //kardioida zmodyfikowana
        double y8 = 2 * (2 * sin(0.01 * t) - sin (2 * t));
        double x8a = 3 * (2 * cos(1 * t) - cos (2 * t));                                  //kardioida
        double y8a = 3 * (2 * sin(1 * t) - sin (2 * t));
        double x9 = 0.03 * t * pow(cos(t/4),5);                                           //asteroida zmodyfikowana
        double y9 = 0.03 * t * pow(sin(t/4),5);
        double x9a = 6 * pow(cos(t/4),5);                                                 //asteroida
        double y9a = 6 * pow(sin(t/4),5);
        double x10 = 0.002 * t * (16 * cos(t) + 3) * 1 *  sin(t);                         //ślimak pascala zmodyfikowany
        double y10 = 0.002 * t * (16 * cos(t) + 3) * 1 *  cos(t);
        double x10a = (5 * cos(t) + 2) * sin(t);                                          //ślimak pascala
        double y10a = (5 * cos(t) + 2) * cos(t);
        double x10b = 0.001 * t * (0.01 * cos(t) + 0.6) * 0.06 * t * sin(t);              //ślimak pascala zmodyfikowany spiralny
        double y10b = 0.001 * t * (0.01 * cos(t) + 0.6) * 0.06 * t * cos(t);
        double x11 = 4 * (cos(t) + cos(7*t)/2 + sin(17*t)/3);                             //serwetka
        double y11 = 4 * (sin(t) + sin(7*t)/2 + cos(17*t)/3);

        double x0 = 11 * tan(1/t) * 0.2 * cos(t);                                         //testy
        double y0 = 0.2 * cos(1 * t) * tan( 10 * t);


        return new Point2D(x2, y4).multiply(60);                                          //wybór jednej z powyższych figur
    }

    private void saveScreenshot(Scene scene) {
        WritableImage fxImage = scene.snapshot(null);

        BufferedImage awtImage = SwingFXUtils.fromFXImage(fxImage, null);

        try {
            ImageIO.write(awtImage, "png", new File("screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createContent());
        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ENTER) {                                             //robi screenshot do pliku png po naciśnięciu ENTER
                saveScreenshot(scene);
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

/*
ptak
        double x4 = 0.01 * t * (2 * cos(t) + cos(2 * t));                                 //deltoida
        double y9 = 0.03 * t * pow(sin(t/4),3);

donut
        double x8 = 2 * (2 * cos(0.02 * t) - cos (2 * t));                                //kardioida zmodyfikowana
        double y8 = 2 * (2 * sin(0.02 * t) - sin (2 * t));

ciastko
        double x9 = 0.03 * t * pow(cos(t/4),5);                                           //asteroida zmodyfikowana
        double y8a = 3 * (2 * sin(1 * t) - sin (2 * t));

 spirala ze ślimaka vs spirala archimedesa
        double x3 = 0.02 * t * sin(3 * t);                                                //spirala archimedesa
        double y3 = 0.02 * t * cos(3 * t);

        double x10 = 0.001 * t * (0.01 * cos(t) + 0.6) * 0.06 * t * sin(t);               //ślimak pascala zmodyfikowany
        double y10 = 0.001 * t * (0.01 * cos(t) + 0.6) * 0.06 * t * cos(t);
Klepsydra
        double x3 = 0.02 * t * sin(3 * t);                                                //spirala archimedesa
        double y10 = 0.001 * t * (0.01 * cos(t) + 0.6) * 0.06 * t * cos(t);

Faforek (trójkąt)
        double x7 = 0.04 * t - 6 * sin(1 * t);                                            //cykloida
        double y3 = 0.02 * t * cos(3 * t);

 */