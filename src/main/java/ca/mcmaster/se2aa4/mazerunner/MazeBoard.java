package ca.mcmaster.se2aa4.mazerunner;

abstract class Game {
    abstract int[] findEntrance();
    abstract int[] findExit();
}
public class MazeBoard extends Game {
    @Override
    int[] findEntrance() {
        return new int[0];
    }

    @Override
    int[] findExit() {
        return new int[0];
    }
}