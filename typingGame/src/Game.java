/**
 * A game class for typing skill.
 *
 * @Adam Sultan
 */
import java.util.Calendar;
import java.util.Scanner;

public class Game
{
    // instance variables - replace the example below with your own
    Calendar mycal;//for timer
    Player player;
    String[] typeThis;
    String[] input;
    int time;
    int score;
    int sTime, fTime;
    //the score of this round

    /**
     * Default Constructor for objects of class Game
     */
    public Game()
    {
        //default constructer (not used unless playing the game without a player)
        this.mycal = Calendar.getInstance(); 
        this.time = 0;
        this.player = new Player();
        this.score = 0;
        this.typeThis  = new String[]{"Java is case sensitive, meaning upper and lower-case letters aren’t the same. Variables and methods all have distinction in capitalization.",
            "Primitives can hold a single piece of info, such as a letter or number. An example can be an int.",
            "Objects are essentially multiple pieces of data bundled together, which can be primitives or other objects. An example is strings.",
            "A method is a series of actions and statements, executed when called upon either within the main or another method.",
            "Link lists assemble objects into a single entity, whereby accessing the first node gives the user access to the rest."};

        this.input = new String[20];
    }

    public Game(Player p)
    {
        //constructor that takes a specific player object
        this.mycal = Calendar.getInstance(); 
        this.time = 0;
        this.player=p;
        this.score = 0;
        this.typeThis  = new String[]{"Java is case sensitive, meaning upper and lower-case letters aren’t the same. Variables and methods all have distinction in capitalization.",
            "Primitives can hold a single piece of info, such as a letter or number. An example can be an int.",
            "Objects are essentially multiple pieces of data bundled together, which can be primitives or other objects. An example is strings.",
            "A method is a series of actions and statements, executed when called upon either within the main or another method.",
            "Link lists assemble objects into a single entity, whereby accessing the first node gives the user access to the rest."};
        this.input = new String[20];
    }

    public int getSTime()
    {
        int sTime;
        //instance variable that gets a calendar
        mycal = Calendar.getInstance();
        //variable that gets time in milliseconds from the calendar
        sTime = (int)mycal.getTimeInMillis();
        return sTime;
    }

    public int getFTime()
    {
        int fTime;
        //instance variable that gets a calendar
        mycal = Calendar.getInstance();
        //variable that gets time in milliseconds from the calendar
        fTime = (int)mycal.getTimeInMillis();
        return fTime;
    }

    public int getTime()
    {
        this.time =(fTime-sTime)/1000;
        //change the users time by subtracting initial from final. divide by 1000 to get elapsed time in seconds
        return time;
    }

    public int getScore(int words, int time)
    {
        int wpm = words*60/time;
        //get words per minute with words and time
        return wpm;
    }

    public int setScore(int newScore)
    {
        if (newScore>this.player.score)
        //if they improved their score, return the new score
            return newScore;
        else
        //if they didnt improve their score, return the original best score
            return this.player.score;
    }

    public void game() {
        Scanner userInput = new Scanner(System.in);
        int currentScore = 0;
        
        try {
            System.out.println("Please Enter Your First Name: ");
            String first = userInput.nextLine();
            
            System.out.println("Please Enter Your Last Name: ");
            String last = userInput.nextLine();
            
            this.player.setName(first, last);
            
            System.out.println("Type the following phrase as fast as you can:\n");
            
            for (int i = 0; i < 5; i++) {
                System.out.println(typeThis[i]);
                this.sTime = getSTime();
                fillArray(userInput);
                
                while (this.input.length != 20) {
                    System.out.println("Incomplete sentence, go again...");
                    fillArray(userInput);
                }
                
                this.fTime = getFTime();
                currentScore = getScore(compareStrings(typeThis[i].split(" ")), getTime());
                this.player.score = setScore(getScore(compareStrings(typeThis[i].split(" ")), getTime()));
                
                System.out.println("Your score was: " + currentScore + " Words Per Minute\n\n");
            }
            
            System.out.println(this.player.getName() + "'s best score is: " + this.player.score + " words per minute");
        } finally {
            userInput.close(); // Close the Scanner when done
        }
    }

    public void fillArray(Scanner userInput) {
        //save the inputed string as a variable
        //split the string at each space
        //fill the input array with the split strings
        String phrase = userInput.nextLine();
        this.input = phrase.split(" ");
    }
    

    public int compareStrings(String[] s)
    {
        int wordsTyped = 0;
        for (int i = 0 ; i<input.length; i++)
        {
            if (s[i].equals(input[i]))
            {
                //if the words at given index are equal, add a "word typed"
                wordsTyped++;
            }
        }
        return wordsTyped;
    }

    public static void Main()
    {
        Player p = new Player ();
        //create new player
        Game testRun = new Game(p);
        //make game
        testRun.game();
        //play game
    }
}