import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.cli.*;

public class MainTest {

    @Test
    public void testCommandLineParsingValid() throws ParseException {
        String[] args = {"-i", "maze.txt"};
        Options options = new Options();
        options.addOption("i", true, "input the maze file");
        options.addOption("p", true, "input the maze file and path");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        assertTrue(cmd.hasOption("i"), "Command-line should have option -i");
        assertEquals("maze.txt", cmd.getOptionValue("i"), "Maze file name should match");
    }

    @Test
    public void testCommandLineParsingMissingFile() {
        String[] args = {"-p", "path.txt"};
        Options options = new Options();
        options.addOption("i", true, "input the maze file");

        CommandLineParser parser = new DefaultParser();
        Exception exception = assertThrows(ParseException.class, () -> {
            parser.parse(options, args);
        });

        assertNotNull(exception.getMessage(), "Exception should be thrown for missing file");
    }
}
