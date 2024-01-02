package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Model.StaticTool;
import View.MainFrame;

public class AboutSweeping extends JDialog {

    private static final long serialVersionUID = 1L;
    AboutSweeping sweeping = null;
    private JLabel labelIcon;
    private JLabel labelOne;
    private JLabel labelTwo;
    private JLabel labelThree;
    private JLabel labelFour;
    private JLabel labelFive;
    private Box boxOne;
    private Box boxTwo;
    private Box boxThree;
    private Box boxFour;
    private Box boxFive;
    private JPanel panelT;

    // Games rule
    public AboutSweeping(MainFrame mainFrame) {

        super(mainFrame);
        sweeping = this;
        this.setTitle("MineSweeper Rules:");
        this.add(getPanel());
        this.setSize(new Dimension(300, 200));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setVisible(true);

    }

    // Panel game rules
    private JPanel getPanel() {
        JPanel panel = new JPanel();
        labelIcon = new JLabel(StaticTool.imageIcon);
        labelOne = new JLabel("MINESWEEPER");
        boxOne = Box.createHorizontalBox();
        boxOne.add(labelIcon);
        boxOne.add(Box.createHorizontalStrut(20));
        boxOne.add(labelOne);
        labelTwo = new JLabel("Avoid All Bombs");
        boxTwo = Box.createHorizontalBox();
        boxTwo.add(labelTwo);
        labelThree = new JLabel("Expose All Empty Spaces");
        boxThree = Box.createHorizontalBox();
        boxThree.add(labelThree);
        labelFour = new JLabel("Tips 1: Use Numbers to Determine Bomb Spots");
        boxFour = Box.createHorizontalBox();
        boxFour.add(labelFour);
        labelFive = new JLabel("Tips 2: Use Flag to Mark Suspected Bomb Spots");
        boxFive = Box.createHorizontalBox();
        boxFive.add(labelFive);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(boxOne);
        panel.add(boxTwo);
        panel.add(boxThree);
        panel.add(boxFour);
        panel.add(boxFive);

        // Close button
        JButton button = new JButton("Close");
        button.addActionListener(new ActionListener() {

            // Click close check
            @Override
            public void actionPerformed(ActionEvent e) {
                sweeping.dispose();

            }
        });
        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel1.add(button);
        panel.add(panel1);
        Border border = BorderFactory.createEtchedBorder();
        panel.setBorder(border);
        panelT = new JPanel(new BorderLayout());
        Border border2 = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        panelT.add(panel);
        panelT.setBorder(border2);

        return panelT;

    }

}
