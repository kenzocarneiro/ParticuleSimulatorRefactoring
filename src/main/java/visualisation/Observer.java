package visualisation;

import particules.Particule;

import java.util.List;

public interface Observer {
    void update(List<Particule> p);
}
