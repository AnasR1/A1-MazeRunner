package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final Logger logger = LogManager.getLogger();

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
            if (maze.get(row).isEmpty() || maze.get(row).length() < 1) {
                continue;
            }
            logger.info(row);
            if (maze.get(row).charAt(0) == ' ' || maze.get(row).charAt(0) == '\0') {
                return new int[]{row, 0};
            }
        }
        return new int[]{-1, -1};
    }

    @Override
    int[] findExit() {
        int lastCol = maze.getFirst().length() - 1;

        for (int row = 1; row < maze.size() - 1; row++) {
            if (maze.get(row).isEmpty() || maze.get(row).length() < 1) {
                continue;
            }
            logger.info(row);
            if (maze.get(row).charAt(lastCol) == ' ' || maze.get(row).charAt(lastCol) == '\0') {
                return new int[]{row, lastCol};
            }
        }
        return new int[]{-1, -1};
    }
}
