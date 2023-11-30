package visualisation;

import controleur.Controleur;

public final class FabriqueVueApplication {
    private static FabriqueVueApplication instance;

    private FabriqueVueApplication() {
    }

    public static FabriqueVueApplication getInstance() {
        if (instance == null) {
            instance = new FabriqueVueApplication();
        }
        return instance;
    }

    public VueApplication creationVueApplication(String lib, Controleur c) {
        return new VueApplication(lib, c);
    }
}
