package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathFinder{

    private PathfinderStrategy strategy;
    private MazeBoard mazeBoard;
    private static final Logger logger = LogManager.getLogger();

    public PathFinder(MazeBoard mazeBoard, PathfinderStrategy strategy) {
        this.mazeBoard = mazeBoard;
        this.strategy = strategy;
    }

    private static final int[][] DIRECTIONS = {
        {-1, 0}, // North
        {0, 1},  // East
        {1, 0},  // South
        {0, -1}  // West
    };
    
    public String findPath(int[] entrance, int[] exit) {
        logger.info("Computing path using strategy: " + strategy.getClass().getSimpleName());
        return strategy.computePath(mazeBoard, entrance, exit);
    }

    protected String factoredFormPath(String path){
        StringBuilder factored_path = new StringBuilder();
        int counter = 0;
        char curr_letter = path.charAt(0);
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == curr_letter) {
                counter++;
            }
            else{
                factored_path.append(counter).append(path.charAt(i-1));
                counter = 1;
                curr_letter = path.charAt(i);
            }
        }
        factored_path.append(counter).append(path.charAt(path.length()-1));
        return factored_path.toString();
    }

    //NEEDS To be updated to accept factored form paths currently only works with full paths
    protected boolean pathValid(String path, int[] entrance, int[] exit) {
        int currRow = entrance[0];
        int currCol = entrance[1];
        int direction = 1;

        logger.info("Executing path validator");
        logger.info("Entrance: Row " + currRow + " Col " + currCol);
        logger.info("Exit: Row " + exit[0] + " Col " + exit[1]);

        for (int i = 0; i < path.length(); i++) {
            char move = path.charAt(i);
            switch (move) {
                case 'F': {
                    int newRow = currRow + DIRECTIONS[direction][0];
                    int newCol = currCol + DIRECTIONS[direction][1];
                    if (!canMove(newRow, newCol)) {
                        logger.warn("Invalid move at step " + i + ": cannot move forward from (" + currRow + ", " + currCol + ")");
                        return false;
                    }
                    currRow = newRow;
                    currCol = newCol;
                    break;
                }
                case 'L': {
                    direction = (direction + 3) % 4;
                    break;
                }
                case 'R': {
                    direction = (direction + 1) % 4;
                    break;
                }
                case ' ': {
                    break;
                }
                default: {
                    logger.warn("Invalid instruction '" + move + "' at step " + i);
                    return false;
                }
            }
            logger.info("Step " + i + " executed. New position: (" + currRow + ", " + currCol + "), direction: " + direction);
        }

        if (currRow == exit[0] && currCol == exit[1]) {
            logger.info("Path validation succeeded: reached exit at (" + currRow + ", " + currCol + ")");
            return true;
        } else {
            logger.warn("Path ended at (" + currRow + ", " + currCol + ") which does not match exit (" + exit[0] + ", " + exit[1] + ")");
            return false;
        }
    }

    private boolean canMove(int row, int col) {
        if (row < 0 || row >= mazeBoard.maze.size()) {
            return false;
        }
        if (col < 0) {
            return false;
        }
        String line = mazeBoard.maze.get(row);
        if (col >= line.length()) {
            return true;
        }
        char cell = line.charAt(col);
        return (cell == ' ' || cell == '\0');
    }
}