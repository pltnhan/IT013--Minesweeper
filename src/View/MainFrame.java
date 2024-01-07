package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Controller.TimerListener;
import Model.StaticTool;

public class MainFrame extends JFrame implements ActionListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private BombJMenuBar menuBar = new BombJMenuBar(this);

    private FaceJPanel faceJPanel = new FaceJPanel(this);

    private BombJPanel bombJPanel = new BombJPanel(this);

    private TimerListener timerListener = new TimerListener(this);

    private Timer timer = new Timer(1000, timerListener);


    // Main interface of the game
    public MainFrame() {
        init();
        // JButton undo = new JButton();
        // undo.setText("Undo");
        // undo.addActionListener(e -> {

        // });
        // JButton leaderBoard = new JButton();
        // leaderBoard.setText("Leader Board");
        // leaderBoard.addActionListener(e -> {

        // });

        this.setIconImage(StaticTool.imageIcon.getImage());
        this.setTitle("MineSweeper QnQ");
        this.setSize(new Dimension(3000, 300));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
        // this.add(leaderBoard);
        // this.add(undo);


    }

    // Main
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        new MainFrame();

    }

    private void init() {
        this.setJMenuBar(menuBar);
        this.add(faceJPanel, BorderLayout.NORTH);
        this.add(bombJPanel);

    }

    // Restart the game
    public void reStartGame() {

        this.remove(faceJPanel);
        this.remove(bombJPanel);

        StaticTool.bombCount = StaticTool.allcount;
        StaticTool.timecount = 0;
        StaticTool.isStart = false;

        faceJPanel = new FaceJPanel(this);
        bombJPanel = new BombJPanel(this);
        this.add(faceJPanel, BorderLayout.NORTH);
        this.add(bombJPanel);
        this.pack();
        this.validate();

        getTimer().stop();

    }

    public FaceJPanel getFaceJPanel() {
        return faceJPanel;
    }

    public BombJPanel getBombJPanel() {
        return bombJPanel;
    }

    public Timer getTimer() {
        return timer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}
