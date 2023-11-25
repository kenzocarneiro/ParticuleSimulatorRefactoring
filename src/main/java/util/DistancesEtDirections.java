package util;

import particules.Particule;

/* Utilitaires pour des calculs de distance et de direction */

public class DistancesEtDirections {

	public static double distanceDepuisUnPoint(double x0, double y0, double x1, double y1) {
		// rend la distance entre la particule et un point donne
		return  Math.sqrt(Math.pow(x1 - x0, 2) + Math.pow(y1 - y0, 2));
	}
	
	public static double directionDepuisUnPoint(Particule p, double xOrigine, double yOrigine, double axe) {
		// Rends l'angle en radians (entre ]-PI, PI[) de l'objet positionnable p
		// par rapport a un point origine et a l'axe donne
		// tan x = cote oppose / cote adjacent
		double angle;
		double x = p.getX();
		double y = p.getY();
		if (x != xOrigine)
			angle = Math.atan((y - yOrigine) / (x - xOrigine));
		else if (y < yOrigine)
			angle = -(Math.PI / 2);
		else
			angle = (Math.PI / 2);
		// atan a deux solutions, donc il faut corriger...
		if (x < xOrigine) {
			if (y < yOrigine)
				angle -=  Math.PI;
			else
				angle +=  Math.PI;
		}
		// Il suffit maintenant de soustraire l'axe a la direction (en radians)
		// et obtenir une direction entre -PI et PI
		double dir = angle - axe;

		if (dir >=  Math.PI)
			return dir -  (Math.PI * 2);
		else if (dir < - Math.PI)
			return dir +  (Math.PI * 2);
		else
			return dir;
	}
}
