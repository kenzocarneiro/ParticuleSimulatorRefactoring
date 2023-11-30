package visualisation;

import etats.EtatParticule;
import particules.Particule;
import particules.ParticuleType;

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
            if(particule.getEtat().getClass().getSimpleName().contains("Normal")) nbNormal--;
            if(particule.getEtat().getClass().getSimpleName().contains("Excite")) nbExcite--;
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
    public void updateEtat(EtatParticule oldEtat, EtatParticule newEtat){
        if(oldEtat.getClass().getSimpleName().contains("Normal")) nbNormal--;
        if(oldEtat.getClass().getSimpleName().contains("Excite")) nbExcite--;
        if(oldEtat.getClass().getSimpleName().contains("Jeune")) nbJeune--;
        if(oldEtat.getClass().getSimpleName().contains("Active")) nbActive--;
        if(oldEtat.getClass().getSimpleName().contains("FinDeVie")) nbFinDeVie--;

        if(newEtat.getClass().getSimpleName().contains("Normal")) nbNormal++;
        if(newEtat.getClass().getSimpleName().contains("Excite")) nbExcite++;
        if(newEtat.getClass().getSimpleName().contains("Jeune")) nbJeune++;
        if(newEtat.getClass().getSimpleName().contains("Active")) nbActive++;
        if(newEtat.getClass().getSimpleName().contains("FinDeVie")) nbFinDeVie++;
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

    public Component getMenuDebug() {
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
        return mDebug;
    }

    public Component getText() {
        return texte;
    }
}
