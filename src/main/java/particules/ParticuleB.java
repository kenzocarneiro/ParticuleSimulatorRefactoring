package particules;

public class ParticuleB extends Particule {

    public ParticuleB(Champ c, double x, double y, double dC) {
        super(c, x, y, dC);
        vitesseCourante = 30f;
        prochaineVitesse = 30f;
        passageACTIVE = 100;
        passageFINDEVIE = 300;
        passageMORT = 700;
    }

    public void resetVitesse() {
        this.prochaineVitesse = 30f;
    }


    public void handleCollision(Particule p) {
        if (p.getClass() == ParticuleB.class) {
            this.champ.naissance(ParticuleType.B, this.x, this.y);
            p.meurt();
            this.meurt();
            this.champ.removeParticule(this);
            this.champ.removeParticule(p);
        }

        if (p.getClass() == ParticuleA.class) {
            this.resetVitesse();
            p.resetVitesse();
            this.champ.naissance(ParticuleType.A, this.x, this.y);
        }
    }
}
