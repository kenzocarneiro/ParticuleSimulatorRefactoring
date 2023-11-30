package visualisation;

import controleur.Controleur;

public final class FabriqueVueChampDeParticules {
    private static FabriqueVueChampDeParticules instance;

    private FabriqueVueChampDeParticules() {
    }

    public static FabriqueVueChampDeParticules getInstance() {
        if (instance == null) {
            instance = new FabriqueVueChampDeParticules();
        }
        return instance;
    }

    public VueChampDeParticules creationVueChampDeParticules(Controleur c) {
        return new VueChampDeParticules(c);
    }
}
