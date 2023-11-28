package etats;

import particules.Particule;

import java.util.List;

public abstract class EtatParticule {
    Particule particule;
    public EtatParticule(Particule particule) {
        this.particule = particule;
    }

    public Particule getParticule() {
        return particule;
    }
    public EtatParticule collisionSimple(List<Particule> champ){
        throw new RuntimeException("Operation Impossible");
    }
}
