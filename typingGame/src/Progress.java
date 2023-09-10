/**
 * a class to store the Progress of a player as a file.
 *
 * take a player object
 * @Jialu Li
 */
import java.io.*;
public class Progress implements java.io.Serializable
{
    // instance variables - replace the example below with your own
    Player player;

    /**
     * Constructor for objects of class Progress
     */
    public Progress()
    {
        // initialise instance variables
        this.player=new Player();
    }

    public Progress(Player player)
    {
        // initialise instance variables
        this.player=player;
    }
    
    //used to write objects 
    public void saveToFile(String file){
        try{
            ObjectOutputStream out;
            out=new ObjectOutputStream(new FileOutputStream("save.txt"));
            out.writeObject(this.player);
            out.close();
            
        }
        catch(FileNotFoundException e){
            System.out.println("Can't find file.");
        }
        catch(IOException e){
            System.out.println("Error: Can't write");
        }
        
        
    }
    
    //use to read objects
    public void getFromFile(String file){
        ObjectInputStream in;
        try{
            in = new ObjectInputStream(new FileInputStream(file));
            Player a=new Player();
            a=(Player)in.readObject();
            System.out.println(a);
            in.close();
        }
        catch(ClassNotFoundException e){}
        catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    public String toString(){
        return this.player.toString();
    }
    
    public static void main(String[] agrs){
        Player a=new Player("Jialu","Li");
        Progress p =new Progress(a);
        p.saveToFile("save.txt");
        p.getFromFile("save.txt");
    }
    
}