package visualisation;

import comportement.Comportement;
import comportement.ComportementType;
import controleur.Controleur;
import etats.*;
import particules.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VueDebug implements Observer {
    private boolean debug = false;
    
    private final JPanel panelInfoParticules = new JPanel();
    private final GridLayout gridLayout = new GridLayout();
    
    private Map<ParticuleType, JLabel> labelFixeNbParticules;
    private Map<ParticuleType, JLabel> labelNbParticules;
    private JLabel labelFixeTotalParticules;
    private JLabel labelTotalParticules;

    private Map<EtatType, JLabel> labelFixeNbEtats;
    private Map<EtatType, JLabel> labelNbEtats;
    private JLabel labelFixeTotalEtats;
    private JLabel labelTotalEtats;

    private Map<CycleType, JLabel> labelFixeNbCycles;
    private Map<CycleType, JLabel> labelNbCycles;
    private JLabel labelFixeTotalCycles;
    private JLabel labelTotalCycles;

    private Map<ComportementType, JLabel> labelFixeNbComportements;
    private Map<ComportementType, JLabel> labelNbComportements;
    private JLabel labelFixeTotalComportements;
    private JLabel labelTotalComportements;

    // informations sur les particules
    private Map<ParticuleType, Integer> nbParticules;
    private Map<EtatType, Integer> nbEtats;
    private Map<CycleType, Integer> nbCycles;
    private Map<ComportementType, Integer> nbComportements;

    public VueDebug() {
        gridLayout.setColumns(2 * (maxTailleEnum() + 1));
        gridLayout.setRows(0);
        panelInfoParticules.setLayout(gridLayout);
        
        // particules
        labelFixeNbParticules = new HashMap<ParticuleType, JLabel>();
        labelNbParticules = new HashMap<ParticuleType, JLabel>();
        nbParticules = new HashMap<ParticuleType, Integer>() {{
            for (ParticuleType particuleType : ParticuleType.values()) {
                put(particuleType, 0);
                labelFixeNbParticules.put(particuleType, ajoutFixeLabel(new StringBuilder("").append(particuleType)));
                labelNbParticules.put(particuleType, ajoutVariableLabel());
            }
        }};
        int ajoutVide = maxTailleEnum() - ParticuleType.values().length;
        for (int i = 0; i < 2*ajoutVide; i++) {
            ajoutFixeLabel(new StringBuilder(""), true);
        }
        labelFixeTotalParticules = ajoutFixeLabel(new StringBuilder("Total"));
        labelTotalParticules = ajoutVariableLabel();

        // etats
        labelFixeNbEtats = new HashMap<EtatType, JLabel>();
        labelNbEtats = new HashMap<EtatType, JLabel>();
        nbEtats = new HashMap<EtatType, Integer>() {{
            for (EtatType etatType : EtatType.values()) {
                put(etatType, 0);
                labelFixeNbEtats.put(etatType, ajoutFixeLabel(new StringBuilder("").append(etatType)));
                labelNbEtats.put(etatType, ajoutVariableLabel());
            }
        }};
        ajoutVide = maxTailleEnum() - EtatType.values().length;
        for (int i = 0; i < 2*ajoutVide; i++) {
            ajoutFixeLabel(new StringBuilder(""), true);
        }
        labelFixeTotalEtats = ajoutFixeLabel(new StringBuilder("Total"));
        labelTotalEtats = ajoutVariableLabel();

        // cycles
        labelFixeNbCycles = new HashMap<CycleType, JLabel>();
        labelNbCycles = new HashMap<CycleType, JLabel>();
        nbCycles = new HashMap<CycleType, Integer>() {{
            for (CycleType cycleType : CycleType.values()) {
                put(cycleType, 0);
                labelFixeNbCycles.put(cycleType, ajoutFixeLabel(new StringBuilder("").append(cycleType)));
                labelNbCycles.put(cycleType, ajoutVariableLabel());
            }
        }};
        ajoutVide = maxTailleEnum() - CycleType.values().length;
        for (int i = 0; i < 2*ajoutVide; i++) {
            ajoutFixeLabel(new StringBuilder(""), true);
        }
        labelFixeTotalCycles = ajoutFixeLabel(new StringBuilder("Total"));
        labelTotalCycles = ajoutVariableLabel();

        // comportements
        labelFixeNbComportements = new HashMap<ComportementType, JLabel>();
        labelNbComportements = new HashMap<ComportementType, JLabel>();
        nbComportements = new HashMap<ComportementType, Integer>() {{
            for (ComportementType comportementType : ComportementType.values()) {
                put(comportementType, 0);
                labelFixeNbComportements.put(comportementType, ajoutFixeLabel(new StringBuilder("").append(comportementType)));
                labelNbComportements.put(comportementType, ajoutVariableLabel());
            }
        }};
        ajoutVide = maxTailleEnum() - ComportementType.values().length;
        for (int i = 0; i < 2*ajoutVide; i++) {
            ajoutFixeLabel(new StringBuilder(""), true);
        }
        labelFixeTotalComportements = ajoutFixeLabel(new StringBuilder("Total"));
        labelTotalComportements = ajoutVariableLabel();
        
        majPanelInfoParticules();
    }

    private int maxTailleEnum() {
        int max = ParticuleType.values().length;
        if(EtatType.values().length > max)
            max = EtatType.values().length;
        if(CycleType.values().length > max)
            max = CycleType.values().length;
        if(ComportementType.values().length > max)
            max = ComportementType.values().length;

        return max;
    }

    /**
     * Label est le texte fixe
     */
    private JLabel ajoutFixeLabel(StringBuilder stringBuilder) {
        return ajoutFixeLabel(stringBuilder, false);
    }

    private JLabel ajoutFixeLabel(StringBuilder stringBuilder, boolean vide) {
        if(!vide)
            stringBuilder.append(" : ");
        JLabel label = new JLabel(stringBuilder.toString());
        this.panelInfoParticules.add(label);
        return label;
    }

    /**
     * Label qui est variable et mis à jour lors des updates
     */ 
    private JLabel ajoutVariableLabel()
    {
        JLabel label = new JLabel("0");
        // label.setSize(20, label.getHeight());
        this.panelInfoParticules.add(label);
        return label;
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
        majPanelInfoParticules();
    }

    @Override
    public void updateAdd(List<Particule> particules) {
        particules.forEach(this::incrementParticule);
        majPanelInfoParticules();
    }

    @Override
    public void updateEtat(EtatParticule oldEtat, EtatParticule newEtat) {
        decrementEtat(oldEtat);
        incrementEtat(newEtat);
        majPanelInfoParticules();
    }

    @Override
    public void updateComportement(Comportement oldComportement, Comportement newComportement) {
        decrementComportement(oldComportement);
        incrementComportement(newComportement);
        majPanelInfoParticules();
    }

    private void majPanelInfoParticules() {
        int somme = 0;
        for (ParticuleType particuleType : ParticuleType.values()) {
            labelNbParticules.get(particuleType).setText(nbParticules.get(particuleType).toString());
            somme += nbParticules.get(particuleType);
        }
        labelTotalParticules.setText(String.valueOf(somme));

        if(debug) {
            somme = 0;
            for (EtatType etatType : EtatType.values()) {
                labelNbEtats.get(etatType).setText(nbEtats.get(etatType).toString());
                somme += nbEtats.get(etatType);
            }
            labelTotalEtats.setText(String.valueOf(somme));

            somme = 0;
            for (CycleType cycleType : CycleType.values()) {
                labelNbCycles.get(cycleType).setText(nbCycles.get(cycleType).toString());
                somme += nbCycles.get(cycleType);
            }
            labelTotalCycles.setText(String.valueOf(somme));

            somme = 0;
            for (ComportementType comportementType : ComportementType.values()) {
                labelNbComportements.get(comportementType).setText(nbComportements.get(comportementType).toString());
                somme += nbComportements.get(comportementType);
            }
            labelTotalComportements.setText(String.valueOf(somme));
        }

        for (EtatType etatType : EtatType.values()) {
            labelFixeNbEtats.get(etatType).setVisible(debug);
            labelNbEtats.get(etatType).setVisible(debug);
        }
        labelFixeTotalEtats.setVisible(debug);
        labelTotalEtats.setVisible(debug);

        for (CycleType cycleType : CycleType.values()) {
            labelFixeNbCycles.get(cycleType).setVisible(debug);
            labelNbCycles.get(cycleType).setVisible(debug);
        }
        labelFixeTotalCycles.setVisible(debug);
        labelTotalCycles.setVisible(debug);

        for (ComportementType comportementType : ComportementType.values()) {
            labelFixeNbComportements.get(comportementType).setVisible(debug);
            labelNbComportements.get(comportementType).setVisible(debug);
        }
        labelFixeTotalComportements.setVisible(debug);
        labelTotalComportements.setVisible(debug);
    }

    public Component getMenuDebug(Controleur controleur) {
        JMenu mDebug = new JMenu("Debug");
        JMenuItem miDebug = new JMenuItem("Afficher les détails");
        miDebug.addActionListener(e -> {
            debug = !debug;
            majPanelInfoParticules();
        });
        mDebug.add(miDebug);

        JMenuItem miDebug2 = new JMenuItem("Test collision");
        JOptionPane jop = new JOptionPane();
        miDebug2.addActionListener(e -> {
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
                // Pour une collision entre C et A avec C excité guérisseur et A excité épilétique
                // ET   une collision entre B et A avec B excité normale et A excité épilétique
                // -> x = 100 + i * 475
                Particule p = fabriqueParticule.creationParticule(controleur.getchampParticules(), 100 + i * 475, 100, Math.PI*i, estEpileptique);
                p.setEtat(excite == 0 ? FabriqueEtat.getInstance().creationEtat(p, EtatType.NORMAL, CycleType.ACTIVE) : FabriqueEtat.getInstance().creationEtat(p, EtatType.EXCITE, CycleType.ACTIVE));
                particules.add(p);
            }
            particules.forEach(controleur::ajouterManuellement);
        });
        mDebug.add(miDebug2);

        JSlider slider = new JSlider(10, 1000, 30);
        slider.setMajorTickSpacing(10);
        slider.addChangeListener(e -> controleur.getSim().setDelaiSimulation(slider.getValue()));
        mDebug.add(slider);

        JMenuItem miDebug3 = new JMenuItem("Nettoyage de la zone");
        miDebug3.addActionListener(e -> controleur.getchampParticules().exterminer());
        mDebug.add(miDebug3);

        return mDebug;
    }

    public JPanel getPanelInfoParticules() {
        return this.panelInfoParticules;
    }
}
