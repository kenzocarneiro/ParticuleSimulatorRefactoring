package comportement;

import particules.Particule;

public class ComportementNormal extends Comportement {
    public ComportementNormal(Particule particule) {
        super(particule);
    }

    public void contamine(Particule p) {
    }

    public ComportementType getComportementType() {
        return ComportementType.NORMAL;
    }
}
