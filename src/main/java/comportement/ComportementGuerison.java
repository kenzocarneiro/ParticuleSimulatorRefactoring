package comportement;

import etats.EtatType;
import particules.Particule;

public class ComportementGuerison extends Comportement {
    public ComportementGuerison(Particule particule) {
        super(particule);
    }

    public void contamine(Particule p) {
        if(this.particule.estExciteEtActive() && p.estExciteEtActive()) {
            p.setComportement(new ComportementNormal(p));
        }
    }

    @Override
    public boolean resisteEpilepsie(){
        return true;
    }

    public ComportementType getComportementType() {
        return ComportementType.GUERISON;
    }
}
