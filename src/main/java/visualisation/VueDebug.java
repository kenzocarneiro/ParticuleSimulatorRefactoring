package visualisation;

import controleur.Controleur;
import etats.EtatExciteActive;
import etats.EtatNormalActive;
import etats.EtatParticule;
import particules.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VueDebug implements Observer {
    private boolean debug = false;
    private final JLabel texte = new JLabel();
    private final int[] particules = new int[ParticuleType.values().length];
    int nbNormal, nbExcite, nbJeune, nbActive, nbFinDeVie;

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
            texte.append(" => total : ").append(nbJeune + nbActive + nbFinDeVie);
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
                int type1 = jop.showOptionDialog(null, "Type de 1", "Test collision", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"A", "B", "C"}, null);
//                int etat = jop.showOptionDialog(null, "Cycle de vie de 1", "Test collision", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Jeune", "Active", "FinDeVie"}, null);
                int excite = jop.showOptionDialog(null, "Etat de 1", "Test collision", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Normal", "Excite"}, null);

                int type2 = jop.showOptionDialog(null, "Type de 2", "Test collision", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"A", "B", "C"}, null);
//                int etat2 = jop.showOptionDialog(null, "Cycle de vie de 2", "Test collision", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Jeune", "Active", "FinDeVie"}, null);
                int excite2 = jop.showOptionDialog(null, "Etat de 2", "Test collision", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Normal", "Excite"}, null);

                ParticuleType typeParticule1 = ParticuleType.values()[type1];
                ParticuleType typeParticule2 = ParticuleType.values()[type2];
                Particule p1 = null;
                switch (typeParticule1) {
                    case A:
                        p1 = FabriqueParticuleA.getInstance().creationParticule(controleur.getchampParticules(), 100, 100, 0);
                        break;
                    case B:
                        p1 = FabriqueParticuleB.getInstance().creationParticule(controleur.getchampParticules(), 100, 100, 0);
                        break;
//                    case C:
//                        p1 = FabriqueParticuleC.getInstance().creationParticule(champ, 50, 50, 0);
//                        break;
                }
                Particule p2 = null;
                switch (typeParticule2) {
                    case A:
                        p2 = FabriqueParticuleA.getInstance().creationParticule(controleur.getchampParticules(), 800, 100, Math.PI);
                        break;
                    case B:
                        p2 = FabriqueParticuleB.getInstance().creationParticule(controleur.getchampParticules(), 800, 100, Math.PI);
                        break;
//                    case C:
//                        p2 = FabriqueParticuleC.getInstance().creationParticule(champ, 50, 50, 0);
                    // break;
                }
                if (excite == 0) p1.setEtat(new EtatNormalActive(p1));
                if (excite2 == 0) p2.setEtat(new EtatNormalActive(p2));

                if (excite == 1) p1.setEtat(new EtatExciteActive(p1));
                if (excite2 == 1) p2.setEtat(new EtatExciteActive(p2));


                controleur.getchampParticules().addParticule(p1);
                controleur.getchampParticules().addParticule(p2);
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
