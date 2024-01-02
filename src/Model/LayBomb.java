package Model;

import java.util.Random;

// bomb laying
public class LayBomb {
    public static void lay(MineLabel[][] label, int row, int col) {

        // Random bomb
        int count = 0;
        Random random = new Random();
        while (count < StaticTool.allcount) {
            int x = random.nextInt(StaticTool.allrow);
            int y = random.nextInt(StaticTool.allcol);
            if (label[x][y].isMineTag() == false && !(x == row && y == col)) {
                label[x][y].setMineTag(true);
                label[x][y].setCounAround(9);
                if (StaticTool.isHole == true) {
                    label[x][y].setIcon(StaticTool.holeIcon);
                }

                count++;
            }

        }

        computeBomb(label);
    }

    // Count bomb
    public static void computeBomb(MineLabel label[][]) {

        for (int i = 0; i < label.length; i++) {
            for (int j = 0; j < label[i].length; j++) {
                if (label[i][j].isMineTag() == false) {
                    int count = 0;
                    for (int x = Math.max(0, i - 1); x <= Math.min(
                            StaticTool.allrow - 1, i + 1); x++) {
                        for (int y = Math.max(0, j - 1); y <= Math.min(
                                StaticTool.allcol - 1, j + 1); y++) {
                            if (label[x][y].isMineTag() == true) {
                                count++;

                            }
                        }
                    }

                    label[i][j].setCounAround(count);

                }

            }

        }

    }
}
