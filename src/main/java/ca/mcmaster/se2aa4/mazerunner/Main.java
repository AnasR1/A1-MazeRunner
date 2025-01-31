package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        // Apache CLI Parsing
        Options options = new Options();
        options.addOption("i", true, "input the maze file");
        options.addOption("p", true, "input the maze file and path");

        CommandLineParser parser = new DefaultParser();
        String mazeFile = null;

        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("i")) {
                if (cmd.hasOption("p")){
                    String path = cmd.getOptionValue("p");
                }
                else{
                    mazeFile = cmd.getOptionValue("i");
                }
            } else {
                logger.error("No -i flag detected");
                System.exit(1);
            }
        } catch (ParseException e) {
            logger.error("Error parsing command-line arguments", e);
            System.exit(1);
        }

        logger.info("** Starting Maze Runner");
        List<String> maze = new ArrayList<>();
        MazeBoard mazeBoard = new MazeBoard(maze);

        try (BufferedReader reader = new BufferedReader(new FileReader(mazeFile))) {
            logger.info("**** Reading the maze from file " + mazeFile);
            String line;
            while ((line = reader.readLine()) != null) {
                mazeBoard.extendBoard(line);
            }
        } catch (Exception e) {
            logger.error("Error reading file: ");
            System.exit(1);
        }

        logger.info("**** Maze Layout:");
        for (String line : mazeBoard.maze) {
            logger.info(line);
        }

        logger.info("**** Computing path");
        int[] entrance = mazeBoard.findEntrance();
        int[] exit = mazeBoard.findExit();

        if (entrance[0] == -1 || exit[0] == -1) {
            logger.error("Entrance or exit not found in the maze.");
            System.exit(1);
        }

        logger.info("Entrance found at: Row " + entrance[0] + ", Column " + entrance[1]);
        logger.info("Exit found at: Row " + exit[0] + ", Column " + exit[1]);

        PathFinder pathFinder = new PathFinder(mazeBoard);

        String path = pathFinder.Pathfinder(entrance, exit);
        String factoredPath = pathFinder.factoredFormPath(path);
        logger.info("Path: " + path);
        logger.info("Factored Form Path: " + factoredPath);

        logger.info("** End of MazeRunner");
    }
}
