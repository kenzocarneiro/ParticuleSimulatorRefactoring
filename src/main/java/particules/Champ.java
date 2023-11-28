package particules;

import controleur.Controleur;
import visualisation.Sujet;

import java.util.List;


public interface Champ extends Sujet {

    int getLargeur();

    int getHauteur();

    List<Particule> getParticules();

    void ajouterUnePopulation(int type, int nb);

    void supprimerLesParticulesDecedees();

    void setControleur(Controleur c);

    void naissance(int i, double x, double y);

    void updatePopulation();

}
