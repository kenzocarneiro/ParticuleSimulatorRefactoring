package visualisation;

import comportement.Comportement;
import comportement.ComportementType;
import controleur.Controleur;
import etats.*;
import particules.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VueDebug implements Observer {
    private boolean debug = false;
    private final JLabel texte = new JLabel();

    Map<ParticuleType, Integer> nbParticules;

    Map<EtatType, Integer> nbEtats;
    Map<CycleType, Integer> nbCycles;
    Map<ComportementType, Integer> nbComportements;

    public VueDebug() {
        nbParticules = new HashMap<ParticuleType, Integer>() {{
            for (ParticuleType particuleType : ParticuleType.values()) {
                put(particuleType, 0);
            }
        }};

        nbEtats = new HashMap<EtatType, Integer>() {{
            for (EtatType etatType : EtatType.values()) {
                put(etatType, 0);
            }
        }};
        nbCycles = new HashMap<CycleType, Integer>() {{
            for (CycleType cycleType : CycleType.values()) {
                put(cycleType, 0);
            }
        }};
        nbComportements = new HashMap<ComportementType, Integer>() {{
            for (ComportementType comportementType : ComportementType.values()) {
                put(comportementType, 0);
            }
        }};

        majNbParticulesText();
    }

    public void incrementParticule(Particule particule) {
        nbParticules.merge(particule.getType(), 1, Integer::sum);
        incrementEtat(particule.getEtat());
        incrementComportement(particule.getComportement());
    }
    public void decrementParticule(Particule particule) {
        nbParticules.merge(particule.getType(), -1, Integer::sum);
        decrementEtat(particule.getEtat());
        decrementComportement(particule.getComportement());
    }

    public void incrementEtat(EtatParticule etatParticule) {
        nbEtats.merge(etatParticule.getEtatType(), 1, Integer::sum);
        nbCycles.merge(etatParticule.getCycleType(), 1, Integer::sum);
    }
    public void decrementEtat(EtatParticule etatParticule) {
        nbEtats.merge(etatParticule.getEtatType(), -1, Integer::sum);
        nbCycles.merge(etatParticule.getCycleType(), -1, Integer::sum);
    }

    public void incrementComportement(Comportement comportement) {
        nbComportements.merge(comportement.getComportementType(), 1, Integer::sum);
    }
    public void decrementComportement(Comportement comportement) {
        nbComportements.merge(comportement.getComportementType(), -1, Integer::sum);
    }


    @Override
    public void updateRemove(List<Particule> particules) {
        particules.forEach(this::decrementParticule);
        majNbParticulesText();
    }

    @Override
    public void updateAdd(List<Particule> particules) {
        particules.forEach(this::incrementParticule);
        majNbParticulesText();
    }

    @Override
    public void updateEtat(EtatParticule oldEtat, EtatParticule newEtat) {
        decrementEtat(oldEtat);
        incrementEtat(newEtat);
        majNbParticulesText();
    }

    @Override
    public void updateComportement(Comportement oldComportement, Comportement newComportement) {
        decrementComportement(oldComportement);
        incrementComportement(newComportement);
        majNbParticulesText();
    }

    private void majNbParticulesText() {
        StringBuilder texte = new StringBuilder("<html>");
        int somme = 0;
        for (ParticuleType particuleType : ParticuleType.values()) {
            texte.append("Particules ").append(particuleType).append(" : ").append(nbParticules.get(particuleType));
            texte.append(" , ");
            somme += nbParticules.get(particuleType);
        }
        if (debug) {
            texte.append(" => total : ").append(somme);
            texte.append("<br/>");
            nbEtats.forEach((etatType, integer) -> texte.append(etatType).append(" : ").append(integer).append(", "));
            texte.append(" => total : ").append(nbEtats.values().stream().mapToInt(Integer::intValue).sum());
            texte.append("<br/>");
            nbCycles.forEach((cycleType, integer) -> texte.append(cycleType).append(" : ").append(integer).append(", "));
            texte.append(" => total : ").append(nbCycles.values().stream().mapToInt(Integer::intValue).sum());
            texte.append("<br/>");
            nbComportements.forEach((comportementType, integer) -> texte.append(comportementType).append(" : ").append(integer).append(", "));
            texte.append(" => total : ").append(nbComportements.values().stream().mapToInt(Integer::intValue).sum());
        }
        texte.append("</html>");
        this.texte.setText(texte.toString());
    }

    public Component getMenuDebug(Controleur controleur) {
        JMenu mDebug = new JMenu("Debug");
        JMenuItem miDebug = new JMenuItem("Afficher les détails");
        miDebug.addActionListener(e -> {
            debug = !debug;
            majNbParticulesText();
        });
        mDebug.add(miDebug);

        JMenuItem miDebug2 = new JMenuItem("Test collision");
        JOptionPane jop = new JOptionPane();
        miDebug2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Particule> particules = new ArrayList<>();
                for (int i = 0; i < 2; i++) {
                    int type = jop.showOptionDialog(null, "Type de " + (i + 1), "Test collision", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"A", "B", "C"}, null);
                    // int etat = jop.showOptionDialog(null, "Cycle de vie de " + (i + 1), "Test collision", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Jeune", "Active", "FinDeVie"}, null);
                    int excite = jop.showOptionDialog(null, "Etat de " + (i + 1), "Test collision", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Normal", "Excite"}, null);
                    int epileptique = jop.showOptionDialog(null, (i + 1) + " est-t'il epileptique ?", "Test collision", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Oui", "Non"}, null);

                    if (type == -1 || excite == -1 || epileptique == -1) return;

                    ParticuleType typeParticule = ParticuleType.values()[type];
                    boolean estEpileptique = epileptique == 0;

                    FabriqueParticule fabriqueParticule = null;
                    switch (typeParticule) {
                        case A:
                            fabriqueParticule = FabriqueParticuleA.getInstance();
                            break;
                        case B:
                            fabriqueParticule = FabriqueParticuleB.getInstance();
                            break;
                        case C:
                            fabriqueParticule = FabriqueParticuleC.getInstance();
                            break;
                    }
                    assert fabriqueParticule != null;
                    Particule p = fabriqueParticule.creationParticule(controleur.getchampParticules(), 100 + i * 300, 400, Math.PI*i, estEpileptique);
                    p.setEtat(excite == 0 ? FabriqueEtat.getInstance().creationEtat(p, EtatType.NORMAL, CycleType.ACTIVE) : FabriqueEtat.getInstance().creationEtat(p, EtatType.EXCITE, CycleType.ACTIVE));
                    particules.add(p);
                }
                particules.forEach(controleur::ajouterManuellement);
            }
        });
        mDebug.add(miDebug2);

        JSlider slider = new JSlider(10, 1000, 30);
        slider.setMajorTickSpacing(10);
        slider.addChangeListener(e -> controleur.getSim().setDelaiSimulation(slider.getValue()));
        mDebug.add(slider);

        JMenuItem miDebug3 = new JMenuItem("Nettoyage de la zone");
        miDebug3.addActionListener(e -> {
            controleur.getchampParticules().exterminer();
        });
        mDebug.add(miDebug3);

        return mDebug;
    }

    public Component getText() {
        return texte;
    }
}
