

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 *Abstract class layout of BoardStrategy 
 */
public abstract class AbstractStrategy implements BoardStrategy
{
    private JFrame frame;
    final Container pane;
    final JPanel manPanel;
    
    //ArrayLists of Buttons
    private ArrayList<JButton> AJBlist = new ArrayList<JButton>();
    private ArrayList<JButton> BJBlist = new ArrayList<JButton>();
    private ArrayList<JButton> mancala = new ArrayList<JButton>();
    private ArrayList<JLabel> playerLabel = new ArrayList<JLabel>();
    private ArrayList<JLabel> pitLabel = new ArrayList<JLabel>();
    private ArrayList<JButton> allButtons = new ArrayList<JButton>();
  
    private JButton undoButton;
    private JButton quitButton;
    private JLabel player1Turn;
    private JLabel player2Turn;

    /**
     *Creates an instance of the board
     */
    public AbstractStrategy()
    {
        //prepares frame
        frame = new JFrame("Mancala Board");
        frame.setSize(1200, 600);
        pane = frame.getContentPane();
        pane.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        manPanel = new JPanel(null);
        
        createMancala();
        createLabels();
        createRow1Pits();
        createRow2Pits();
        insertButtons();
        createLabelsForPlayers();
        
        undoButton = new JButton("UNDO");
        quitButton = new JButton("QUIT");
        undoButton.setBounds(10, 500, 75, 75);
        quitButton.setBounds(1100, 500, 75, 75);
        manPanel.add(undoButton);
        manPanel.add(quitButton);
        player1Turn = new JLabel("Player 1 -->");
        player2Turn = new JLabel("<-- Player 2 ");
        addPlayerTurns();

    }

    /**
     * Displays starting player's turn 
     */
    public void addPlayerTurns()
    {
        player1Turn.setForeground(Color.red);
        player2Turn.setForeground(Color.blue);
        player1Turn.setBounds(450, 300, 100, 100);
        player2Turn.setBounds(450, 100, 100, 100);
        manPanel.add(player1Turn);
        manPanel.add(player2Turn);
        player2Turn.setVisible(false);
    }

    /**
     * Displays player1 turn onto the board
     * @param isTurn boolean either it is/is not player1 turn
     */
    public void player1Turn(boolean isTurn){

        player1Turn.setVisible(isTurn);
    }

    /**
     * Displays player2 turn onto the board
     * @param isTurn boolean either it is/it is not player2 turn
     */
    public void player2Turn(boolean isTurn){
        player2Turn.setVisible(isTurn);
    }
    
    public void createRow1Pits()
    {
        //creates row of A pits & adds them to list
        int x = 200;
        for (int i = 0; i < 6; i++) {
            AJBlist.add(new JButton());
            AJBlist.get(i).setBounds(x, 260, 100, 40);
            x += 100; 
            manPanel.add(AJBlist.get(i));
            String buttonNumber = "A" + (i + 1);
            AJBlist.get(i).setName(buttonNumber);
        }
    }

    /**
     * fills in player A's pits with the set number of stones
     * @param numStones sets the number of stones per pit
     */
    public void setRow1Pits (int numStones)
    {
        for (int i = 0; i < 6; i++)
        {
            int numOfStones = (numStones);
            String buttonNumber = setStones(numOfStones);
            AJBlist.get(i).setText(buttonNumber);
        }
    }
    
    public void createRow2Pits() {
        //creates row of B pits & adds them to list
        int x = 700;
        for (int i = 0; i < 6; i++) {
            BJBlist.add(new JButton());
            BJBlist.get(i).setBounds(x, 220, 100, 40);
            x -= 100;
            manPanel.add(BJBlist.get(i));
            String buttonNumber = "B" + (i + 1);
            BJBlist.get(i).setName(buttonNumber);
        }
    }
    
    /**
     * fills in player B's pits with the set number of stones
     * @param numStones numStones sets the number of stones per pit
     */
    public void setRow2Pits (int numStones)
    {
        for (int i = 0; i < 6; i++)
        {
            int numOfStones = (numStones);
            String buttonNumber = setStones(numOfStones);
            BJBlist.get(i).setText(buttonNumber);
        }
    }
   
    /*
     * Puts A & B buttons in one arraylisy
     */
    public void insertButtons() {
        for (int i = 0; i < AJBlist.size(); i++) {
            allButtons.add(AJBlist.get(i));
        }
        for (int i = 0; i < BJBlist.size(); i++) {
            allButtons.add(BJBlist.get(i));
        }
    }

    public void createLabelsForPlayers()
    {
        // Creates labels for Player A's pits
        for (int i = 0; i < 6; i++) {
            final JLabel aLabel = new JLabel("A" + (i + 1));
            aLabel.setForeground(Color.red);
            pitLabel.add(aLabel);

            int xCoord = AJBlist.get(i).getX() + AJBlist.get(i).getWidth() / 2;
            int yCoord = AJBlist.get(i).getY() + AJBlist.get(i).getHeight();
            aLabel.setBounds(xCoord, yCoord, AJBlist.get(i).getWidth(), AJBlist.get(i).getHeight() / 2);
        }

        // Creates labels for Player B's pits
        for (int i = 0; i < 6; i++) {
            final JLabel bLabel = new JLabel("B" + (i + 1));
            pitLabel.add(bLabel);
            bLabel.setForeground(Color.blue);
            int xCoord = BJBlist.get(i).getX() + BJBlist.get(i).getWidth() / 2;
            int yCoord = BJBlist.get(i).getY() - BJBlist.get(i).getHeight() / 2;
            bLabel.setBounds(xCoord, yCoord, BJBlist.get(i).getWidth(), BJBlist.get(i).getHeight() / 2);
        }
        for (JLabel label : pitLabel) {
            manPanel.add(label);
        }


        manPanel.setBounds(0, 0, 1200, 600);
        manPanel.setBorder(BorderFactory.createTitledBorder("Mancala"));
        pane.add(manPanel);
        frame.setResizable(true);
        frame.setVisible(true);
    }

    public void addPitActionListener(ActionListener l)
    {
        for(JButton button: allButtons){
            button.addActionListener(l);
        }
    }

    public void addMancalaActionListener(ActionListener l){
        for(JButton button: mancala){
            button.addActionListener(l);
        }
    }

    /**
     * Adds an action listener to the quit button
     * @param l the action listener to be added
     */
    public void addQuitActionListener(ActionListener l)
    {
        quitButton.addActionListener(l);
    }

    public void addUndoActionListener(ActionListener l)
    {
        undoButton.addActionListener(l);
    }

    /**
     * returns an arraylist that holds both playerA and playerB's mancala
     * @return an arraylist that holds both playerA and playerB's mancala
     */
    public ArrayList<JButton> getMancala()
    {
        return mancala;
    }

    /**
     * returns an arraylist that holds both playerA and playerB's mancala label
     * @return an arraylist that holds both playerA and playerB's mancala label
     */
    public ArrayList<JLabel> getPlayerLabel()
    {
        return playerLabel;
    }

    /**
     * gets the arraylist that holds playerA and playerB's pits
     * @return the arraylist that holds playerA and playerB's pits
     */
    public ArrayList<JButton> getAllButtons(){
        return allButtons;
    }

    /**
     * Takes in an integer value and returns the number of asterisks for the value
     * @param number the number of asterisks to be returns
     * @return a number of asterisks
     */
    public String setStones(int number){
        String stones = "";
        for(int i =0; i < number; i++){
            stones += "*";
        }
        return stones;
    }

    /**
     * returns the frame of the layout
     * @return the frame of the layout
     */
    public JFrame getFrame(){
        return frame;
    }
}
