/**
 * the user interference of a typing game for user to improve their typing skill
 * 
 * Plan:
 * require sentence in the textArea1
 * textArea2 for player to type in the String
 * ask player's name and create a file 
 * shows the score for each round and the best score
 *
 * @Jialu Li
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class GUI implements ActionListener,KeyListener
{

    Game g=new Game();
    String first,last;//for player's name

    JFrame mainFrame, popUp;
    JPanel panel1,panel2;
    JMenuBar mb;
    JMenu m1;
    JMenuItem m11,m22;
    JLabel ask,type,score, intS;
    JTextArea ta1,ta2;
    JButton start;

    int indexTypeThis;//index of the String[]
    Progress progress;//file

    /**
     * 
     */
    public GUI(){
        mainFrame = new JFrame("Typing Game ");
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setSize(800,500);
        //This panel will hold the lables and text fields
        //This will be added to the CENTER of the frame
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(6,1,4,20));//using grid to stack items

        //Creating the MenuBar and adding components
        //This will be added to the top (NORTH) of the frame
        mb = new JMenuBar();//menuBar
        mb.setSize(100, 400);

        m1 = new JMenu("FILE");//component of file in menubar
        m11 = new JMenuItem("Player Info");//drop down of file, open and save as
        m11.addActionListener(this);//add a actionListener - click the "Player Info" to read the file

        m1.add(m11);
        mb.add(m1);//add menuItem to menuBar

        ask=new JLabel("Type this");
        panel1.add(ask);

        ta1 = new JTextArea(); //show require sentence
        ta1.setEditable(false);//produce uneditable JTextArea.
        panel1.add(ta1);

        type=new JLabel("Your chance！");
        panel1.add(type);

        ta2 = new JTextArea();//let the player type in
        ta2.addKeyListener(this);//add keylistener of enter key-press enter then caculate score
        panel1.add(ta2);

        //a button for start
        //add the ActionListener - press the botton to start the game
        //call game() from Game class
        start=new JButton("Start");
        start.addActionListener(this);
        panel1.add(start);

        //Creating the panel at bottom and adding components
        //This panel will be added to the bottom (SOUTH) of the frame
        panel2 = new JPanel(); 
        panel2.setLayout(new GridLayout(1,4,1,5));

        score = new JLabel("Score:");
        intS = new JLabel("0 Words Per Minute");

        panel2.add(score);
        panel2.add(intS);

        mainFrame.add(mb,BorderLayout.NORTH);//add menu bar at top
        mainFrame.add(panel1,BorderLayout.CENTER);//add lables and text feids to center
        mainFrame.add(panel2,BorderLayout.SOUTH);//add panel with tim and score to the bottom
        mainFrame.setVisible(true);


        indexTypeThis=0;//the index of the TypeThis[]

        popUp=new JFrame();
        first = JOptionPane.showInputDialog(popUp, "First Name:");
        last = JOptionPane.showInputDialog(popUp, "Last Name:");
        g.player.setName(first, last);//set the player's name

        progress=new Progress(g.player);//set the player in game as a parameter for file
        progress.saveToFile("save.txt");//rewrite the file;

        JOptionPane.showMessageDialog(popUp, "Welcome "+first+" !\nHere is your instruction:\n\nFive rounds in total.\nClick Start to start the game.\nClick enter after complete each sentence will show your current score at the bottom of the window.\nYour best score will be showed after the last round.\nIf you want to check your personal info please click FILE");
    }  

    public void file(){
        progress=new Progress(g.player);//create the player file
        progress.saveToFile("save.txt");//write the file;
    }

    public void actionPerformed(ActionEvent e) {
        //actionListener for Start Jbotton
        if(e.getSource()==start)
        {

            ta1.setText(g.typeThis[0]);//set the first (index 0)String in textArea1
            ta1.setEditable(false);//produce uneditable JTextArea.
            g.sTime = g.getSTime();//get the start time when the player click the start
        }

        //actionListener for menuItem
        if(e.getSource()==m11)
        {

            progress.getFromFile("save.txt");//read the player info from file

        }
    }

    public void keyPressed(KeyEvent e){

        int currentScore=0;

        if( e.getKeyCode()==KeyEvent.VK_ENTER){

            //when the player press enter
            //get the finish time and slip the String to the input[]
            g.fTime = g.getFTime();
            g.input=ta2.getText().split(" ");

            if (g.input.length!=20)
            {
                //make sure the input is the proper length of the sentence
                //if incomplete score=0;
                JFrame warn=new JFrame();
                JOptionPane.showMessageDialog(warn, "Incomplete sentence, score is 0...");
                currentScore=0;
            }
            else
            //caculate the score for this current round 
                currentScore = g.getScore(g.compareStrings(ta1.getText().split(" ")), g.getTime());

            //test System.out.println(currentScore);
            g.player.score = g.setScore(g.getScore(g.compareStrings(ta1.getText().split(" ")), g.getTime()));
            progress.saveToFile("save.txt");//rewrite the file;

            intS.setText(currentScore+" Words Per Minute");//change the number of the score
            indexTypeThis++;//increase the index to change the String in the array

            //the size of array TypeThis is 5, if index >= 5 quit
            if(indexTypeThis>=5){

                //popUp a window to show the best score
                JFrame result=new JFrame();
                JOptionPane.showMessageDialog(result, g.player.getName()+"'s best score is: "+g.player.score+" words per minute");

                return;
            }

            ta1.setEditable(true);
            ta1.setText(g.typeThis[indexTypeThis]);//change the requirement String to the next
            ta1.setEditable(false);
            ta2.setText("");//clear the ta2 after the player press enter
            g.sTime = g.getSTime();// new start time

        }
    }

    public void keyReleased(KeyEvent arg0){//for KeyPressed()
    }

    public void keyTyped(KeyEvent arg0){//for KeyPressed()
    }

    public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            new GUI();
        }
    });
}

}  