package cz.uhk.pgrf.canvas;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * T��da pro kreslen� kruhov� v�se�e.
 * 
 * @author Tom� Nov�k
 * @version 2016
 */
public class CircleRenderer {

	private BufferedImage img;
	private int color;
	private List<Point> fullcircle = new ArrayList<Point>();
	private LineRenderer cir;
	private double angle;
	int x1 = 0;
	int y1 = 0;
	int x2 = 0;
	int y2 = 0;
	double krajX;
	double krajY;
	int mysX;
	int mysY;

	public CircleRenderer(BufferedImage img) {
		this(img, 0xffffff);
	}

	public CircleRenderer(BufferedImage img, int color) {
		this.img = img;
		this.color = color;
		cir = new LineRenderer(this.img, this.color);
	}

	/**
	 * Vykreslen� kruhu
	 */
	public void Draw() {
		krajX = x2 - x1;
		krajY = y2 - y1;
		mysX = mysX - x1;
		mysY = mysY - y1;

		// Kontorla, kde kreslit v�se�
		int kontrola = (int) ((krajX * mysY) - (krajY * mysX));
		if (kontrola >= 0) {
			angle = 2 * Math.PI - angle;
		}

		// V�po�ty polom�ru, pooto�en�, �hlu o kter� pooto�it
		double r = (int) Math.sqrt(Math.abs(krajX * krajX) + Math.abs(krajY * krajY));
		double alfa = 10 / r;

		int n = (int) ((2 * Math.PI) / alfa);

		double dcos = Math.cos(alfa);
		double dsin = Math.sin(alfa);
		double controlangle = 0;

		// Zad�n� prvn�ho bodu a dal��ch po sob� jdouc�ch bod�
		fullcircle.add(new Point(((int) krajX + x1), ((int) krajY + y1)));
		for (int k = 0; k <= n; k++) {
			controlangle = controlangle + alfa;
			double xn = (krajX * dcos) + (krajY * dsin);
			double yn = -(krajX * dsin) + (krajY * dcos);
			if (controlangle <= angle) {
				fullcircle.add(new Point(((int) (Math.round(xn) + x1)), ((int) (Math.round(yn) + y1))));
			}
			krajX = (int) Math.round(xn);
			krajY = (int) Math.round(yn);
		}

		// Spojen� bod� vykreslen�mi �arami pomoc� LineRendereru
		for (int i = 0; i < fullcircle.size() - 1; i++) {
			cir.Draw((int) fullcircle.get(i).getX(), (int) fullcircle.get(i).getY(), (int) fullcircle.get(i + 1).getX(),
					(int) fullcircle.get(i + 1).getY());
		}
		fullcircle.clear();
	}

	/**
	 * Nastaven� sou�adnic st�edu
	 * 
	 * @param x1
	 * @param y1
	 */
	public void setX(int x1, int y1) {
		this.x1 = x1;
		this.y1 = y1;
	}

	/**
	 * Nastaven� sou�adnic kraje
	 * 
	 * @param x2
	 * @param y2
	 */
	public void setY(int x2, int y2) {
		this.x2 = x2;
		this.y2 = y2;
	}

	/**
	 * Nastaven� �hlu pro v�po�et (podle pozice kurzoru)
	 * 
	 * @param x
	 * @param y
	 */
	public void setAngle(int x, int y) {
		this.mysX = x;
		this.mysY = y;
		double cosAngle = (((krajX * (x - x1)) + (krajY * (y - y1)))
				/ (Math.sqrt(Math.pow(krajX, 2) + Math.pow(krajY, 2))
						* Math.sqrt(Math.pow((x - x1), 2) + Math.pow((y - y1), 2))));
		angle = Math.acos(cosAngle);
	}

	// P�et�en� metoda pro nastaven� �hlu
	public void setAngle(int x) {
		this.angle = x;
	}
}
