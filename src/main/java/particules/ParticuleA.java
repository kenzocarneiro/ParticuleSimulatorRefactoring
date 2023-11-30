package particules;

import java.awt.*;

public class ParticuleA extends Particule {
    private final double vitesseOriginale = 10f;
    private final Color couleur = new Color(1.0f, 0.0f, 0.0f);

    @Override
    public Color getCouleur() {
        return couleur;
    }

    public ParticuleA(Champ c, double x, double y, double dC) {
        super(c, x, y, dC);
        vitesseCourante = vitesseOriginale;
        resetVitesse();
        passageACTIVE = 500;
        passageFINDEVIE = 1500;
        passageMORT = 2000;
    }

    @Override
    public double getVitesseOriginale() {
        return vitesseOriginale;
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

    public ParticuleType getType() {
        return ParticuleType.A;
    }
}
