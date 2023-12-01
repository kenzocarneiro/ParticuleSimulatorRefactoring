package comportement;

import particules.Particule;

public abstract class Comportement {
    Particule particule;

    public Comportement(Particule particule) {
        this.particule = particule;
    }

    public boolean isVisible(){
        return true;
    }

    public abstract void contamine(Particule p);

    public boolean resisteEpilepsie(){
        return false;
    }



    public abstract ComportementType getComportementType();
}
