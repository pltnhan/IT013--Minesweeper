package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.StaticTool;
import main.MainFrame;

// Timer set
public class TimerListener implements ActionListener {
    private MainFrame mainFrame;

    public TimerListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StaticTool.timecount++;
        if (StaticTool.timecount > 999) {
            StaticTool.timecount = 999;

        }
        mainFrame.getFaceJPanel().setTime(StaticTool.timecount);

    }

}
