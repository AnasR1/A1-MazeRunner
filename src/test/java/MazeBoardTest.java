import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List; 

import ca.mcmaster.se2aa4.mazerunner.*;

public class MazeBoardTest {

    @Test
    public void testFindEntrance() {
        List<String> maze = Arrays.asList(
                "#####",
                "     ",
                "#   #",
                "#####"
        );
        MazeBoard mazeBoard = new MazeBoard(maze);
        int[] entrance = mazeBoard.findEntrance();
        assertArrayEquals(new int[]{1, 0}, entrance, "Entrance should be at row 1, column 0");
    }

    @Test
    public void testFindExit() {
        List<String> maze = Arrays.asList(
                "#####",
                "     ",
                "#   #",
                "#####"
        );
        MazeBoard mazeBoard = new MazeBoard(maze);
        int[] exit = mazeBoard.findExit();
        assertArrayEquals(new int[]{1, 4}, exit, "Exit should be at row 1, column 4");
    }

    @Test
    public void testExtendBoard() {
        MazeBoard mazeBoard = new MazeBoard(new ArrayList<>(Arrays.asList("#####")));
        mazeBoard.extendBoard("#   #");
        assertEquals(2, mazeBoard.maze.size(), "Maze should have 2 rows after extending");
    }
}