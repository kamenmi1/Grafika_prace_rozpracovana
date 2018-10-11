package cz.uhk.pgrf.canvas;

import java.awt.*;

/**
 * T��da hrany n-uheln�ku, zodpov�dn� za kroky nutn� ke Scanline algoritmu.
 * 
 * @author Tom� Nov�k
 * @version 2016
 */

public class SLine {

	private Point a;
	private Point b;

	// konstruktor
	public SLine(Point a, Point b) {
		this.a = a;
		this.b = b;
	}

	// zm�na sm�ru
	public void changeDirection() {
		if (a.getY() > b.getY()) {
			Point pomoc = a;
			a = b;
			b = pomoc;
		}
	}

	// zji�t�n� zda m��e existovat pr�se��k, z�rov�� podm�nka na zkr�cen�
	public boolean isIntersection(int y) {
		return (y >= a.getY() && y < b.getY());
	}

	// ur�en� pr�se��ku v Y
	public int getIntersection(int y) {
		float dx = (float) (b.getX() - a.getX());
		float dy = (float) (b.getY() - a.getY());
		float k = dx / dy;
		float q = (float) (a.getX() - (k * a.getY()));
		// Obec. rovnice p��mky k ur�en� pr�se��ku
		float x = (k * y) + q;
		return (int) x;
	}

	// zji�t�n� zda jsou vodorovn�
	public boolean isHorizontal() {
		return (a.getY() == b.getY());
	}
}