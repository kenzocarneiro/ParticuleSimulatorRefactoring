package visualisation;

import particules.Particule;

public class FabriqueVueParticule {

    public static VueParticule creationVueParticule(Particule p) {
        return new VueParticule(p);
    }
}
