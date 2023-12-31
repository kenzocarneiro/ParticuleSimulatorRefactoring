package particules;

import etats.EtatParticule;

import java.awt.*;

public class ParticuleB extends Particule {
    private final double vitesseOriginale = 30f;
    private final Color couleur = new Color(0.0f, 1.0f, 0.0f);

    @Override
    public Color getCouleur() {
        return couleur;
    }

    public ParticuleB(Champ c, double x, double y, double dC, boolean epileptique) {
        super(c, x, y, dC, epileptique);
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
    public EtatParticule handleCollision(Particule p) {
        // B collides with A (and both are Active and Excited)
        if (p.getType().equals(ParticuleType.A)) {
            this.champ.naissance(ParticuleType.A, this.x, this.y);
            p.setEtat(p.intervertirEtat());
            return this.intervertirEtat();
        }
        // B collides with B (and both are Active and Excited)
        else if (p.getType().equals(ParticuleType.B)) {
            this.champ.naissance(ParticuleType.A, this.x, this.y);
            p.meurt();
            this.meurt();
        }
        // B collides with C (and both are Active and Excited)
        else if (p.getType().equals(ParticuleType.C)) {
            p.setEtat(p.getEtat().calme());
            return this.getEtat().calme();
        }
        return this.getEtat();
    }

    public ParticuleType getType() {
        return ParticuleType.B;
    }
}
