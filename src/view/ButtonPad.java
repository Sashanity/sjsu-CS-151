package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.BlockingQueue;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import controller.ButtonController;

public class ButtonPad extends JPanel implements Observer {

	private JButton solutionButton, newGameButton, exitButton, submitButton; // buttons used in the game.
	private JCheckBox helpButton;
	private ButtonGroup keypad;
	private JToggleButton[] keypadNumbers;
	public BlockingQueue<Message> queue;
	public ButtonPad(BlockingQueue<Message> queue) {
		super(new BorderLayout());
		this.queue = queue;
		JPanel aPanel = new JPanel();
		aPanel.setLayout(new BoxLayout(aPanel, BoxLayout.Y_AXIS));
		add(aPanel, BorderLayout.NORTH);

		JPanel panelGameOptions = new JPanel();
		panelGameOptions.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelGameOptions.setBorder(BorderFactory.createTitledBorder("panelGameOptions"));
		aPanel.add(panelGameOptions);

		newGameButton = new JButton("New Game");
		newGameButton.setPreferredSize(new Dimension(100, 30));
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
					try {
						queue.put(new NewGameMessage());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
		});

		// send message to ButtonController to create new game,
		// setStartTime to System current time
		// sudokuboard.setclues(game);

		solutionButton = new JButton("Solution");
		solutionButton.setPreferredSize(new Dimension(100, 30));
		solutionButton.setToolTipText("See the solution!");
		solutionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					queue.put(new SolutionMessage());
				} catch (InterruptedException exception) {
					exception.printStackTrace();
				}
				// send message to ButtonController for sudokuBoard to setSolution(game)
				// game.resetScore();
			}

		});

		exitButton = new JButton("Exit");
		exitButton.setPreferredSize(new Dimension(100, 30));
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					queue.put(new ExitGameMessage());
				} catch (InterruptedException exception) {
					exception.printStackTrace();
				}
				// send message to ButtonController to call game.score
			}

		});

		submitButton = new JButton("Submit");
		submitButton.setPreferredSize(new Dimension(100, 30));
		submitButton.setToolTipText("Check if your input is correct!");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					queue.put(new SubmitGameMessage());
				} catch (InterruptedException exception) {
					exception.printStackTrace();
				}
				// send message to ButtonController to call game.score
			}

		});

		panelGameOptions.add(newGameButton);
		panelGameOptions.add(solutionButton);
		panelGameOptions.add(submitButton);
		panelGameOptions.add(exitButton);

		helpButton = new JCheckBox("Help", false);
		helpButton.setBackground(Color.green);
		helpButton.setPreferredSize(new Dimension(75, 30));
		helpButton.setToolTipText("Toggle on to see which cells are correct/incorrect");
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					queue.put(new HelpMessage());
				} catch (InterruptedException exception) {
					exception.printStackTrace();
				}
			}

		});
		panelGameOptions.add(helpButton);
		/*
		 * JPanel panelNumbers = new JPanel(); panelNumbers.setLayout(new
		 * FlowLayout(FlowLayout.CENTER));
		 * panelNumbers.setBorder(BorderFactory.createTitledBorder("panelNumbers"));
		 * aPanel.add(panelNumbers);
		 */
		JPanel panelNumbers = new JPanel(new GridLayout(3, 3));
		panelNumbers.setBorder(BorderFactory.createTitledBorder("panelNumbers"));
		aPanel.add(panelNumbers);

		// panelNumbers.add(helpButton); // maybe put help in diff position
		keypad = new ButtonGroup();
		keypadNumbers = new JToggleButton[9];

		for (int i = 0; i < 9; i++) {
			keypadNumbers[i] = new JToggleButton("" + (i + 1));
			keypadNumbers[i].setPreferredSize(new Dimension(50, 50));
			keypad.add(keypadNumbers[i]);
			panelNumbers.add(keypadNumbers[i]);
		}
		

	}

	public JButton getSolutionButton() {
		return solutionButton;
	}

	public JButton getNewGameButton() {
		return newGameButton;
	}

	public JButton getExitButton() {
		return exitButton;
	}

	public JCheckBox getHelpButton() {
		return helpButton;
	}

	public ButtonGroup getKeypad() {
		return keypad;
	}

	public JButton getSubmitButton() {
		return submitButton;
	}

	public JToggleButton[] getKeypadNumbers() {
		return keypadNumbers;
	}

	public void update(Observable o, Object obj) {

		// update observer
	}

}
