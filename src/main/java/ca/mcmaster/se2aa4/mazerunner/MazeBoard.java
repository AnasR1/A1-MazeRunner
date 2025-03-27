package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

abstract class Game {
    public List<String> maze;

    public Game(List<String> maze) {
        this.maze = maze;
    }

    public abstract void extendBoard(String line);
    public abstract int[] findEntrance();
    public abstract int[] findExit();
}

public class MazeBoard extends Game {

    public MazeBoard(List<String> maze) {
        super(maze);
    }

    @Override
    public void extendBoard(String line) {
        maze.add(line);
    }

    @Override
    public int[] findEntrance() {

        for (int row = 1; row < maze.size() - 1; row++) {
            if (maze.get(row).isEmpty() || maze.get(row).length() < 1) {
                return new int[]{row, 0};
            }
            if (maze.get(row).charAt(0) == ' ' || maze.get(row).charAt(0) == '\0') {
                return new int[]{row, 0};
            }
        }
        return new int[]{-1, -1};
    }

    @Override
    public int[] findExit() {
        int lastCol = maze.getFirst().length() - 1;

        for (int row = 1; row < maze.size() - 1; row++) {
            if (maze.get(row).isEmpty() || maze.get(row).length() < 1 || maze.get(row).length() < lastCol) {
                return new int[]{row, lastCol};
            }
            else if (maze.get(row).charAt(lastCol) == ' ' || maze.get(row).charAt(lastCol) == '\0') {
                return new int[]{row, lastCol};
            }
        }
        return new int[]{-1, -1};
    }
}
