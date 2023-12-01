package comportement;

import particules.Particule;

public class FabriqueComportement {
    private static FabriqueComportement instance;

    private FabriqueComportement() {
    }

    public static FabriqueComportement getInstance() {
        if (instance == null) {
            instance = new FabriqueComportement();
        }
        return instance;
    }

    public Comportement creationComportement(Particule particule, ComportementType comportementType) {
        switch (comportementType) {
            case NORMAL:
                return new ComportementNormal(particule);
            case EPILEPTIQUE:
                return new ComportementEpileptique(particule);
        }
        return null;
    }
}
