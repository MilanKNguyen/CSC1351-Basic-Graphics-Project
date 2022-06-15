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
public class Pond extends Sprite {

    public Pond(SpriteComponent sc, int x, int y) {
	super(sc);
	Picture a = new Picture("pond.png"); //sets a png into a variable
	setPicture(a);
	setX(x);
	setY(y);
    }
}
