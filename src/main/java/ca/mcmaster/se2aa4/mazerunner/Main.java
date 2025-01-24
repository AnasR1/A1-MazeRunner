package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
        try {
            logger.info("**** Reading the maze from file " + maze_file);
            BufferedReader reader = new BufferedReader(new FileReader(maze_file));
            String line;
            while ((line = reader.readLine()) != null) {
                maze.add(line);
            }
        } catch(Exception e) {
            logger.error("Error Reading File");
        }

        for (String line:maze) {
            logger.info(line);
        }
        logger.info("**** Computing path");
        try {
            //Try the pathing algorithm from player class

        }
        catch (Exception e){
            logger.warn("PATH NOT COMPUTED");
            logger.info("** End of MazeRunner");
        }
    }
}
