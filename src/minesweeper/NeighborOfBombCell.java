package minesweeper;

public class NeighborOfBombCell extends Cell {
    public NeighborOfBombCell() {
        super();
    }

    public NeighborOfBombCell(boolean isCovered, boolean isMarked) {
        super(isCovered, isMarked);
    }

    public void getCount() {
        // Implementation for counting the number of bomb cells nearby
    }

    @Override
    public CellType getCellType() {
        return CellType.Bombneighbor;
    }

    @Override
    public String getImageName() {
        return ImageName.Covered.toString();
    }
}