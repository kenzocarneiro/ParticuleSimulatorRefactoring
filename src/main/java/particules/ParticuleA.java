package particules;

public class ParticuleA extends Particule {
    public ParticuleA(Champ c, double x, double y, double dC) {
        super(c, x, y, dC);
        vitesseCourante = 10f;
        resetVitesse();
        passageACTIVE = 500;
        passageFINDEVIE = 1500;
        passageMORT = 2000;
    }

    @Override
    public void resetVitesse() {
        this.prochaineVitesse = 10f;
    }

    @Override
    public void handleCollision(Particule p) {
        // A collides with A (and both are Active and Excited)
        if (p.getClass() == ParticuleA.class) {
            p.meurt();
            this.meurt();
            this.champ.removeParticule(this);
            this.champ.removeParticule(p);
        }

        // A collides with B (and both are Active and Excited)
        if (p.getClass() == ParticuleB.class) {
            this.intervertirEtat();
            p.intervertirEtat();
            this.champ.naissance(ParticuleType.A, this.x, this.y);
        }
    }
}
