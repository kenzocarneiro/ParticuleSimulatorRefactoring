package particules;

import java.awt.*;

public class ParticuleB extends Particule {
    private final double vitesseOriginale = 30f;
    private final Color couleur = new Color(0.0f, 1.0f, 0.0f);

    @Override
    public Color getCouleur() {
        return couleur;
    }

    public ParticuleB(Champ c, double x, double y, double dC) {
        super(c, x, y, dC);
        vitesseCourante = vitesseOriginale;
        resetVitesse();
        passageACTIVE = 100;
        passageFINDEVIE = 300;
        passageMORT = 700;
    }

    @Override
    public double getVitesseOriginale() {
        return vitesseOriginale;
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

    public ParticuleType getType() {
        return ParticuleType.B;
    }
}
