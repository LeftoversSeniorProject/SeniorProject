package MarcoAndPolo;

/**
 * Created by chriscurreri on 3/27/17.
 */

public class PlayerStateManager {


    /**
     * @param args
     */
    public static void main(String[] args) {

        String[] idArray = {"Tom", "Jen", "Jay", "Chris", "Meg", "Mike", "Abby",
                "Jane", "Tim", "Nicky", "Viet", "Dashawn", "Donald", "Lam"};
        Polo[] polos = new Polo[idArray.length-1];

        for(int i = 0; i < polos.length; i++){
            polos[i] = (Polo) new Player(idArray[i+1], i+2).playerState;
        }
        Marco marco = (Marco) new Player(idArray[0], 1, polos).playerState;

        /** Start of Demonstration **/

        System.out.println("Marco: " + marco.getID()); //Get Marco's ID

        String output = "Polos: " + polos[0].getID();
        for(int i = 1; i < polos.length; i++){
            output = output + ", " + polos[i].getID();
        }
        System.out.println(output); //Get Polos' IDs
        System.out.println();

        System.out.println("Marco looks for the Polo's location:");
        System.out.println(marco.location()); //Gets Polos' location
        System.out.println();

        boolean isTagged = false;
        Marco check = marco.tag();
        if(check != null){
            isTagged = true;
            marco = check;
        }

        System.out.println("Marco cannot tag anyone because he is not at their location:");
        System.out.println("coordinates = " + String.valueOf(marco.getCoords())
                + " tag = " + isTagged);
        System.out.println();

        System.out.println("So Marco changes his location:");
        marco.setCoords(8);
        System.out.println("new coordinates = " + String.valueOf(marco.getCoords()));
        System.out.println();

        check = marco.tag();
        if(check != null){
            isTagged = true;
            marco = check;
        }

        System.out.println("And now he can tag a Polo:");
        System.out.println("tag = " + isTagged);
        System.out.println();

        System.out.println("Now we have a new Marco and list of Polos:");
        System.out.println("Marco: " + marco.getID());

        output = "Polos: " + polos[0].getID();
        for(int i = 1; i < polos.length; i++){
            output = output + ", " + polos[i].getID();
        }
        System.out.println(output); //Get Polos' IDs
        System.out.println();


    }
}