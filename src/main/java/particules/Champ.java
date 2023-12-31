package particules;

import controleur.Controleur;
import visualisation.Sujet;

import java.util.List;


public interface Champ extends Sujet {

    int getLargeur();

    int getHauteur();

    List<Particule> getParticules();

    void ajouterUnePopulation(ParticuleType type, int nb);

    void supprimerLesParticulesDecedees();

    void setControleur(Controleur c);

    void naissance(ParticuleType type, double x, double y);

    void updatePopulation();

    void ajouterManuellement(Particule p);

    void exterminer();
}
