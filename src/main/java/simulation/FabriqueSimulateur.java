package simulation;

import controleur.Controleur;

public class FabriqueSimulateur {

        public static Simulateur creationSimulateur(int delaiSimulation, Controleur c) {
            return new Simulateur(delaiSimulation, c);
        }
}
