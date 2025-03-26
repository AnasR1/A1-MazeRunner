package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeBoardFactory {
    private static final Logger logger = LogManager.getLogger();

    public MazeBoard createMazeBoard(String filePath) {
        List<String> maze = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            logger.info("Reading the maze from file: " + filePath);
            String line;
            while ((line = reader.readLine()) != null) {
                maze.add(line);
            }
        } catch (Exception e) {
            logger.error("Error reading maze file: " + filePath, e);
            System.exit(1);
        }

        return new MazeBoard(maze);
    }
}