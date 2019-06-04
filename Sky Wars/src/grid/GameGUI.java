package grid;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JToggleButton;

public class GameGUI extends JFrame {

	private JPanel contentPane;
	SaveGame saveGame;	
	private JButton btnMove;
	private JButton btnSaveAndExit;
	private JPanel panel;	
	java.net.URL imgSpace = getClass().getResource("/images/space.jpg");
	ImageIcon space1 = new ImageIcon(imgSpace);
	java.net.URL imgBS1 = getClass().getResource("/images/baddieship1.jpg");
	ImageIcon baddie1 = new ImageIcon(imgBS1);
	java.net.URL imgBS2 = getClass().getResource("/images/baddieship2.jpg");
	ImageIcon baddie2 = new ImageIcon(imgBS2);
	java.net.URL imgBS3 = getClass().getResource("/images/baddieship3.jpg");
	ImageIcon baddie3 = new ImageIcon(imgBS3);
	java.net.URL imgBS4 = getClass().getResource("/images/baddieship4.jpeg");
	ImageIcon baddie4 = new ImageIcon(imgBS4);
	java.net.URL imgMS = getClass().getResource("/images/mastership.jpg");
	ImageIcon masterShip = new ImageIcon(imgMS);	
	private JTextField txtOffensiveModeSelected;
	private JToggleButton tglbtnChangeMode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameGUI frame = new GameGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}//end main

	/**
	 * Create the frame.
	 */
	public GameGUI() {
		setForeground(Color.BLACK);
		setTitle("Welcome to SKY WARS!");
		setBackground(Color.BLACK);
		saveGame = new SaveGame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnMove());
		contentPane.add(getBtnSaveAndExit());
		contentPane.add(getPanel());
		panel.setLayout(new GridLayout(4, 4));
		contentPane.add(getTxtOffensiveModeSelected());
		contentPane.add(getTglbtnChangeMode());
		
		
		
		
		for(int loop =0; loop<4; loop++) {
			for(int loop2 =0; loop2<4; loop2++) {				
				JButton backgroundLabel = new JButton("", this.space1);				
				this.panel.add(backgroundLabel);
			}
		}
		updateGrid(saveGame.grid);
		
	}

	//updates the display panel/grid during the game
	
	public void updateGrid(Grid grid) {


		panel.removeAll();


		for(int row = 0; row < grid.ROW_COUNT; row++) {
			for(int col = 0; col < grid.COLUMN_COUNT; col++) {

				//displays MasterShip icon on the grid
				if(grid.isMasterShipOn(row, col) == true) {
					JButton mastershipLabel  = new JButton("", this.masterShip);					
					mastershipLabel.setPreferredSize(new Dimension(125, 125));					
					this.panel.add(mastershipLabel);

				} else {

					//gets enemy ships on square
					ArrayList<EnemyShip> enemyShipsOnGrid = saveGame.grid.grid.get(row).theSquares.get(col).theShips;

					//if no enemy ships, displays background image
					if(enemyShipsOnGrid.size() == 0) {
						JButton backgroundLabel = new JButton("", this.space1);						
						backgroundLabel.setPreferredSize(new Dimension(125, 125));						
						this.panel.add(backgroundLabel);
					}

					//displays 1 enemy ship
					if(enemyShipsOnGrid.size() == 1) {
						JButton baddie1Label = new JButton("", this.baddie1);						
						baddie1Label.setPreferredSize(new Dimension(125, 125));						
						this.panel.add(baddie1Label);
					}

					if(enemyShipsOnGrid.size() > 1) {
						switch(enemyShipsOnGrid.size()) {
						//displays 2 enemy ships

						case 2:
							JButton baddie2Label = new JButton("", this.baddie2);							
							baddie2Label.setPreferredSize(new Dimension(125, 125));	
							this.panel.add(baddie2Label);

							break;

							//displays 3 enemy ships

						case 3:
							JButton baddie3Label = new JButton("", this.baddie3);							
							baddie3Label.setPreferredSize(new Dimension(125, 125));							
							this.panel.add(baddie3Label);

							break;

							//displays 4+ enemy ships

						default:
							JButton baddie4Label = new JButton("", this.baddie4);							
							baddie4Label.setPreferredSize(new Dimension(125, 125));							
							this.panel.add(baddie4Label);

						}

					}

				}

			}

		}		

		panel.revalidate();

	} 


	private JButton getBtnMove() {
		if (btnMove == null) {
			btnMove = new JButton("JUMP");
			btnMove.setForeground(Color.WHITE);
			btnMove.setFont(new Font("Franklin Gothic Heavy", Font.BOLD, 16));
			btnMove.setBackground(Color.RED);
			btnMove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					saveGame.grid.runGame();
					updateGrid(saveGame.grid);					 
				}
			});
			btnMove.setBounds(42, 39, 134, 35);
		}
		return btnMove;
	}
	private JButton getBtnSaveAndExit() {
		if (btnSaveAndExit == null) {
			btnSaveAndExit = new JButton("Save & Exit");
			btnSaveAndExit.setForeground(Color.WHITE);
			btnSaveAndExit.setFont(new Font("Franklin Gothic Heavy", Font.PLAIN, 16));
			btnSaveAndExit.setBackground(Color.GREEN);
			btnSaveAndExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					saveGame.serialize();
					System.exit(0);
				}
			});
			btnSaveAndExit.setBounds(415, 39, 141, 35);
		}
		return btnSaveAndExit;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.BLACK);
			panel.setBounds(42, 97, 514, 435);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		}
		return panel;
	}
	private JTextField getTxtOffensiveModeSelected() {
		if (txtOffensiveModeSelected == null) {
			txtOffensiveModeSelected = new JTextField();
			txtOffensiveModeSelected.setBackground(Color.BLACK);
			txtOffensiveModeSelected.setForeground(Color.WHITE);
			txtOffensiveModeSelected.setText("");
			txtOffensiveModeSelected.setHorizontalAlignment(SwingConstants.CENTER);
			txtOffensiveModeSelected.setBounds(184, 10, 215, 19);
			txtOffensiveModeSelected.setColumns(10);
		}
		return txtOffensiveModeSelected;
	}
	private JToggleButton getTglbtnChangeMode() {
		if (tglbtnChangeMode == null) {
			tglbtnChangeMode = new JToggleButton("Change Mode");
			tglbtnChangeMode.setForeground(Color.WHITE);
			tglbtnChangeMode.setBackground(Color.BLUE);
			tglbtnChangeMode.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (tglbtnChangeMode.isSelected()) {
					txtOffensiveModeSelected.setText("Offensive Mode On");
					saveGame.grid.checkMasterShip();
					saveGame.grid.getState().changeMode();
					saveGame.grid.setMode(false);
				} else {
					txtOffensiveModeSelected.setText("Defensive Mode On");
					saveGame.grid.checkMasterShip();
					saveGame.grid.getState().changeMode();
					saveGame.grid.setMode(true);
				}
				}
			});
			tglbtnChangeMode.setBounds(242, 48, 115, 21);
		}
		return tglbtnChangeMode;
	}
}//end frame
