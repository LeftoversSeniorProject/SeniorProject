package MarcoAndPolo;

/**
 * Created by chriscurreri on 3/27/17.
 */

public class Player {
    PlayerState playerState; // the State of the player either Marco or Polo

    /**
     * Constructor for Marco
     * @param id- the name of the player
     * @param coords- the location of the player
     * @param polos- the list of Players that are Polo (Array)
     */
    public Player(String id, int coords, Polo[] polos){
        playerState = new Marco(this, id, coords, polos);
    }

    /**
     * Constructor for Polo
     * @param id- the name of the player
     * @param coords- the location of the player
     */
    public Player(String id, int coords){
        playerState = new Polo(this, id, coords);
    }

    /**
     * Sets a new Player State (Marco or Polo) for testing purposes
     * @param newPlayerState
     */
    public void setPlayerState(PlayerState newPlayerState){
        playerState = newPlayerState;
    }

    public String getID(){
        return playerState.getID();
    }

    public int getCoords(){
        return playerState.getCoords();
    }

    public void setCoords(int newCoords){
        playerState.setCoords(newCoords);
    }

    public String location(){
        return playerState.location();
    }
}
