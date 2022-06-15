/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
public class Bush1 extends Sprite {

    public Bush1(SpriteComponent sc, int x, int y) {
	super(sc);
	Picture bush = new Picture("bush.png"); //sets a png into a variable
	setPicture(bush);
	setX(x);
	setY(y);
    }
}
