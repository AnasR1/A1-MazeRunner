package ca.mcmaster.se2aa4.mazerunner;

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
    public MazeBoard(List<String> maze) {
        super(maze);
    }

    @Override
    void extendBoard(String line) {
        maze.add(line);
    }

    @Override
    int[] findEntrance() {
        for (int row = 0; row <  maze.size(); row++) {
            if (maze.get(row).charAt(0) == ' ') {
                return new int[]{row, 0};
            }
        }
        return new int[]{-1, -1};
    }

    @Override
    int[] findExit() {
        for (int row = 0; row < maze.size(); row++) {
            if (maze.get(row).charAt(maze.get(row).length() - 1) == ' ') {
                return new int[]{row, maze.get(row).length() - 1};
            }
        }
        return new int[]{-1, -1};
    }
}