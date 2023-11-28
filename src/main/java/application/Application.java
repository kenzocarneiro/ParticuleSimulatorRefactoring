package application;

import controleur.Controleur;
import controleur.FabriqueControleur;

public class Application {

    private static Controleur c;

    public static Controleur getC() {
        return c;
    }

    public static void main(String[] args) {


        /*
         * Création du contrôleur en lui précisant les dimensions du champ de particules
         * Le contrôleur après creation lance la simulation
         */

        c = FabriqueControleur.creationControleur("Simulateur de particules", 640, 480);
        c.lancerSimulation();

    }

}
