package controleur;

public final class FabriqueControleur {
    private static FabriqueControleur instance;

    private FabriqueControleur() {
    }

    public static FabriqueControleur getInstance() {
        if (instance == null) {
            instance = new FabriqueControleur();
        }
        return instance;
    }

    public Controleur creationControleur(String lib, int largeur, int hauteur) {
        return new Controleur(lib, largeur, hauteur);
    }
}
