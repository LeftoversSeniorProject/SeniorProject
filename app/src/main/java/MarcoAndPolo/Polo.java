package MarcoAndPolo;

/**
 * Created by chriscurreri on 3/27/17.
 */


public class Polo implements PlayerState{
    Player player;

    private String id;
    private int coords;

    /**
     * Constructor for Polo
     * @param player- the player class
     * @param id- Polo's ID
     * @param coords- Polo's coordinates
     */
    public Polo(Player player, String id, int coords){
        this.player = player;
        this.id = id;
        this.coords = coords;

    }

    @Override
    public String getID() {
        return id;
    }

    /**
     * Gets the coordinates of Polo
     * @return the coordinates of Polo
     */
    @Override
    public int getCoords(){
        return coords;
    }

    /**
     * Sets the coordinates of Polo
     */
    @Override
    public void setCoords(int newCoords){
        coords = newCoords;
    }

    /**
     * Gets the location of Polo and alerts Polo that they
     * have been spotted.
     */
    @Override
    public String location() {
        System.out.println(id + " you have been spotted!!!");
        return String.valueOf(coords);
    }
}

