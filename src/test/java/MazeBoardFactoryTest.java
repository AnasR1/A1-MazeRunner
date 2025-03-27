import ca.mcmaster.se2aa4.mazerunner.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MazeBoardFactoryTest {

    @Test
    public void testMazeBoardCreation() {
        MazeBoardFactory factory = new MazeBoardFactory();
        MazeBoard mazeBoard = factory.createMazeBoard("./examples/tiny.maz.txt"); 

        assertNotNull(mazeBoard, "MazeBoard should not be null");
        assertFalse(mazeBoard.maze.isEmpty(), "MazeBoard should contain data");
    }
}