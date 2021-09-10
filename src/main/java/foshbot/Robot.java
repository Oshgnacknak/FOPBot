package foshbot;

public interface Robot {

    /**
     * Turns the robots body to the left
     */
    void turnLeft();


    /**
     * Move the robot one step forward in the current direction
     */
    void move();


    /**
     * Puts down one coin at the current position
     */
    void putCoin();

    /**
     * Picks up one coin at the current position
     */
    void pickCoin();


    /**
     * @return the current direction of the robot
     */
    foshbot.Direction getDirection();


    /**
     * @return the number of coins the robot possesses
     */
    int getNumberOfCoins();

    /**
     * @return true if the robot has any coins
     */
    default boolean hasAnyCoins() {
        return getNumberOfCoins() > 0;
    }

    /**
     * @return true if the robot is facing up
     */
    default boolean isFacingUp() {
        return getDirection() == Direction.NORTH;
    }

    /**
     * @return true if the robot is facing down
     */
    default boolean isFacingDown() {
        return getDirection() == Direction.SOUTH;
    }

    /**
     * @return true if the robot is facing left
     */
    default boolean isFacingLeft() {
        return getDirection() == Direction.EAST;
    }

    /**
     * @return true if the robot is facing right
     */
    default boolean isFacingRight() {
        return getDirection() == Direction.WEST;
    }

    /**
     * Turn off the robot
     */
    void turnOff();

    /**
     * @return true if the robot is turned off
     */
    boolean isTurnedOff();

    /**
     * @return true if the robot is turned on
     */
    default boolean isTurnedOn() {
        return !isTurnedOff();
    }

    /**
     * @return true if the robot is standing on a coin/if at least one coin is at
     *         the same position as the robot's position
     */
    boolean isNextToACoin();

    /**
     * @return true if at least another robot is at the same position as the robot's
     *         position
     */
    boolean isNextToARobot();

    /**
     * Sets the robot's x-coordinate
     */
    void setX(int x);

    /**
     * @return The robot's x-coordinate
     */
    int getX();

    /**
     * Sets the robot's y-coordinate
     */
    void setY(int y);

    /**
     * @return The robot's y-coordinate
     */
    int getY();

    /**
     * Sets the robot's field (x,y)
     */
    void setField(int x, int y);

    /**
     * @return true if the robot is not facing an object that is standing in the way
     *         (e.g. a wall)
     */
    boolean isFrontClear();
}