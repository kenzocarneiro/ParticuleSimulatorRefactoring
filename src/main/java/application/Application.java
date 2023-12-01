package application;

import controleur.Controleur;
import controleur.FabriqueControleur;

public class Application {

    private static Controleur controleur;

    public static Controleur getControleur() {
        return controleur;
    }

    public static void main(String[] args) {
        /*
         * Création du contrôleur en lui précisant les dimensions du champ de particules
         * Le contrôleur après creation lance la simulation
         */
        controleur = FabriqueControleur.getInstance().creationControleur("Champ de particules", 800, 600);
        controleur.lancerSimulation();
    }

}
