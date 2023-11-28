package visualisation;

import controleur.Controleur;

public class FabriqueVueChampDeParticules {

        public static VueChampDeParticules creationVueChampDeParticules(Controleur c) {
            return new VueChampDeParticules(c);
        }
}
