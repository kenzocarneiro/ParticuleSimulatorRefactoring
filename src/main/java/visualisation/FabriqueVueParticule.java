package visualisation;

import particules.FabriqueParticule;
import particules.Particule;

public final class FabriqueVueParticule {

    private static FabriqueVueParticule instance;

    private FabriqueVueParticule() {
    }

    public static FabriqueVueParticule getInstance() {
        if (instance == null) {
            instance = new FabriqueVueParticule();
        }
        return instance;
    }

    public VueParticule creationVueParticule(Particule p) {
        return new VueParticule(p);
    }
}
