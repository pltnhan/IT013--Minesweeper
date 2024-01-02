package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Game extends JFrame {

    private JLabel statusBar;
    private JButton bUndo;
    private JButton bRedo;
    private JButton bRule;
    private JPanel buttonPanel;
    private JTextArea textArea;
    private JPanel statusPanel;
    protected Board board;
    
    
    public Game() throws IOException {
        initUI();
    }
    
    private void initUI() throws IOException {
        // Initialize components
        statusBar = new JLabel("Flags Left");
        bUndo = new JButton("Undo");
        bRedo = new JButton("Redo");
        bRule = new JButton("Rule");
        buttonPanel = new JPanel();
        textArea = new JTextArea();
        statusPanel = new JPanel();

        // Set layout and add components
        buttonPanel.add(bUndo, BorderLayout.CENTER);
        buttonPanel.add(bRedo, BorderLayout.SOUTH);
        buttonPanel.add(bRule,  BorderLayout.NORTH);
        statusPanel.add(statusBar, BorderLayout.CENTER);

        add(buttonPanel, BorderLayout.NORTH);
//        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);

        board = new Board(statusBar, bUndo, bRedo, bRule, textArea);
        add(board);

        // Set frame properties
        setTitle("Minesweeper");
        setResizable(false);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Game ex;
			try {
				ex = new Game();
                //Automatically saves file when user exits out of game
                ex.addWindowListener(new java.awt.event.WindowAdapter() {
                        public void windowClosing(WindowEvent winEvt) {
                        try {
                            Board.saveGameStatus2File();
                        } catch (IOException ex1) {
                        }
                    }
                });
                ex.setVisible(true);
			} catch (IOException e) {
				e.printStackTrace();
			}
        });
    }
}
