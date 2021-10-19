package dungeonRunner;

import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DungeonRunner extends JFrame {
	private ImageIcon groundIcon = new ImageIcon("\\Users\\Zephyrus\\Downloads\\ground.jpg");
	private ImageIcon heroIcon = new ImageIcon("\\Users\\Zephyrus\\Downloads\\hero.jpg");
	private ImageIcon wallIcon = new ImageIcon("\\Users\\Zephyrus\\Downloads\\wall.jpg");
	private JPanel panel = new JPanel();
	private int width = 21;
	private int height = 21;
	private JLabel[][] way = new JLabel[height][width];
	private int[] positionNumber = new int[width];
	private int xPosition = 1;
	private int yPosition = 1;

	public DungeonRunner() {
		super("DungeonRunner");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(38 * width, 39 * height);
		panel = game();
		setVisible(true);
		add(panel);
		setResizable(false);
		addKeyListener(getController());
	}

	public JPanel game() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(height, width, 0, 0));
		init(panel);
		return panel;
	}

	private void init(JPanel panel) {
		int place = 0;
		for (int i = 0; i < height; i++) {
			if (i % 2 == 0) {
				if (i == 0 || i == height - 1) {
					for (int j = 0; j < width; j++) {
						way[i][j] = new JLabel(wallIcon);
					}
				} else {
					place = randomPlace();
					positionNumber[i] = place;
					for (int j = 0; j < width; j++) {
						way[i][j] = new JLabel(wallIcon);
					}
					way[i][place] = new JLabel(groundIcon);
				}
			} else {
				for (int j = 0; j < width; j++) {
					if (j == 0 || j == width - 1) {
						way[i][j] = new JLabel(wallIcon);
					} else {
						way[i][j] = new JLabel(groundIcon);
					}
				}
			}
			way[1][1] = new JLabel(heroIcon);
			way[height - 1][width - 2] = new JLabel(groundIcon);
		}

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				panel.add(way[i][j]);
			}
		}

	}

	private int randomPlace() {
		return (int) (Math.random() * (width - 2)) + 1;
	}

	private KeyAdapter controller = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_D) {
				if (yPosition != width - 2 && xPosition % 2 != 0) {
					way[xPosition][yPosition].setIcon(groundIcon);
					way[xPosition][++yPosition].setIcon(heroIcon);
				}
			} else if (e.getKeyCode() == KeyEvent.VK_A) {
				if (yPosition != 1 && xPosition % 2 != 0) {
					way[xPosition][yPosition].setIcon(groundIcon);
					way[xPosition][--yPosition].setIcon(heroIcon);
				}
			}else if (e.getKeyCode() == KeyEvent.VK_S) {
				if (xPosition % 2 != 0 && yPosition == positionNumber[xPosition+1]) {
					way[xPosition][yPosition].setIcon(groundIcon);
					way[++xPosition][yPosition].setIcon(heroIcon);
				} else if(xPosition % 2 == 0) {
					way[xPosition][yPosition].setIcon(groundIcon);
					way[++xPosition][yPosition].setIcon(heroIcon);
				}
			}else if (e.getKeyCode() == KeyEvent.VK_W) {
				if (xPosition % 2 != 0 && yPosition == positionNumber[xPosition-1]) {
					way[xPosition][yPosition].setIcon(groundIcon);
					way[--xPosition][yPosition].setIcon(heroIcon);
				} else if(xPosition % 2 == 0) {
					way[xPosition][yPosition].setIcon(groundIcon);
					way[--xPosition][yPosition].setIcon(heroIcon);
				}
			}
		}

	};

	public KeyAdapter getController() {
		return controller;
	}

}
