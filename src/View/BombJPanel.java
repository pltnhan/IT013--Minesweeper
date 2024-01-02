package View;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Controller.Listener;
import Model.CommandHistory;
import Model.MineLabel;
import Model.StaticTool;
import View.MainFrame;

public class BombJPanel extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    MineLabel[][] labels = new MineLabel[StaticTool.allrow][StaticTool.allcol];
    private Listener listener;
    private MainFrame mainFrame;
    private CommandHistory commandHistory;

    public BombJPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(new GridLayout(StaticTool.allrow, StaticTool.allcol));
        init();

    }

    private void init() {

        listener = new Listener(labels, mainFrame, commandHistory);

        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels[i].length; j++) {
                labels[i][j] = new MineLabel(i, j);
                labels[i][j].setIcon(StaticTool.iconBlank);
                labels[i][j].addMouseListener(listener);
                this.add(labels[i][j]);
            }
        }
        Border borderLow = BorderFactory.createLoweredBevelBorder();

        Border borderEmpty = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border borderCom1 = BorderFactory.createCompoundBorder(borderEmpty,
                borderLow);

        this.setBorder(borderCom1);
        this.setBackground(Color.LIGHT_GRAY);

    }

}
