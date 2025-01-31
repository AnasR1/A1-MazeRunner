package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathFinder {
    StringBuilder path = new StringBuilder();
    private static final Logger logger = LogManager.getLogger();

    private static final int[][] DIRECTIONS = {
            {-1, 0}, // North
            {0, 1},  // East
            {1, 0},  // South
            {0, -1}  // West
    };

    private MazeBoard mazeBoard;

    public PathFinder(MazeBoard mazeBoard) {
        this.mazeBoard = mazeBoard;
    }

    protected String Pathfinder(int[] entrance, int[] exit) {
        int currRow = entrance[0];
        int currCol = entrance[1];
        int direction = 1; // Start facing East (right)
        List<String> instructions = new ArrayList<>();

        logger.info("Executing path finder using the right-hand rule");
        logger.info("Entrance: Row " + currRow + " Col " + currCol);
        logger.info("Exit: Row " + exit[0] + " Col " + exit[1]);

        while (currRow != exit[0] || currCol != exit[1]) {
            int rightDir = (direction + 1) % 4;
            int forwardDir = direction;
            int leftDir = (direction + 3) % 4;
            int backDir = (direction + 2) % 4;

            int rightRow = currRow + DIRECTIONS[rightDir][0];
            int rightCol = currCol + DIRECTIONS[rightDir][1];
            int forwardRow = currRow + DIRECTIONS[forwardDir][0];
            int forwardCol = currCol + DIRECTIONS[forwardDir][1];
            int leftRow = currRow + DIRECTIONS[leftDir][0];
            int leftCol = currCol + DIRECTIONS[leftDir][1];
            int backRow = currRow + DIRECTIONS[backDir][0];
            int backCol = currCol + DIRECTIONS[backDir][1];

            if (canMove(rightRow, rightCol)) {
                instructions.add("R");
                instructions.add("F");
                currRow = rightRow;
                currCol = rightCol;
                direction = rightDir;
            } else if (canMove(forwardRow, forwardCol)) {
                instructions.add("F");
                currRow = forwardRow;
                currCol = forwardCol;
            } else if (canMove(leftRow, leftCol)) {
                instructions.add("L");
                instructions.add("F");
                currRow = leftRow;
                currCol = leftCol;
                direction = leftDir;
            } else if (canMove(backRow, backCol)) {
                instructions.add("R");
                instructions.add("R");
                instructions.add("F");
                currRow = backRow;
                currCol = backCol;
                direction = backDir;
            } else {
                logger.warn("No valid move found, breaking loop");
                break;
            }
            logger.info("Current Position: Row " + currRow + " Col " + currCol);
        }

        for (String instruction : instructions) {
            path.append(instruction);
        }
        return path.toString();
    }

    private boolean canMove(int row, int col) {
        if (row < 0 || row >= mazeBoard.maze.size() || col < 0 || col >= mazeBoard.maze.get(row).length()) {
            return false;
        }
        return (mazeBoard.maze.get(row).charAt(col) == ' ' || mazeBoard.maze.get(row).isEmpty());
    }
}