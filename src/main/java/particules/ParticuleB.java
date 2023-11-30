package particules;

public class ParticuleB extends Particule {
    public ParticuleB(Champ c, double x, double y, double dC) {
        super(c, x, y, dC);
        vitesseCourante = 30f;
        resetVitesse();
        passageACTIVE = 100;
        passageFINDEVIE = 300;
        passageMORT = 700;
    }

    @Override
    public void resetVitesse() {
        this.prochaineVitesse = 30f;
    }

    @Override
    public void handleCollision(Particule p) {
        // B collides with B (and both are Active and Excited)
        if (p.getClass() == ParticuleB.class) {
            this.champ.naissance(ParticuleType.A, this.x, this.y);
            p.meurt();
            this.meurt();
            this.champ.removeParticule(this);
            this.champ.removeParticule(p);
        }
        // B collides with A (and both are Active and Excited)
        if (p.getClass() == ParticuleA.class) {
            this.intervertirEtat();
            p.intervertirEtat();
            this.champ.naissance(ParticuleType.A, this.x, this.y);
        }
    }
}
