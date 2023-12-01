package etats;

import particules.Particule;
public class FabriqueEtat {
    private static FabriqueEtat instance;

    private FabriqueEtat() {
    }

    public static FabriqueEtat getInstance() {
        if (instance == null) {
            instance = new FabriqueEtat();
        }
        return instance;
    }

    public EtatParticule creationEtat(Particule particule, EtatType etatType, CycleType cycleType) {
        switch (etatType) {
            case NORMAL:
                return creationEtatNormal(particule, cycleType);
            case EXCITE:
                return creationEtatExcite(particule, cycleType);
        }
        return null;
    }

    public EtatNormal creationEtatNormal(Particule particule, CycleType cycleType) {
        switch (cycleType) {
            case JEUNE:
                return new EtatNormalJeune(particule);
            case ACTIVE:
                return new EtatNormalActive(particule);
            case FIN_DE_VIE:
                return new EtatNormalFinDeVie(particule);
            case MORTE:
                return new EtatNormalMorte(particule);
        }
        return null;
    }

    public EtatExcite creationEtatExcite(Particule particule, CycleType cycleType) {
        switch (cycleType) {
            case JEUNE:
                return new EtatExciteJeune(particule);
            case ACTIVE:
                return new EtatExciteActive(particule);
            case FIN_DE_VIE:
                return new EtatExciteFinDeVie(particule);
            case MORTE:
                return new EtatExciteMorte(particule);
        }
        return null;
    }
}
