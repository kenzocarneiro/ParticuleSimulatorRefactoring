package comportement;

import particules.Particule;

public abstract class Comportement {
    Particule particule;

    public Comportement(Particule particule) {
        this.particule = particule;
    }

    public abstract boolean isVisible();

    public abstract void contamine(Particule p);
}
