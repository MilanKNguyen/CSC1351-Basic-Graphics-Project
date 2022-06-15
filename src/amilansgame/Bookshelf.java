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
public class Bookshelf extends Sprite {

    public Bookshelf(SpriteComponent sc, int x, int y) {
	super(sc);
	Picture a = new Picture("bookshelf.png"); //sets a png into a variable
	setPicture(a);
	setX(x);
	setY(y);
    }
}
