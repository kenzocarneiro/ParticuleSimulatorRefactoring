package visualisation;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

import controleur.Controleur;

import particules.Particule;


public class VueChampDeParticules extends JPanel {
	private static final long serialVersionUID = 5823608240299297297L;

	

	private Controleur c = null;
	private List<VueParticule> particulesADessiner;
	
	
	
	public VueChampDeParticules(Controleur c) {
		this.c = c;
		this.updateParticulesVisibles();
		setPreferredSize(new Dimension(c.getchampParticules().getLargeur()+Particule.epaisseur/2, c.getchampParticules().getHauteur()+Particule.epaisseur/2));
	}

	
	public void updateParticulesVisibles() {
		this.particulesADessiner = new ArrayList<VueParticule>();
		for (Particule p : c.getPopulationModele()) {
			this.particulesADessiner.add(new VueParticule(p));
		}
	}
	
	
	public void paint(Graphics gr) {
		Graphics2D g = (Graphics2D) gr;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setStroke(new BasicStroke(3f));
		super.paint(g);
		List<VueParticule> aSupprimer = new ArrayList<VueParticule>();
		synchronized (particulesADessiner) {
			for(VueParticule d : particulesADessiner){
				if (d.outOfDate()) {
					aSupprimer.add(d);
				}
				else {
					d.seDessine(g);
				}
			}
			this.particulesADessiner.removeAll(aSupprimer);
		}
	}

	
	
	

}
