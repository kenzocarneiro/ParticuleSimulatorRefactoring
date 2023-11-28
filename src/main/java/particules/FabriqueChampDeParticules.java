package particules;

public class FabriqueChampDeParticules {

    public static ChampDeParticules creationChampDeParticules(int largeur, int hauteur) {
        return new ChampDeParticules(largeur, hauteur);
    }

    public static ChampDeParticules creationChampDeParticules(int largeur, int hauteur, int nb, int typeParticule) {
        return new ChampDeParticules(largeur, hauteur, nb, typeParticule);
    }
}
