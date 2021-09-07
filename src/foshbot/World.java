package foshbot;

public interface World {

    /**
     * @return the worlds width
     */
    int getWidth();

    /**
     * @return the worlds height
     */
    int getHeight();

    /**
     * @return true if a block is in field (x, y)
     */
    boolean isBlockInField(int x, int y);

    /**
     * @return true if there is a wall at field (x, y) in direction dir
     */
    boolean fieldHasWallInDirection(int x, int y, Direction dir);

    /**
     * @return true if at least one coin is in field (x, y)
     */
    boolean isCoinInField(int x, int y);

    /**
	 * @return true if another robot is in field (x, y)
	 */
    boolean isAnotherRobotInField(int x, int y, Robot robot);

    /**
     * Puts down N coins at field (x, y)
     */
    void placeCoinStack(int x, int y, int numberOfCoins);

    /**
     * Places a block at field (x, y)
     */
    void placeBlock(int x, int y);

    /**
     * Adds a robot to the world at field (x, y) looking at direction dir
     */
    Robot newRobot(int x, int y, Direction dir, int numberOfCoins);

    /**
     * Set or remove a wall at field (x, y) in direction dir
     * true
     */
    void placeWall(int x, int y, Direction dir, boolean wall);

    /**
     * Reset the world (remove all entities and walls)
     */
    void reset();
}