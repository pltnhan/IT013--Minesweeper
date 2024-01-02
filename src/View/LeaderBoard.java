package View;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Model.Leader;
import Model.StaticTool;
import View.MainFrame;
import Model.Leader1;

public class LeaderBoard extends JDialog {

    private static final long serialVersionUID = 1L;
    JTextArea textArea = new JTextArea();
    private JPanel panel = null;
    private int level = 0;

    // Leaderboard
    public LeaderBoard(int level, MainFrame mainFrame) {
        super(mainFrame);
        this.level = level;
        this.setTitle("Leaderboard");
        this.add(getPanel());
        this.setSize(new Dimension(220, 150));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);
    }

    public JPanel getPanel() {
        textArea.setEditable(false);
        textArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        addMessage();
        panel = new JPanel(new BorderLayout());
        panel.add(scrollPane);
        return panel;
    }

    // Easy leaderboard
    private void addMessage() {
        if (level == 1) {
            for (Leader Leader : StaticTool.treeSetC) {
                textArea.append(Leader.toString() + "\n");
            }
            // Medium leaderboard
        } else if (level == 2) {
            for (Leader Leader : StaticTool.treeSetZ) {
                textArea.append(Leader.toString() + "\n");
            }
            // Hard leaderboard
        } else {
            for (Leader Leader : StaticTool.treeSetG) {
                textArea.append(Leader.toString() + "\n");
            }
        }
    }

}
