package cz.uhk.pgrf.canvas;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * T��da vykresluj�c� N-�heln�k.
 * 
 * @author Tom� Nov�k
 * @version 2016
 */

public class PolygonRenderer {

	private BufferedImage img;
	private int color;
	private LineRenderer a;

	public PolygonRenderer(BufferedImage img) {
		this(img, 0xffffff);
	}

	public PolygonRenderer(BufferedImage img, int color) {
		this.img = img;
		this.color = color;
		a = new LineRenderer(this.img, this.color);
	}

	/**
	 * Vykreslen� spojen�ch �ar do N-�heln�ku pomoc� LineRendereru
	 * 
	 * @param points
	 */
	public void Draw(List<Point> points) {
		for (int i = 0; i < points.size() - 1; i++) {
			a.Draw((int) points.get(i).getX(), (int) points.get(i).getY(), (int) points.get(i + 1).getX(),
					(int) points.get(i + 1).getY());

			if (points.size() > 1) {
				a.Draw((int) points.get(0).getX(), (int) points.get(0).getY(),
						(int) points.get(points.size() - 1).getX(), (int) points.get(points.size() - 1).getY());
			}

		}
	}

	public int getColor() {
		return color;
	}
}
