package cz.uhk.pgrf.canvas;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * T��da pro kreslen� vyhlazen� (AA) ��ry.
 * 
 * @author Tom� Nov�k
 * @version 2016
 */

public class AALineRenderer {

	private BufferedImage img;
	private List<Integer> colors = new ArrayList<Integer>();

	public AALineRenderer(BufferedImage img) {
		this.img = img;
		colors.add(0x1Aff0000);
		colors.add(0x4Dff0000);
		colors.add(0x80ff0000);
	}

	/**
	 * Pomocn� v�po�ty
	 * 
	 */
	/*private int zaokrouleno(double x) {
		return (int) Math.round(x + 0.5);
	}*/


/*
	private double rfpart(double x) {
		return 1;
	}
*/

	/**
	 * Kreslen� AALine
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void Draw(int x1, int y1, int x2, int y2) {
		boolean steep = Math.abs(y2 - y1) > Math.abs(x2 - x1);

		// Prohozen� vod�c�ch os
		if (steep) {
			int pomoc = x1;
			x1 = y1;
			y1 = pomoc;

			pomoc = x2;
			x2 = y2;
			y2 = pomoc;
		}

		if (x1 > x2) {
			int pomoc = x1;
			x1 = x2;
			x2 = pomoc;

			pomoc = y1;
			y1 = y2;
			y2 = pomoc;
		}

		// V�po�ty
		double dx = x2 - x1;
		double dy = y2 - y1;
		double k = dy / dx;

		int xend = /*zaokrouleno*/(x1);
		int yend = (int) (y1 + k * (xend - x1));
		int xpxl1 = xend;
		double intery = yend + k;

		xend = /*zaokrouleno*/(x2);
		yend = (int) (y2 + k * (xend - x2));
		int xpxl2 = xend;

		// Kreslen� do dan�ch kvadrant�
		if (steep) {
			for (int x = (xpxl1 + 1); x <= xpxl2 - 1; x++) {
				drawing((int) (intery), x, /*rfpart*/(1));
				drawing((int) (intery) + 1, x, /*fpart*/(intery));
				intery = intery + k;
			}
		} else {
			for (int x = (xpxl1 + 1); x <= xpxl2 - 1; x++) {
				drawing(x, (int) (intery), /*rfpart*/(1));
				drawing(x, (int) (intery) + 1, /*fpart*/(intery));
				intery = intery + k;
			}
		}
	}

	/**
	 * Vykreslen� pixel� s alfa kan�lem
	 * 
	 * @param x
	 * @param y
	 * @param c
	 */
	private void drawing(int x, int y, double c) {
		double alfa = Math.round(c * 100.0) / 100.0;
		int color = 0;
		if (0 <= alfa && alfa <= 0.3) {
			color = colors.get(0);
		} else if (0.3 < alfa && alfa <= 0.7) {
			color = colors.get(1);
		} else if (0.7 < alfa && alfa <= 1) {
			color = colors.get(2);
		}
		img.setRGB(x, y, color);
	}
}
