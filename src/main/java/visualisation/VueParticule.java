package visualisation;

import etats.EtatExcite;
import particules.Particule;

import java.awt.*;

public class VueParticule {
    private Particule p = null;
    private Color couleurParticule = null;

    public VueParticule(Particule p) {
        this.p = p;
    }

    public Particule getParticule() {
        return p;
    }

    public void seDessine(Graphics g) {
        int x = (int) this.p.getX();
        int y = (int) this.p.getY();
        float coefColor = p.getEtat().getCoefColor();
        g.setColor(new Color((p.getCouleur().getRed() * coefColor)/255,
                (p.getCouleur().getGreen() * coefColor)/255,
                (p.getCouleur().getBlue() * coefColor)/255));

        int epaisseur = Particule.getEpaisseur();
        g.fillOval(x - (epaisseur / 2), y + (epaisseur / 2), epaisseur, epaisseur);
        if (p.getEtat() instanceof EtatExcite) {
            g.drawOval(x - (epaisseur / 2) - 5, y + (epaisseur / 2) - 5, epaisseur + 10, epaisseur + 10);
        }
    }

    public boolean outOfDate() {
        return this.p.estMorte();
    }
}
