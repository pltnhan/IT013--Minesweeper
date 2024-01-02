package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Model.StaticTool;
import View.AboutSweeping;
import View.LeaderBoard;
import View.MainFrame;
import View.UserDefinedJDialog;

public class BombJMenuBar extends JMenuBar {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // All the Menu Panel
    JMenu menuGame = new JMenu("Game(G)");

    JMenu menuHelp = new JMenu("Help(H)");

    JMenuItem menuItemStart = new JMenuItem("New Game");

    JMenuItem menuItemC = new JMenuItem("Easy");

    JMenuItem menuItemZ = new JMenuItem("Medium");

    JMenuItem menuItemG = new JMenuItem("Hard");

    JMenu menuHero = new JMenu("Leaderboard");
    JMenuItem menuHeroC = new JMenuItem("Easy Leaderboard");
    JMenuItem menuHeroZ = new JMenuItem("Medium Leaderboard");
    JMenuItem menuHeroG = new JMenuItem("Hard Leaderboard");
    JMenuItem menuItemCustom = new JMenuItem("Custom");
    JMenuItem menuItemExit = new JMenuItem("Exit");

    JMenuItem menuItemAbout = new JMenuItem("Game Rules");
    JMenuItem menuItemHole = new JMenuItem("Mines Hint");

    MainFrame mainFrame;

    public BombJMenuBar(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        init();
    }

    private void init() {
        menuGame.setMnemonic('G'); // Alt + G to access Game(G)
        menuHelp.setMnemonic('H'); // Alt + H to access Help(H)

        // New game Click
        menuGame.add(menuItemStart);

        menuItemStart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.reStartGame();
            }
        });

        // Easy Click
        menuGame.addSeparator();
        menuGame.add(menuItemC);
        menuItemC.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                StaticTool.allrow = 9;
                StaticTool.allcol = 9;
                StaticTool.allcount = 10;
                mainFrame.reStartGame();
            }
        });

        // Medium Click
        menuGame.add(menuItemZ);
        menuItemZ.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                StaticTool.allrow = 16;
                StaticTool.allcol = 16;
                StaticTool.allcount = 40;
                mainFrame.reStartGame();
            }
        });

        // Hard Click
        menuGame.add(menuItemG);
        menuItemG.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                StaticTool.allrow = 16;
                StaticTool.allcol = 30;
                StaticTool.allcount = 99;
                mainFrame.reStartGame();
            }
        });

        // Custom Click
        menuGame.addSeparator();
        menuGame.add(menuItemCustom);
        menuItemCustom.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new UserDefinedJDialog(mainFrame);

            }
        });

        // Put mouse at leaderboard
        menuGame.addSeparator();
        menuGame.add(menuHero);
        menuHero.add(menuHeroC);
        menuHeroC.addActionListener(new ActionListener() {

            // Easy Leaderboard Click
            @Override
            public void actionPerformed(ActionEvent e) {
                new LeaderBoard(1, mainFrame);

            }
        });

        menuHero.add(menuHeroZ);
        menuHeroZ.addActionListener(new ActionListener() {

            // Medium Leaderboard Click
            @Override
            public void actionPerformed(ActionEvent e) {
                new LeaderBoard(2, mainFrame);

            }
        });
        menuHero.add(menuHeroG);
        menuHeroG.addActionListener(new ActionListener() {

            // Hard Leaderboard Click
            @Override
            public void actionPerformed(ActionEvent e) {
                new LeaderBoard(3, mainFrame);

            }
        });

        // Exit Click
        menuGame.addSeparator();
        menuGame.add(menuItemExit);
        menuItemExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(JFrame.EXIT_ON_CLOSE);

            }
        });

        // Game Rules Click
        menuHelp.add(menuItemAbout);
        menuItemAbout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new AboutSweeping(mainFrame);

            }
        });

        // Mines Hint Click
        menuHelp.add(menuItemHole);
        menuItemHole.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                StaticTool.isHole = true;

            }
        });


        this.add(menuGame);
        this.add(menuHelp);

    }

}
