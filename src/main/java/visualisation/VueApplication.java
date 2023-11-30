package visualisation;

import controleur.Controleur;
import particules.ParticuleType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueApplication extends JFrame{

    private static final long serialVersionUID = -1697573906837405737L;
    private static final String menu = "Insertion particules";
    private final Controleur controleur;
    private final JMenu m;
    private JOptionPane nbParticules;
    private final VueChampDeParticules affichageSimulation;
    private final VueDebug vueDebug;

    public VueApplication(String lib, Controleur c) {
        super(lib);
        this.controleur = c;
        JMenuBar mb = new JMenuBar();
        m = new JMenu(menu);
        nbParticules = new JOptionPane();

        for (int i = 0; i < ParticuleType.values().length; i++) {
            JMenuItem mi = getjMenuItem(i);
            m.add(mi);
        }

        mb.add(m);


        this.vueDebug = new VueDebug();
        mb.add(this.vueDebug.getMenuDebug());

        this.setJMenuBar(mb);
        this.add(this.vueDebug.getText(), BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.affichageSimulation = FabriqueVueChampDeParticules.getInstance().creationVueChampDeParticules(this.controleur);
        this.getContentPane().add(this.affichageSimulation, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }

    private JMenuItem getjMenuItem(int i) {
        JMenuItem mi = new JMenuItem("Particules " + ParticuleType.values()[i]);
        final int b = i;
        mi.addActionListener(new ActionListener() {

            @SuppressWarnings("static-access")
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nbParticules.showInputDialog(null, "Saisir le nombre de particules à générer !", "Nombre de particules de type " + ParticuleType.values()[b], nbParticules.QUESTION_MESSAGE);
                if (nombre == null || !isNumeric(nombre)) return;
                controleur.ajouterPopulation(Integer.parseInt(nombre), ParticuleType.values()[b]);
            }
        });
        return mi;
    }

    public void majParticulesADessiner() {
        this.affichageSimulation.updateParticulesVisibles();
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public Observer getVueDebug() {
        return this.vueDebug;
    }
}
