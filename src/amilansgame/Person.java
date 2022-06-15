/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package amilansgame;

import basicgraphics.Sprite;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author mnguy
 */
public class Person extends Sprite {

    public Picture initialPic;

    /**
     * Initializes the sprite, setting its picture, position, and speed. It also
     * adds it to the SpriteComponent.
     *
     * @param sc
     */
    public Person(SpriteComponent sc, int x, int y) {
	super(sc);
	initialPic = new Picture("baldcharacter.png");
	setPicture(initialPic);
	Dimension d = sc.getSize();
	setX(x);
	setY(y);
	setVelX(0);
	setVelY(0);
    }

    public Person(SpriteComponent sc, int x, int y, Picture p) {
	super(sc);
	setPicture(p);
	Dimension d = sc.getSize();
	setX(x);
	setY(y);
	setVelX(0);
	setVelY(0);
    }

    public void turn(double incr) {
	double heading = Math.atan2(getVelY(), getVelX());
	heading += incr;
	setVelY(Math.sin(heading));
	setVelX(Math.cos(heading));
	setPicture(initialPic.rotate(heading));
    }

    /**
     * This sprite only reacts to collisions with the borders of the display
     * region. When it does, it wraps to the other side.
     *
     * @param se
     */
    @Override
    public void processEvent(SpriteCollisionEvent se) {
	SpriteComponent sc = getSpriteComponent();
	if (se.xlo) {
	    setVelX(0);
	    setVelY(0);
	    JOptionPane.showMessageDialog(sc, "you can't go past");
	}
	if (se.xhi) {
	    setVelX(0);
	    setVelY(0);
	    JOptionPane.showMessageDialog(sc, "you can't go past");
	}
	if (se.ylo) {
	    setVelY(0);
	    setVelX(0);
	    JOptionPane.showMessageDialog(sc, "you can't go past");
	}
	if (se.yhi) {
	    setVelY(0);
	    setVelX(0);
	    JOptionPane.showMessageDialog(sc, "you can't go past");
	}
    }
}
