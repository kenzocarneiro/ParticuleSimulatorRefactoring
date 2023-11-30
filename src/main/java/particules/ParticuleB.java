package particules;

import java.util.List;


public class ParticuleB extends Particule {

    public ParticuleB(Champ c, double x, double y, double dC) {
        super(c, x, y, dC);
        vitesseCourante = 30f;
        this.prochaineVitesse = 30f;
        this.passageACTIVE = 100;
        this.passageFINDEVIE = 300;
        this.passageMORT = 700;
    }

    public void resetVitesse() {
        this.prochaineVitesse = 30f;
    }


    public void handleCollision(Particule p) {
        if (p.getClass() == ParticuleB.class) {
            this.champ.naissance(1, this.x, this.y);
            p.phaseDeLaParticule = Phase.MORTE;
            this.phaseDeLaParticule = Phase.MORTE;
            this.champ.removeParticule(this);
            this.champ.removeParticule(p);
        }

        if (p.getClass() == ParticuleA.class) {
            this.resetVitesse();
            p.resetVitesse();
            this.champ.naissance(0, this.x, this.y);
        }
    }
}
