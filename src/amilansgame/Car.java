package amilansgame;

import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import basicshooter.Game;
import javax.swing.ImageIcon;

/**
 *
 * @author mnguy
 */
public class Car extends Sprite {

    public Car(SpriteComponent sc, int x, int y) {
	super(sc);
	Picture car = new Picture("car.png"); //sets a png into a variable
	setPicture(car);
	setX(x);
	setY(y);
    }
}
