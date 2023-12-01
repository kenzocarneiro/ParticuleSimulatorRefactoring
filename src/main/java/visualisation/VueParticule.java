package visualisation;

import comportement.ComportementType;
import etats.EtatType;
import particules.Particule;

import java.awt.*;

public class VueParticule {
    final private Particule p;

    public VueParticule(Particule p) {
        this.p = p;
    }

    public Particule getParticule() {
        return p;
    }

    public void seDessine(Graphics g) {
        if(! this.p.isVisible())
            return;

        int x = (int) this.p.getX();
        int y = (int) this.p.getY();
        float coefColor = p.getEtat().getCoefColor();
        g.setColor(new Color((p.getCouleur().getRed() * coefColor)/255,
                (p.getCouleur().getGreen() * coefColor)/255,
                (p.getCouleur().getBlue() * coefColor)/255));

        int epaisseur = Particule.getEpaisseur();

        if (p.getComportement().getComportementType().equals(ComportementType.EPILEPTIQUE)) {
            g.fillRect(x - (epaisseur / 2), y + (epaisseur / 2), epaisseur, epaisseur);
        }
        else {
            g.fillOval(x - (epaisseur / 2), y + (epaisseur / 2), epaisseur, epaisseur);
        }

        if (p.getEtat().getEtatType().equals(EtatType.EXCITE)) {
            g.drawOval(x - (epaisseur / 2) - 5, y + (epaisseur / 2) - 5, epaisseur + 10, epaisseur + 10);
        }
    }

    public boolean outOfDate() {
        return this.p.estMorte();
    }
}
