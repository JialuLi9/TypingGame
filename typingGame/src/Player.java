/**
 * This program contains necessary information and functions for a player playing the game.
 *
 * @ Charles Lin
 */
public class Player implements java.io.Serializable
{
    // instance variables
    private String firstName, lastName;
    int score;

    /**
     * Constructor for objects of class Player
     */
    public Player(String fn, String ln)
    {
        // initialise instance variables
        firstName = fn;
        lastName = ln;
        score = 0;
    }
    public Player()
    {
        // initialise instance variables
        firstName = "n/a";
        lastName = "n/a";
        score = 0;
    }
        
    /**
     * Get name of player
     */
    public String getName() {
        return firstName + " " + lastName;
    }
    
    /**
     * Change name of player
     */
    public void setName(String fn2, String ln2) {
        firstName = fn2;
        lastName = ln2;
    }
    
    /**
     * Output variables
     */
    public String toString() {
        String out = "Player: " + firstName + " " + lastName + "\nScore: " + score;
        return out;
    }
    
    /**
     * Testing methods
     */
    public static void main (String args[]) {
        Player p1 = new Player("George", "Jerry");
        p1.getName();
        p1.setName("Joseph", "Jones");
        System.out.println(p1);
    }
}