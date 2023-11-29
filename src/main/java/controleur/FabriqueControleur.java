package controleur;

public class FabriqueControleur {

        public static Controleur creationControleur(String lib, int largeur, int hauteur, int nb, int type) {
            return new Controleur(lib, largeur, hauteur, nb, type);
        }

        public static Controleur creationControleur(String lib, int largeur, int hauteur) {
            return new Controleur(lib, largeur, hauteur);
        }
}
