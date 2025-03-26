package ca.mcmaster.se2aa4.mazerunner;

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
        String inputPath = null;

        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("i")) {
                mazeFile = cmd.getOptionValue("i");
            } else {
                System.out.println("No -i flag detected");
                System.exit(1);
            }
            if (cmd.hasOption("p")){
                inputPath = cmd.getOptionValue("p");
            }
        } catch (ParseException e) {
            logger.error("Error parsing command-line arguments", e);
            System.exit(1);
        }

        System.out.println("** Starting Maze Runner");
        MazeBoardFactory factory = new MazeBoardFactory();
        MazeBoard mazeBoard = factory.createMazeBoard(mazeFile);

        System.out.println("**** Maze Layout:");
        for (String line : mazeBoard.maze) {
            System.out.println(line);
        }

        System.out.println("**** Computing path");
        int[] entrance = mazeBoard.findEntrance();
        int[] exit = mazeBoard.findExit();

        if (entrance[0] == -1 || exit[0] == -1) {
            System.out.println("Entrance or exit not found in the maze.");
            System.exit(1);
        }

        System.out.println("Entrance found at: Row " + entrance[0] + ", Column " + entrance[1]);
        System.out.println("Exit found at: Row " + exit[0] + ", Column " + exit[1]);

        PathfinderStrategy strategy = new RightHandRuleStrategy();
        PathFinder pathFinder = new PathFinder(mazeBoard, strategy);

        String path = pathFinder.findPath(entrance, exit);
        String factoredPath = pathFinder.factoredFormPath(path);
        System.out.println("Path: " + path);
        System.out.println("Factored Form Path: " + factoredPath);

        if (inputPath != null) {
            System.out.println("Validating Input path: " + inputPath);
            boolean isValid = pathFinder.pathValid(inputPath, entrance, exit);
            if (isValid) {
                System.out.println("The provided path is valid!");
            } else {
                System.out.println("The provided path is invalid!");
            }
        }
        System.out.println("** End of MazeRunner");
    }
}
