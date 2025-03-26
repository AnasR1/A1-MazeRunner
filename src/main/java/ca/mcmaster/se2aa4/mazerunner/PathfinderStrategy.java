package ca.mcmaster.se2aa4.mazerunner;

public interface PathfinderStrategy {
    public String computePath(MazeBoard mazeBoard, int[] entrance, int[] exit);
}
