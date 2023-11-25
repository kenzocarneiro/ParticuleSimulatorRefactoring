package application;

import controleur.Controleur;

public class Application {

    private static Controleur c;
    public static Controleur getC() {
        return c;
    }

    public static void main (String args[]) {
    	
    	
    	/*
    	 * Création du contrôleur en lui précisant les dimensions du champ de particules
    	 * Le contrôleur après creation lance la simulation
    	 */
    	
       c = new Controleur("Simulateur de particules",640,480);
       c.lancerSimulation();
       
    }

}
