package cz.uhk.pgrf.canvas;

import java.awt.image.BufferedImage;

/**
 * Interface pro vypl�ovac� t��dy.
 * 
 * @author Tom� Nov�k
 * @version 2016
 */

public abstract class Filler {
	protected BufferedImage img;
	protected int color;

	protected Filler(BufferedImage img) {
		this(img, 0xffffffff);
	}

	protected Filler(BufferedImage img, int color) {
		this.img = img;
		this.color = color;
	}

	public void setColor(int C) {
		color = C;
	}

	protected void drawPixel() {

	}
}
