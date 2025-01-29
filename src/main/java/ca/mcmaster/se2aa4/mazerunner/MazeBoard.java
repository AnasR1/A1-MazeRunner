package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

abstract class Game {
    protected List<String> maze;

    public Game(List<String> maze) {
        this.maze = maze;
    }

    abstract void extendBoard(String line);
    abstract int[] findEntrance();
    abstract int[] findExit();
}

public class MazeBoard extends Game {
    private static final int[][] DIRECTIONS = {
            {-1, 0}, // North
            {0, 1},  // East
            {1, 0},  // South
            {0, -1}  // West
    };

    public MazeBoard(List<String> maze) {
        super(maze);
    }

    @Override
    void extendBoard(String line) {
        maze.add(line);
    }

    @Override
    int[] findEntrance() {
        for (int row = 1; row < maze.size() - 1; row++) {
            if (maze.get(row).charAt(0) == ' ') {
                return new int[]{row, 0};
            }
        }
        return new int[]{-1, -1};
    }

    @Override
    int[] findExit() {
        int lastCol = maze.getFirst().length() - 1;

        for (int row = 1; row < maze.size() - 1; row++) {
            if (maze.get(row).charAt(lastCol) == ' ') {
                return new int[]{row, lastCol};
            }
        }
        return new int[]{-1, -1};
    }
}
