import java.awt.event.ActionListener;

/**
 * @author Dev, Ira, Praneet
 */
public interface BoardStrategy
{
    /**
     * create player A and player B's mancala
     */
    void createMancala();
    
    /**
     * create player A and player B's mancala label
     */
    void createLabels();
    
    /**
     * create player A's pit
     */
    void createRow1Pits();
    
    /**
     * create player B's pits
     */
    void createRow2Pits();
    
    /**
     * Puts both player A and player B's pits into one arraylist
     */
    void insertButtons();
    
    /**
     * create the labels for the player A and player B's pits
     */
    void createLabelsForPlayers();
    
    /**
     * Adds an action listener to each of the player's pits
     * @param l action listener to be added to the pit
     */
    void addPitActionListener(ActionListener l);
    
	/**
	 * Adds an action listener to each of the player's mancala
     * @param l action listener to be added to the mancala
	 */
	void addMancalaActionListener(ActionListener l);
	
	/**
	 * Adds an action listener to the undo button
	 * @param l the action listener to be added 
	 */
	void addUndoActionListener(ActionListener l);
	
}