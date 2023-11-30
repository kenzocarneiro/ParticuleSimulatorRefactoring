package simulation;

import controleur.Controleur;

public class FabriqueSimulateur {
    private static Simulateur simulateur;

    private FabriqueSimulateur() {
    }

    public static Simulateur creationSimulateur(int delai, Controleur c) {
        if (simulateur == null) {
            simulateur = new Simulateur(delai, c);
        }
        return simulateur;
    }
}
