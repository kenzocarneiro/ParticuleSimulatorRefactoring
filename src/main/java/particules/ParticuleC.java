package particules;

import comportement.*;
import etats.EtatParticule;

import java.awt.*;

public class ParticuleC extends Particule{
    private final double vitesseOriginale = 15f;
    private final Color couleur = new Color(0.0f, 0.0f, 1.0f);

    public ParticuleC(Champ c, double x, double y, double dC, boolean epileptique) {
        super(c, x, y, dC, epileptique);
        vitesseCourante = vitesseOriginale;
        resetVitesse();
        passageACTIVE = 10;
        passageFINDEVIE = 60;
        passageMORT = 71;
        this.comportement = FabriqueComportement.getInstance().creationComportement(this, ComportementType.GUERISON);
    }

    @Override
    public double getVitesseOriginale() {
        return vitesseOriginale;
    }

    @Override
    public EtatParticule handleCollision(Particule p) {
        p.setEtat(p.getEtat().calme());
        return this.getEtat().calme();
    }

    public ParticuleType getType() {
        return ParticuleType.C;
    }

    @Override
    public Color getCouleur() {
        return couleur;
    }

    public double getVitesseExcite() {
        return 17f;
    }
}
