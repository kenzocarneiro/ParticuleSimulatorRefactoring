package particules;

public class ParticuleA extends Particule {

    public ParticuleA(Champ c, double x, double y, double dC) {
        super(c, x, y, dC);
        vitesseCourante = 10f;
        prochaineVitesse = 10f;
        passageACTIVE = 500;
        passageFINDEVIE = 1500;
        passageMORT = 2000;
    }

    public void handleCollision(Particule p) {
        if (p.getClass() == ParticuleA.class) {
            this.champ.naissance(0, this.x, this.y);
            p.meurt();
            this.meurt();
            this.champ.removeParticule(this);
            this.champ.removeParticule(p);
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
