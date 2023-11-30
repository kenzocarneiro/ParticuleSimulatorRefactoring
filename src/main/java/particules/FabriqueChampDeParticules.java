package particules;

public class FabriqueChampDeParticules {
    private static FabriqueChampDeParticules instance;

    private FabriqueChampDeParticules() {}

    public static FabriqueChampDeParticules getInstance() {
        if (instance == null) {
            instance = new FabriqueChampDeParticules();
        }
        return instance;
    }

    public ChampDeParticules creationChampDeParticules(int largeur, int hauteur) {
        return new ChampDeParticules(largeur, hauteur);
    }

    public ChampDeParticules creationChampDeParticules(int largeur, int hauteur, int nbParticules, ParticuleType typeParticule) {
        return new ChampDeParticules(largeur, hauteur, nbParticules, typeParticule);
    }

}
