package visualisation;

import controleur.Controleur;
import particules.Particule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VueApplication extends JFrame implements Observer {

    /**
     *
     */
    private static final long serialVersionUID = -1697573906837405737L;
    private static final String menu = "Insertion particules";
    private static final String[] libelleTypesParticules = {"Particules A", "Particules B"};
    private int[] particules = new int[libelleTypesParticules.length];
    private static final int[] typesParticules = {0, 1};
    private final Controleur controleur;
    private final JMenu m;
    private JOptionPane nbParticules;
    private final JLabel texte = new JLabel("Particules : 0");
    private final VueChampDeParticules affichageSimulation;

    public VueApplication(String lib, Controleur c) {
        super(lib);
        this.controleur = c;
        JMenuBar mb = new JMenuBar();
        m = new JMenu(menu);
        nbParticules = new JOptionPane();

        for (int i = 0; i < libelleTypesParticules.length; i++) {
            JMenuItem mi = new JMenuItem(libelleTypesParticules[i]);
            final int b = i;
            mi.addActionListener(new ActionListener() {

                @SuppressWarnings("static-access")
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nombre = nbParticules.showInputDialog(null, "Saisir le nombre de particules à générer !", "Nombre de particules de type " + libelleTypesParticules[b], JOptionPane.QUESTION_MESSAGE);
                    controleur.ajouterPopulation(Integer.parseInt(nombre), typesParticules[b]);
                }
            });
            m.add(mi);
        }

        mb.add(m);
        this.setJMenuBar(mb);
        this.add(texte, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.affichageSimulation = FabriqueVueChampDeParticules.creationVueChampDeParticules(this.controleur);
        this.getContentPane().add(this.affichageSimulation, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }

    public void majParticulesADessiner() {
        this.affichageSimulation.updateParticulesVisibles();
    }

    @Override
    public void updateRemove(List<Particule> p) {
        for (Particule particule : p) {
            for (int i = 0; i < libelleTypesParticules.length; i++) {
                if (particule.getClass().getSimpleName().equals("Particule" + libelleTypesParticules[i].charAt(libelleTypesParticules[i].length() - 1))) {
                    particules[i]--;
                }
            }
        }
        majNbParticulesText();
    }

    @Override
    public void updateAdd(List<Particule> p) {
        for (Particule particule : p) {
            for (int i = 0; i < libelleTypesParticules.length; i++) {
                if (particule.getClass().getSimpleName().equals("Particule" + libelleTypesParticules[i].charAt(libelleTypesParticules[i].length() - 1))) {
                    particules[i]++;
                }
            }
        }
        majNbParticulesText();
    }

    private void majNbParticulesText() {
        StringBuilder texte = new StringBuilder("Particules : ");
        for (int i = 0; i < libelleTypesParticules.length; i++) {
            texte.append(particules[i]).append(" ").append(libelleTypesParticules[i]);
            if(i != libelleTypesParticules.length - 1) texte.append(", ");
        }
        this.texte.setText(texte.toString());
    }
}
