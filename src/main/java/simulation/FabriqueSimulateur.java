package simulation;

import controleur.Controleur;

public class FabriqueSimulateur {
    private static FabriqueSimulateur instance;

    private FabriqueSimulateur() {}

    public static FabriqueSimulateur getInstance() {
        if (instance == null) {
            instance = new FabriqueSimulateur();
        }
        return instance;
    }

    public Simulateur creationSimulateur(int delai, Controleur c) {
        return new Simulateur(delai, c);
    }
}
