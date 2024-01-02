package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import Model.Command;
import Model.CommandHistory;
import Model.LayBomb;
import Model.Leader;
import Model.MineLabel;
import Model.StaticTool;
import main.MainFrame;

public class Listener implements MouseListener{
    private MineLabel[][] mineLabel;
    private MainFrame mainFrame;
    private boolean isDoublePress = false;
    private CommandHistory commandHistory;

    public Listener(MineLabel[][] mineLabel, MainFrame mainFrame, CommandHistory commandHistory) {
        this.mineLabel = mineLabel;
        this.mainFrame = mainFrame;
        this.commandHistory = commandHistory;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Handle mouse click events if needed
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Handle mouse enter events if needed
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Handle mouse exit events if needed
    }

    @Override
    public void mousePressed(MouseEvent e) {
        MineLabel mineLabel = (MineLabel) e.getSource();

        int row = mineLabel.getRowx();
        int col = mineLabel.getColy();

        if (e.getModifiersEx() == InputEvent.BUTTON1_DOWN_MASK + InputEvent.BUTTON3_DOWN_MASK) {
            isDoublePress = true;
            doublePress(row, col);
        } else if ((e.getModifiersEx() & InputEvent.BUTTON1_DOWN_MASK) != 0 && mineLabel.isFlagTag() == false) {
            if (mineLabel.isExpendTag() == false) {
                mineLabel.setIcon(StaticTool.icon0);
            }
            mainFrame.getFaceJPanel().getLabelFace().setIcon(StaticTool.clickIcon);
        } else if ((e.getModifiersEx() & InputEvent.BUTTON3_DOWN_MASK) != 0 && mineLabel.isExpendTag() == false) {
            if (mineLabel.getRightClickCount() == 0) {
                mineLabel.setIcon(StaticTool.flagIcon);
                mineLabel.setRightClickCount(1);
                mineLabel.setFlagTag(true);
                StaticTool.bombCount--;
                mainFrame.getFaceJPanel().setNumber(StaticTool.bombCount);
            } else if (mineLabel.getRightClickCount() == 1) {
                mineLabel.setIcon(StaticTool.askIcon);
                mineLabel.setRightClickCount(2);
                mineLabel.setFlagTag(false);
                StaticTool.bombCount++;
                mainFrame.getFaceJPanel().setNumber(StaticTool.bombCount);
            } else {
                mineLabel.setIcon(StaticTool.iconBlank);
                mineLabel.setRightClickCount(0);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MineLabel mineLabel = (MineLabel) e.getSource();
        int row = mineLabel.getRowx();
        int col = mineLabel.getColy();

        if (isDoublePress) {
            isDoublePress = false;
            if (mineLabel.isExpendTag() == false && mineLabel.isFlagTag() == false) {
                backIcon(row, col);
            } else {
                boolean isEquals = isEquals(row, col);
                if (isEquals) {
                    doubleExpend(row, col);
                } else {
                    backIcon(row, col);
                }
            }
            mainFrame.getFaceJPanel().getLabelFace().setIcon(StaticTool.smileIcon);
        } else if (e.getModifiersEx() != InputEvent.BUTTON1_DOWN_MASK && mineLabel.isFlagTag() == false) {
            if (StaticTool.isStart == false) {
                LayBomb.lay(this.mineLabel, row, col);
                StaticTool.isStart = true;
            }
            mainFrame.getTimer().start();

            if (mineLabel.isMineTag() == true) {
                bombAction(row, col);
                mineLabel.setIcon(StaticTool.bloodIcon);
                mainFrame.getFaceJPanel().getLabelFace().setIcon(StaticTool.faultFaceIcon);
            } else {
                mainFrame.getFaceJPanel().getLabelFace().setIcon(StaticTool.smileIcon);
                expand(row, col);
            }
        }
        isWin();
    }

    private void bombAction(int row, int col) {
        for (int i = 0; i < mineLabel.length; i++) {
            for (int j = 0; j < mineLabel[i].length; j++) {
                if (mineLabel[i][j].isMineTag()) {
                    if (mineLabel[i][j].isFlagTag() == false) {
                        mineLabel[i][j].setIcon(StaticTool.blackBombIcon);
                    }
                } else {
                    if (mineLabel[i][j].isFlagTag()) {
                        mineLabel[i][j].setIcon(StaticTool.errorBombIcon);
                    }
                }
            }
        }

        mainFrame.getTimer().stop();

        for (int i = 0; i < mineLabel.length; i++) {
            for (int j = 0; j < mineLabel[i].length; j++) {
                mineLabel[i][j].removeMouseListener(this);
            }
        }
    }

    private void expand(int x, int y) {
        int count = mineLabel[x][y].getCounAround();

        if (mineLabel[x][y].isExpendTag() == false && mineLabel[x][y].isFlagTag() == false) {
            if (count == 0) {
                mineLabel[x][y].setIcon(StaticTool.num[count]);
                mineLabel[x][y].setExpendTag(true);
                for (int i = Math.max(0, x - 1); i <= Math.min(mineLabel.length - 1, x + 1); i++) {
                    for (int j = Math.max(0, y - 1); j <= Math.min(mineLabel[x].length - 1, y + 1); j++) {
                        expand(i, j);
                    }
                }
            } else {
                mineLabel[x][y].setIcon(StaticTool.num[count]);
                mineLabel[x][y].setExpendTag(true);
            }
        }
    }

    private void backIcon(int i, int j) {
        for (int x = Math.max(0, i - 1); x <= Math.min(StaticTool.allrow - 1, i + 1); x++) {
            for (int y = Math.max(0, j - 1); y <= Math.min(StaticTool.allcol - 1, j + 1); y++) {
                if (mineLabel[x][y].isFlagTag() == false && mineLabel[x][y].isExpendTag() == false) {
                    int rightClickCount = mineLabel[x][y].getRightClickCount();
                    if (rightClickCount == 2) {
                        mineLabel[x][y].setIcon(StaticTool.askIcon);
                    } else {
                        mineLabel[x][y].setIcon(StaticTool.iconBlank);
                    }
                }
            }
        }
    }

    private boolean isEquals(int i, int j) {
        int count = mineLabel[i][j].getCounAround();
        int flagCount = 0;
        for (int x = Math.max(0, i - 1); x <= Math.min(StaticTool.allrow - 1, i + 1); x++) {
            for (int y = Math.max(0, j - 1); y <= Math.min(StaticTool.allcol - 1, j + 1); y++) {
                if (mineLabel[x][y].isFlagTag()) {
                    flagCount++;
                }
            }
        }
        return count == flagCount;
    }

    private void doublePress(int i, int j) {
        for (int x = Math.max(0, i - 1); x <= Math.min(StaticTool.allrow - 1, i + 1); x++) {
            for (int y = Math.max(0, j - 1); y <= Math.min(StaticTool.allcol - 1, j + 1); y++) {
                if (mineLabel[x][y].isExpendTag() == false && mineLabel[x][y].isFlagTag() == false) {
                    int rightClickCount = mineLabel[x][y].getRightClickCount();
                    if (rightClickCount == 1) {
                        mineLabel[x][y].setIcon(StaticTool.askPressIcon);
                    } else {
                        mineLabel[x][y].setIcon(StaticTool.icon0);
                    }
                }
            }
        }
    }

    private void doubleExpend(int i, int j) {
        for (int x = Math.max(0, i - 1); x <= Math.min(StaticTool.allrow - 1, i + 1); x++) {
            for (int y = Math.max(0, j - 1); y <= Math.min(StaticTool.allcol - 1, j + 1); y++) {
                if (mineLabel[x][y].isMineTag()) {
                    if (mineLabel[x][y].isFlagTag() == false) {
                        bombAction(x, y);
                    }
                } else {
                    if (mineLabel[x][y].isFlagTag() == false) {
                        expand(x, y);
                    }
                }
            }
        }
    }

    private void isWin() {
        int needCount = StaticTool.allrow * StaticTool.allcol - StaticTool.allcount;
        int expendCount = 0;
        for (int i = 0; i < mineLabel.length; i++) {
            for (int j = 0; j < mineLabel[i].length; j++) {
                if (mineLabel[i][j].isExpendTag()) {
                    expendCount++;
                }
            }
        }

        if (needCount == expendCount) {
            for (int i = 0; i < mineLabel.length; i++) {
                for (int j = 0; j < mineLabel[i].length; j++) {
                    if (mineLabel[i][j].isMineTag() && mineLabel[i][j].isFlagTag() == false) {
                        mineLabel[i][j].setIcon(StaticTool.flagIcon);
                        mineLabel[i][j].setFlagTag(true);
                    }
                }
            }

            mainFrame.getFaceJPanel().setNumber(0);
            mainFrame.getTimer().stop();
            for (int i = 0; i < mineLabel.length; i++) {
                for (int j = 0; j < mineLabel[i].length; j++) {
                    mineLabel[i][j].removeMouseListener(this);
                }
            }

            mainFrame.getFaceJPanel().getLabelFace().setIcon(StaticTool.winFaceIcon);
            int level = StaticTool.getLevel();
            if (level != 0) {
                if (level == 1) {
                    String name = JOptionPane.showInputDialog(mainFrame, "Please Enter Your Name: ");
                    if (name != null) {
                        StaticTool.treeSetC.add(new Leader(StaticTool.timecount, name));
                    }
                } else if (level == 2) {
                    String name = JOptionPane.showInputDialog(mainFrame, "Please Enter Your Name: ");
                    if (name != null) {
                        StaticTool.treeSetZ.add(new Leader(StaticTool.timecount, name));
                    }
                } else if (level == 3) {
                    String name = JOptionPane.showInputDialog(mainFrame, "Please Enter Your Name: ");
                    if (name != null) {
                        StaticTool.treeSetG.add(new Leader(StaticTool.timecount, name));
                    }
                }
            }
        }
    }

}
