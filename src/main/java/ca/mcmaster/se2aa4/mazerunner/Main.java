package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        //Apache CLI Parsing
        Options options = new Options();
        options.addOption("i", true, "input the maze file");
        options.addOption("p", true, "taking input file and path string");

        CommandLineParser parser = new DefaultParser();
        String maze_file = null;
        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("i")) {
                maze_file = cmd.getOptionValue("i");
            }
            else{
                logger.error("No -i flag detected");
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        logger.info("** Starting Maze Runner");
        List<String> maze = new ArrayList<String>();
        MazeBoard mazeBoard = new MazeBoard(maze);
        try {
            logger.info("**** Reading the maze from file " + maze_file);
            BufferedReader reader = new BufferedReader(new FileReader(maze_file));
            String line;
            while ((line = reader.readLine()) != null) {
                mazeBoard.extendBoard(line);
            }
        } catch(Exception e) {
            logger.error("Error Reading File");
        }

        for (String line:maze) {
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


        try {
            //Try the pathing algorithm from player class
            List<String> path = new ArrayList<>();
            logger.info("Path instructions:");
            for (String instruction : path) {
                System.out.println(instruction);
            }
        }
        catch (Exception e){
            logger.warn("PATH NOT COMPUTED");
            logger.info("** End of MazeRunner");
        }
    }
}
