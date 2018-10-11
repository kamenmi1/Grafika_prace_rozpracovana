package cz.uhk.pgrf.canvas;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * T��da vykresluj�c� non-AA ��ru.
 * 
 * @author Tom� Nov�k
 * @version 2016
 */

public class LineRenderer {

	private BufferedImage img;
	private int color;

	public LineRenderer(BufferedImage img) {
		this(img, 0xffffff);
	}

	public LineRenderer(BufferedImage img, int color) {
		this.img = img;
		this.color = color;
	}

	/**
	 * Nastaven� barvy
	 * 
	 * @param color
	 */
	public void setColor(int color) {
		this.color = color;
	}

	/**
	 * Kreslen� ��ry
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void Draw(int x1, int y1, int x2, int y2) {

		// vypocet pro smernici
		double dx = x2 - x1;
		double dy = y2 - y1;

		if (Math.abs(y2 - y1) <= Math.abs(x2 - x1)) {

			// Pro jeden bod
			if ((x1 == x2) && (y1 == y2)) {
				img.setRGB(x1, y1, color);
			} else {

				// prohozeni vodicich os
				if (x2 < x1) {
					int pomoc = x2;
					x2 = x1;
					x1 = pomoc;

					pomoc = y2;
					y2 = y1;
					y1 = pomoc;
				}

				// vypocet
				double k = dy / dx;
				int int_y;
				double y = (double) y1;

				// tisk img
				for (int x = x1; x <= x2; x++) {
					int_y = (int) Math.round(y);
					img.setRGB(x, int_y, color);
					y += k;
				}
			}
		} else {

			// prohozeni vodicich os
			if (y2 < y1) {
				int pomoc = x2;
				x2 = x1;
				x1 = pomoc;

				pomoc = y2;
				y2 = y1;
				y1 = pomoc;
			}

			// vypocet
			double k = dx / dy;
			int int_x;
			double x = (double) x1;

			// tisk img
			for (int y = y1; y <= y2; y++) {
				int_x = (int) Math.round(x);
				img.setRGB(int_x, y, color);
				x += k;
			}
		}
	}

	/**
	 * P�ekreslen� p�ede�l�ch �ar
	 * 
	 * @param lines
	 */
	public void ReDraw(List<Point> lines) {
		for (int i = 0; i < lines.size() - 1; i = i + 2) {
			Draw((int) lines.get(i).getX(), (int) lines.get(i).getY(), (int) lines.get(i + 1).getX(),
					(int) lines.get(i + 1).getY());
		}
	}

	public int getColor() {
		return color;
	}
}
