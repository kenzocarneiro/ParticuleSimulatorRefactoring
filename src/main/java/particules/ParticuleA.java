package particules;

import etats.EtatParticule;

import java.awt.*;

public class ParticuleA extends Particule {
    private final double vitesseOriginale = 10f;
    private final Color couleur = new Color(1.0f, 0.0f, 0.0f);

    @Override
    public Color getCouleur() {
        return couleur;
    }

    public ParticuleA(Champ c, double x, double y, double dC, boolean epileptique) {
        super(c, x, y, dC, epileptique);
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
    public EtatParticule handleCollision(Particule p) {
        // A collides with A (and both are Active and Excited)
        if (p.getType().equals(ParticuleType.A)) {
            p.meurt();
            this.meurt();
        }
        // A collides with B (and both are Active and Excited)
        else if (p.getType().equals(ParticuleType.B)) {
            this.champ.naissance(ParticuleType.A, p.x, p.y);
            p.setEtat(p.intervertirEtat());
            return this.intervertirEtat();
        }
        // A collides with C (and both are Active and Excited)
        else if (p.getType().equals(ParticuleType.C)) {
            p.setEtat(p.getEtat().calme());
            return this.getEtat().calme();
        }
        return this.getEtat();
    }

    public ParticuleType getType() {
        return ParticuleType.A;
    }
}
