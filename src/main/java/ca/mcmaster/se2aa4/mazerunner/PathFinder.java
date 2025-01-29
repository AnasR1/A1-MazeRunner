package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathFinder {
    StringBuilder path = new StringBuilder();
    private static final Logger logger = LogManager.getLogger();

    private static int[][] DIRECTIONS = {
            {-1, 0}, // North
            {0, 1},  // East
            {1, 0},  // South
            {0, -1}  // West
    };
    private MazeBoard mazeBoard; // Reference to the MazeBoard object

    public PathFinder(MazeBoard mazeBoard) {
        this.mazeBoard = mazeBoard;
    }

    protected String Pathfinder(int[] entrance, int[] exit){
        int row = entrance[0];
        int col = exit[1];
        int direction = 1;
        List<String> instructions = new ArrayList<>();

        while (row != exit[0] || col != exit[1]) {
            for (int i = -1; i <= 1; i++) {
                int newDirection = (direction + i + 4) % 4;
                int newRow = row + DIRECTIONS[newDirection][0];
                int newCol = col + DIRECTIONS[newDirection][1];
                logger.info(newDirection + " " + newRow + " " + newCol);
                if (canMove(newRow, newCol)) {
                    logger.info(canMove(newRow, newCol));
                    if (i == -1) {
                        instructions.add("L");
                    }
                    else if (i == 1) {
                        instructions.add("R");
                    }
                    instructions.add("F");
                    row = newRow;
                    col = newCol;
                    direction = newDirection;
                    logger.info(instructions);
                    break;
                }
            }
        }

        for (String instruction : instructions) {
            path.append(instruction);
        }

        return path.toString();
    }
    private boolean canMove(int row, int col) {
        logger.info(row + " " + col + " " + mazeBoard.maze.get(row).charAt(col));
        return row >= 0 && row < mazeBoard.maze.size() && col >= 0 && col < mazeBoard.maze.getFirst().length() && mazeBoard.maze.get(row).charAt(col) == ' ';
    }
}