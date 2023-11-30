package visualisation;

import etats.EtatExcite;
import particules.Particule;
import particules.ParticuleA;
import particules.ParticuleB;

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

        g.fillOval(x - (p.epaisseur / 2), y + (p.epaisseur / 2), (p.epaisseur), p.epaisseur);
        if (p.getEtat() instanceof EtatExcite) {
            g.drawOval(x - (p.epaisseur / 2) - 5, y + (p.epaisseur / 2) - 5, (p.epaisseur) + 10, p.epaisseur + 10);
        }
    }

    public boolean outOfDate() {
        return this.p.estMorte();
    }
}
