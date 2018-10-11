package cz.uhk.pgrf.canvas;

import java.awt.image.BufferedImage;

/**
 * T��da pro vypln�n� Seedfillem.
 * 
 * @author Tom� Nov�k
 * @version 2016
 */

public class SeedFiller extends Filler {

	int cB; // barva pozad�

	// konstruktory
	public SeedFiller(BufferedImage img) {
		super(img);
	}

	public SeedFiller(BufferedImage img, int color) {
		super(img, color);
	}

	// vypln�n� po��te�n�ho seedu
	public void StartFill(int X, int Y) {
		cB = img.getRGB(X, Y);
		Fill(X, Y);
	}

	// zaplaven� od po��te�n�ho seedu pomoc� rekurze a� do pln�ho vypln�n� nebo
	// narazen� na hranici
	public void Fill(int X, int Y) {
		if ((X > 0) && (Y > 0) && (X < img.getWidth() - 1) && (Y < img.getHeight() - 1)) {
			if ((img.getRGB(X, Y) == cB) && (img.getRGB(X, Y) != color)) {
				img.setRGB(X, Y, color);
				Fill(X + 1, Y);
				Fill(X - 1, Y);
				Fill(X, Y + 1);
				Fill(X, Y - 1);
			}
		}
	}
}
