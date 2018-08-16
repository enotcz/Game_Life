/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author maksim
 */
public class Bound extends Pane {

    private double size;
    private Circle circleBig;
    private Circle circleSmall;
    private boolean isSmall;
    private boolean isVisible;

    public Bound(double x, double y, double size) {
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.size = size;
        this.setHeight(size);
        this.setWidth(size);
        circleBig = new Circle(x + size / 2, y + size / 2, size / 2);
        circleSmall = new Circle(x + size / 2, y + size / 3, size / 3);
        circleBig.setFill(Color.BURLYWOOD);
        circleSmall.setFill(Color.RED);
        this.getChildren().add(circleBig);
        this.getChildren().add(circleSmall);
        isSmall = false;
        isVisible = true;
    }

    public void blink() {
        if (isSmall = !isSmall) {
            circleSmall.setRadius(size / 3);
        } else {
            circleSmall.setRadius(size / 4);
        }
    }

    public void show() {
        circleBig.setVisible(true);
        circleSmall.setVisible(true);
        isVisible = true;
    }

    public void hide() {
        circleBig.setVisible(false);
        circleSmall.setVisible(false);
        isVisible = false;
    }
    
    public boolean isShown(){
        return isVisible;
    }

    public void setX(double x) {
        this.setLayoutX(x);
        this.circleSmall.setCenterX(size / 2);
        this.circleBig.setCenterX(size / 2);
    }

    public void setY(double y) {
        this.setLayoutY(y);
        this.circleSmall.setCenterY(size / 2);
        this.circleBig.setCenterY(size / 2);
    }

    public void setSize(double size) {
        this.size = size;
        this.setWidth(size);
        this.setHeight(size);
        this.circleSmall.setCenterX(size / 2);
        this.circleBig.setCenterX(size / 2);
        this.circleSmall.setCenterY(size / 2);
        this.circleBig.setCenterY(size / 2);
        this.circleBig.setRadius(size / 2);
        this.circleSmall.setRadius(size / 3);

    }

}
