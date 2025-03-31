import ca.mcmaster.se2aa4.mazerunner.*;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PathFinderTest {

    @Test
    public void testPathfinderBasic() {
        List<String> maze = Arrays.asList(
                "#####",
                "     ",
                "#   #",
                "#####");
        MazeBoard mazeBoard = new MazeBoard(maze);
        PathfinderStrategy strategy = new RightHandRuleStrategy();
        PathFinder pathFinder = new PathFinder(mazeBoard, strategy);

        int[] entrance = {1, 0};
        int[] exit = {1, 4};

        String path = pathFinder.findPath(entrance, exit);
        assertFalse(path.isEmpty(), "Path should not be empty");
    }

    @Test
    public void testFactoredFormPath() {
        PathfinderStrategy strategy = new RightHandRuleStrategy();
        List<String> maze = Arrays.asList(
                "#####",
                "     ",
                "#   #",
                "#####"
        );
        MazeBoard mazeBoard = new MazeBoard(maze);
        PathFinder pathFinder = new PathFinder(mazeBoard, strategy);
        String factoredPath = pathFinder.getFactoredFormPath("FFFF");
        assertEquals("4F", factoredPath, "Factored path should be compressed properly");
    }

    @Test
    public void testPathValidationValid() {
        List<String> maze = Arrays.asList(
                "#####",
                "     ",
                "#   #",
                "#####"
        );
        MazeBoard mazeBoard = new MazeBoard(maze);
        PathfinderStrategy strategy = new RightHandRuleStrategy();
        PathFinder pathFinder = new PathFinder(mazeBoard, strategy);

        int[] entrance = {1, 0};
        int[] exit = {1, 4};

        assertTrue(pathFinder.getPathValid("FFFF", entrance, exit), "Valid path should return true");
    }

    @Test
    public void testPathValidationInvalid() {
        List<String> maze = Arrays.asList(
                "#####",
                "     ",
                "#   #",
                "#####"
        );
        MazeBoard mazeBoard = new MazeBoard(maze);
        PathfinderStrategy strategy = new RightHandRuleStrategy();
        PathFinder pathFinder = new PathFinder(mazeBoard, strategy);

        int[] entrance = {1, 0};
        int[] exit = {1, 4};

        assertFalse(pathFinder.getPathValid("FFFFFF", entrance, exit), "Invalid path should return false");
    }

    @Test
    public void testFactoredPathValidation() {
        List<String> maze = Arrays.asList(
                "#####",
                "     ",
                "#   #",
                "#####"
        );
        MazeBoard mazeBoard = new MazeBoard(maze);
        PathfinderStrategy strategy = new RightHandRuleStrategy();
        PathFinder pathFinder = new PathFinder(mazeBoard, strategy);

        int[] entrance = {1, 0};
        int[] exit = {1, 4};

        assertTrue(pathFinder.getPathValid("4F", entrance, exit), "Valid path should return true");
    }
    
    @Test
    public void testFactoredPathValidationInvalid() {
        List<String> maze = Arrays.asList(
                "#####",
                "     ",
                "#   #",
                "#####"
        );
        MazeBoard mazeBoard = new MazeBoard(maze);
        PathfinderStrategy strategy = new RightHandRuleStrategy();
        PathFinder pathFinder = new PathFinder(mazeBoard, strategy);

        int[] entrance = {1, 0};
        int[] exit = {1, 4};

        assertFalse(pathFinder.getPathValid("5F", entrance, exit), "Invalid path should return false");
    }
}