

public class Display_MinesweeperGUI {
	public static void main(String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				Minesweeper_GUI LMS = new Minesweeper_GUI();
			}
		});
	}
}
