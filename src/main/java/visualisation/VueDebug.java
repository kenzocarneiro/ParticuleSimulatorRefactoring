package visualisation;

import comportement.Comportement;
import comportement.ComportementEpileptique;
import comportement.ComportementNormal;
import controleur.Controleur;
import etats.EtatExciteActive;
import etats.EtatNormalActive;
import etats.EtatParticule;
import particules.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VueDebug implements Observer {
    private boolean debug = false;
    private final JLabel texte = new JLabel();
    private final int[] particules = new int[ParticuleType.values().length];
    int nbNormal, nbExcite, nbJeune, nbActive, nbFinDeVie, nbComportementNormal, nbComportementEpileptique;

    @Override
    public void updateRemove(List<Particule> p) {
        for (Particule particule : p) {
            for (int i = 0; i < ParticuleType.values().length; i++) {
                if (particule.getType().equals(ParticuleType.values()[i])) {
                    particules[i]--;
                }
            }
            if (particule.getEtat().getClass().getSimpleName().contains("Normal")) nbNormal--;
            if (particule.getEtat().getClass().getSimpleName().contains("Excite")) nbExcite--;
            if (particule.getComportement().getClass().getSimpleName().contains("Normal")) nbComportementNormal--;
            if (particule.getComportement().getClass().getSimpleName().contains("Epileptique"))
                nbComportementEpileptique--;
        }
        majNbParticulesText();
    }

    @Override
    public void updateAdd(List<Particule> p) {
        for (Particule particule : p) {
            for (int i = 0; i < ParticuleType.values().length; i++) {
                if (particule.getType().equals(ParticuleType.values()[i])) {
                    particules[i]++;
                }
            }
            nbNormal++;
            nbJeune++;
            if (particule.getComportement() instanceof ComportementNormal) nbComportementNormal++;
            if (particule.getComportement() instanceof ComportementEpileptique) nbComportementEpileptique++;
        }
        majNbParticulesText();
    }

    @Override
    public void updateEtat(EtatParticule oldEtat, EtatParticule newEtat) {
        if (oldEtat.getClass().getSimpleName().contains("Normal")) nbNormal--;
        if (oldEtat.getClass().getSimpleName().contains("Excite")) nbExcite--;
        if (oldEtat.getClass().getSimpleName().contains("Jeune")) nbJeune--;
        if (oldEtat.getClass().getSimpleName().contains("Active")) nbActive--;
        if (oldEtat.getClass().getSimpleName().contains("FinDeVie")) nbFinDeVie--;

        if (newEtat.getClass().getSimpleName().contains("Normal")) nbNormal++;
        if (newEtat.getClass().getSimpleName().contains("Excite")) nbExcite++;
        if (newEtat.getClass().getSimpleName().contains("Jeune")) nbJeune++;
        if (newEtat.getClass().getSimpleName().contains("Active")) nbActive++;
        if (newEtat.getClass().getSimpleName().contains("FinDeVie")) nbFinDeVie++;
        majNbParticulesText();
    }

    @Override
    public void updateComportement(Comportement oldComportement, Comportement newComportement) {
        if (oldComportement != null) {
            if (oldComportement instanceof ComportementNormal) nbComportementNormal--;
            if (oldComportement instanceof ComportementEpileptique) nbComportementEpileptique--;
        }

        if (newComportement instanceof ComportementNormal) nbComportementNormal++;
        if (newComportement instanceof ComportementEpileptique) nbComportementEpileptique++;
        majNbParticulesText();
    }

    private void majNbParticulesText() {
        StringBuilder texte = new StringBuilder("<html>");
        int somme = 0;
        for (int i = 0; i < ParticuleType.values().length; i++) {
            texte.append("Particules ").append(ParticuleType.values()[i]).append(" : ").append(particules[i]);
            if (i != ParticuleType.values().length - 1) texte.append(" , ");
            somme += particules[i];
        }
        if (debug) {
            texte.append(" => total : ").append(somme);
            texte.append("<br/>");
            texte.append("Normal : ").append(nbNormal).append(" , ");
            texte.append("Excite : ").append(nbExcite).append(" => total : ");
            texte.append(nbNormal + nbExcite).append("<br/>");
            texte.append("Jeune : ").append(nbJeune).append(" , ");
            texte.append("Active : ").append(nbActive).append(" , ");
            texte.append("FinDeVie : ").append(nbFinDeVie);
            texte.append(" => total : ").append(nbJeune + nbActive + nbFinDeVie).append("<br/>");
            texte.append("CompNormal : ").append(nbComportementNormal).append(" , ");
            texte.append("CompEpileptique : ").append(nbComportementEpileptique);
            texte.append(" => total : ").append(nbComportementNormal + nbComportementEpileptique).append("<br/>");
        }
        texte.append("</html>");
        this.texte.setText(texte.toString());
    }

    public Component getMenuDebug(Controleur controleur) {
        JMenu mDebug = new JMenu("Debug");
        JMenuItem miDebug = new JMenuItem("Afficher les détails");
        miDebug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                debug = !debug;
                majNbParticulesText();
            }
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
//                        case C:
//                            p = FabriqueParticuleC.getInstance().creationParticule(champ, 50, 50, 0);
//                            break;
                    }
                    assert fabriqueParticule != null;
                    Particule p = fabriqueParticule.creationParticule(controleur.getchampParticules(), 100 + i * 600, 100, Math.PI*i, estEpileptique);
                    p.setEtat(excite == 0 ? new EtatNormalActive(p) : new EtatExciteActive(p));
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

        return mDebug;
    }

    public Component getText() {
        return texte;
    }
}
