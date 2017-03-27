package MarcoAndPolo;

/**
 * Created by chriscurreri on 3/27/17.
 */


public class Marco implements PlayerState{
    Player player;
    private String id;
    private int coords;
    private Polo[] polos;

    /**
     * Constructor for Marco
     * @param player- the player class
     * @param id- Marco's ID
     * @param coords- Marco's coordinates
     */
    public Marco(Player player, String id, int coords, Polo[] polos){
        this.player = player;
        this.id = id;
        this.coords = coords;
        this.polos = polos;
    }

    @Override
    public String getID() {
        return id;
    }

    /**
     * Gets the coordinates of Marco
     * @return the coordinates of Marco
     */
    @Override
    public int getCoords(){
        return coords;
    }

    /**
     * Sets the coordinates of Marco
     */
    @Override
    public void setCoords(int newCoords){
        coords = newCoords;
    }

    /**
     * Revives the coordinates of Polos' and displays them on the screen
     * @return the list of Polos'
     */
    @Override
    public String location() {
        String output = "Polos: " + polos[0].location();
        for(int i = 1; i < polos.length; i++){
            output = output + ", " + polos[i].location();
        }
        return output;
    }

    /**
     * Checks whether Polo is tagged. If Polo is tagged then the players switch places
     * @return true when player is tagged, false when they are not
     */
    public Marco tag(){
        for(int i = 0; i < polos.length; i++){
            if(coords == polos[i].getCoords()){
                Player newMarco = polos[i].player;
                player.setPlayerState(new Player(id, coords).playerState);
                polos[i] = (Polo) player.playerState;
                newMarco.setPlayerState(new Player(newMarco.getID(), newMarco.getCoords(), polos).playerState);
                return (Marco) newMarco.playerState;
            }
        }
        return null;
    }
}
