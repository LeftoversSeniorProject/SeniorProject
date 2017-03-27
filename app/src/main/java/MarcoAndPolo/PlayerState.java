package MarcoAndPolo;

/**
 * Created by chriscurreri on 3/27/17.
 */
public interface PlayerState {
    String getID();
    int getCoords();
    void setCoords(int newCoords);
    String location();

}