package visualisation;

import controleur.Controleur;
import particules.Particule;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class VueChampDeParticules extends JPanel {
    private static final long serialVersionUID = 5823608240299297297L;

    private Controleur c = null;
    private List<VueParticule> particulesADessiner;

    public VueChampDeParticules(Controleur c) {
        this.c = c;
        this.updateParticulesVisibles();
        setPreferredSize(new Dimension(c.getchampParticules().getLargeur() + Particule.epaisseur / 2, c.getchampParticules().getHauteur() + Particule.epaisseur / 2));
    }

    public void updateParticulesVisibles() {
        this.particulesADessiner = new ArrayList<>();
        for (Particule p : c.getPopulationModele()) {
            this.particulesADessiner.add(FabriqueVueParticule.getInstance().creationVueParticule(p));
        }
    }

    public void paint(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(new BasicStroke(3f));
        super.paint(g);
        List<VueParticule> aSupprimer = new ArrayList<>();
        synchronized (particulesADessiner) {
            for (VueParticule d : particulesADessiner) {
                if (d.outOfDate()) {
                    aSupprimer.add(d);
                } else {
                    d.seDessine(g);
                }
            }
            this.particulesADessiner.removeAll(aSupprimer);
        }
    }
}
