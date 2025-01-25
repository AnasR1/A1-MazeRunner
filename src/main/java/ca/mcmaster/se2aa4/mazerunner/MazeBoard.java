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
        int lastCol = maze.get(0).length() - 1;

        for (int row = 1; row < maze.size() - 1; row++) {
            if (maze.get(row).charAt(lastCol) == ' ') {
                return new int[]{row, lastCol};
            }
        }
        return new int[]{-1, -1};
    }

    public String findPath(int[] entrance, int[] exit) {
        StringBuilder path = new StringBuilder();
        int row = entrance[0];
        int col = entrance[1];
        int direction = 1;
        List<String> instructions = new ArrayList<>();

        while (row != exit[0] || col != exit[1]) {
            boolean moved = false;

            for (int i = -1; i <= 1; i++) {
                int newDirection = (direction + i + 4) % 4;
                int newRow = row + DIRECTIONS[newDirection][0];
                int newCol = col + DIRECTIONS[newDirection][1];

                if (canMove(newRow, newCol)) {
                    if (i == -1) {
                        instructions.add("L");
                    } else if (i == 1) {
                        instructions.add("R");
                    }
                    instructions.add("F");

                    row = newRow;
                    col = newCol;
                    direction = newDirection;
                    moved = true;

                    break;
                }
            }

            if (!moved) {
                return "NO PATH FOUND";
            }
        }

        for (String instruction : instructions) {
            path.append(instruction);
        }
        return path.toString();
    }

    private boolean canMove(int row, int col) {
        return row >= 0 && row < maze.size() &&
                col >= 0 && col < maze.get(0).length() &&
                maze.get(row).charAt(col) == ' ';
    }
}
