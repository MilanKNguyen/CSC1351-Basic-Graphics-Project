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
public class Bush2 extends Sprite {

    public Bush2(SpriteComponent sc, int x, int y) {
	super(sc);
	Picture bush = new Picture("bush.png"); //sets a png into a variable
	setPicture(bush);
	setX(x);
	setY(y);
    }
}
