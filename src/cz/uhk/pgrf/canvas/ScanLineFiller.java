package cz.uhk.pgrf.canvas;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * T��da pro vypln�n� pomoc� Scanline.
 * 
 * @author Tom� Nov�k
 * @version 2016
 */

public class ScanLineFiller extends Filler {

	// konstruktory
	public ScanLineFiller(BufferedImage img) {
		super(img);
	}

	public ScanLineFiller(BufferedImage img, int color) {
		super(img, color);
	}

	// vypl�ovac� metoda
	public void Fill(List<Point> points) {

		// prvotn� inicializace min a max rozsahu vypl�ov�n�
		int ymin = (int) points.get(0).getY();
		int ymax = -1;

		List<SLine> lines = new ArrayList<>();

		// nov� nastaven� min a max
		// utvo�en� hran z bod�
		// jejich odstran�n� v p��pad� vodorovnosti a zm�na sm�ru (aby byl u
		// v�ech stejn�)
		for (int i = 0; i < points.size(); i++) {
			if (ymin > points.get(i).getY())
				ymin = (int) points.get(i).getY();
			if (ymax < points.get(i).getY())
				ymax = (int) points.get(i).getY();

			SLine temp = new SLine(points.get(i), points.get((i + 1) % points.size()));

			if (!temp.isHorizontal()) {
				temp.changeDirection();
				lines.add(temp);
			}
		}

		List<Integer> prusecik = new ArrayList<>();

		// zji�t�n� pr�se��k�
		for (int y = ymin; y < ymax; y++) {
			for (SLine sl : lines) {
				if (sl.isIntersection(y)) {
					prusecik.add(sl.getIntersection(y));
				}
			}

			// set��zen� pom podle x
			Collections.sort(prusecik);

			// pospojov�n� lich� -> sud�
			LineRenderer a = new LineRenderer(this.img, this.color);
			for (int i = 0; i < prusecik.size(); i += 2) {
				a.Draw(prusecik.get(i), y, prusecik.get(i + 1), y);
			}
			prusecik.clear();
		}
	}
}