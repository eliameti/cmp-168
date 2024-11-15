import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Minesweeper_GUI extends JFrame{
		private JButton [][] buttons;
		 private GridLayout defaultLayout;
		 private JPanel panel;
		 final int NUM_ROWS = 10;
		 final int NUM_COLUMNS = 10;
		 final int NUM_BOMBS=25;
		 int win=0;

		 public Minesweeper_GUI(){
			 
		    Grid game = new Grid(NUM_ROWS,NUM_COLUMNS,NUM_BOMBS);
		    game.printGrid(game.getBombGrid(),game.getCountGrid());
		    setSize(500,500);
		    setResizable(false);
		    setTitle("Carlos Minesweeper");
		    defaultLayout = new GridLayout(NUM_ROWS,NUM_COLUMNS);
		    panel = new JPanel();
		    panel.setLayout(defaultLayout);

		    buttons =  new JButton[game.getNumRows()][game.getNumColumns()];
		    
		    	for (int i = 0; i < game.getNumRows(); i++) {
					for (int j = 0; j < game.getNumColumns(); j++) {
						buttons[i][j] = new JButton(""); 
						buttons[i][j].setMargin(new Insets(0, 0, 0, 0));
						//buttons[i][j].addActionListener((ActionListener) this);
						
						
						
						int row =i;
						int col =j;
						
						buttons[i][j].addActionListener(new ActionListener() {
							
						@Override
						public void actionPerformed(ActionEvent event) {
							if (game.isBombAtLocation(row, col)) {
								displayEntire(game, buttons);
								int reply = JOptionPane.showConfirmDialog(null, "Would you like to play again?", "Bomb exploded", JOptionPane.YES_NO_OPTION);
									if (reply == JOptionPane.YES_OPTION) {
										clearBoard();
										new Minesweeper_GUI();
										} else {
											JOptionPane.showMessageDialog(null,"Good Game.");
											System.exit(EXIT_ON_CLOSE);
											}
								} else {
									
							int num = game.getCountAtLocation(row, col);
					        buttons[row][col].setText(String.valueOf(num));
					        win++;
					        if(win == ((game.getNumRows() * game.getNumColumns()) - NUM_BOMBS)){
					        	int reply = JOptionPane.showConfirmDialog(null, "Would you like to play again?", "You won", JOptionPane.YES_NO_OPTION);
					        	if (reply == JOptionPane.YES_OPTION) {
					        		clearBoard();
					        		new Minesweeper_GUI();
					        	} else {
					        		JOptionPane.showMessageDialog(null,"Good Game.");
					        		System.exit(EXIT_ON_CLOSE);
					        	} 
					        }
								}
						}
						});
						
						panel.add(buttons[i][j]);
					}
				}

		    
		    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    add(panel);
		    setVisible(true);
		      
		  }
		 


		  public void displayEntire(Grid g, JButton[][] btn){
			  
			  String bombSymbol = "B";
			  
			  for (int i = 0; i < g.getNumRows(); i++) {
				  for (int j = 0; j < g.getNumColumns(); j++) {
					  if (g.isBombAtLocation(i, j)) {
						  btn[i][j].setText(bombSymbol);
						  btn[i][j].setMargin(new Insets(0, 0, 0, 0));
						  } else {
		                    int num = g.getCountAtLocation(i,j);
		                    btn[i][j].setText(String.valueOf(num));
		                    btn[i][j].setEnabled(false);
		                    }					  
				  }				  
			  }		    
		  }
		  
		  public void clearBoard() {
				for(int row=0; row<NUM_ROWS; row++){
					for(int col=0; col < buttons[row].length; col++){
						buttons[row][col].setText("");
					}
				}
			}
		  
}
