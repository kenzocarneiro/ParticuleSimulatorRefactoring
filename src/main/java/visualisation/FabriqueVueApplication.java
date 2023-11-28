package visualisation;

import controleur.Controleur;

public class FabriqueVueApplication {

        public static VueApplication creationVueApplication(String lib, Controleur c) {
            return new VueApplication(lib, c);
        }
}
