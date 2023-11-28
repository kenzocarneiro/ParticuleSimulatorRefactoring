package particules;

import java.util.List;


public class ParticuleA extends Particule {

    public ParticuleA(Champ c, double x, double y, double dC) {
        super(c, x, y, dC);
        vitesseCourante = 10f;
        prochaineVitesse = 10f;
        this.passageACTIVE = 500;
        this.passageFINDEVIE = 1500;
        this.passageMORT = 2000;

    }

    public void handleCollision(Particule p) {
        if (p.getClass() == ParticuleA.class) {
            this.champ.naissance(0, this.x, this.y);
            p.phaseDeLaParticule = Phase.MORTE;
            this.phaseDeLaParticule = Phase.MORTE;
            this.champ.getParticules().remove(this);
            this.champ.getParticules().remove(p);
        }

        if (p.getClass() == ParticuleB.class) {
            this.resetVitesse();
            p.resetVitesse();
            this.champ.naissance(0, this.x, this.y);
        }
    }

    @Override
    public void resetVitesse() {
        this.prochaineVitesse = 10f;
    }
}
