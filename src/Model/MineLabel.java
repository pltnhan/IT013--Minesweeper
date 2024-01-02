package Model;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MineLabel extends JLabel {
    private boolean mineTag;
    private boolean expendTag;
    private boolean flagTag;
    private int rowx;
    private int coly;
    private int counAround;
    private int rightClickCount;

    public MineLabel(int x, int y) {
        this.rowx = x;
        this.coly = y;
    }

    public boolean isMineTag() {
        return mineTag;
    }

    public void setMineTag(boolean mineTag) {
        this.mineTag = mineTag;
    }

    public boolean isExpendTag() {
        return expendTag;
    }

    public void setExpendTag(boolean expendTag) {
        this.expendTag = expendTag;
    }

    public boolean isFlagTag() {
        return flagTag;
    }

    public void setFlagTag(boolean flagTag) {
        this.flagTag = flagTag;
    }

    public int getRowx() {
        return rowx;
    }

    public void setRowx(int rowx) {
        this.rowx = rowx;
    }

    public int getColy() {
        return coly;
    }

    public void setColy(int coly) {
        this.coly = coly;
    }

    public int getCounAround() {
        return counAround;
    }

    public void setCounAround(int counAround) {
        this.counAround = counAround;
    }

    public int getRightClickCount() {
        return rightClickCount;
    }

    public void setRightClickCount(int rightClickCount) {
        this.rightClickCount = rightClickCount;
    }

    // Method to reveal the cell
    public void reveal() {
        if (!expendTag && !flagTag) {
            setIcon(getIconForCount(counAround)); // Customize this method based on your requirements
            expendTag = true;
        }
    }

    // Method to hide the cell
    public void hide() {
        if (expendTag && !flagTag) {
            setIcon(null); // Set it to null or any default icon you want
            expendTag = false;
        }
    }

    // Example method to get icon based on the count around the cell
    private ImageIcon getIconForCount(int count) {
        // Customize this method based on your icon logic
        // For example, you can have an array of icons for different counts
        // and return the appropriate one based on the count.
        return null; // Replace this with your logic
    }
}
